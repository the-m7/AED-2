import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.Value;
import org.neo4j.driver.Values;

import static org.neo4j.driver.Values.parameters;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class EmbeddedNeo4j implements AutoCloseable {

    private final Driver driver;

    public EmbeddedNeo4j(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }

    public void printGreeting(final String message) {
        try (Session session = driver.session()) {
            String greeting = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    Result result = tx.run("CREATE (a:Greeting) " +
                            "SET a.message = $message " +
                            "RETURN a.message + ', from node ' + id(a)",
                            parameters("message", message));
                    return result.single().get(0).asString();
                }
            });
            System.out.println(greeting);
        }
    }

    public LinkedList<String> getUsers() {
        try (Session session = driver.session()) {

            LinkedList<String> users = session.readTransaction(new TransactionWork<LinkedList<String>>() {
                @Override
                public LinkedList<String> execute(Transaction tx) {
                    // Result result = tx.run( "MATCH (people:Person) RETURN people.name");
                    Result result = tx.run("MATCH (people:Person) RETURN people.name");
                    LinkedList<String> myusers = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                        myusers.add(registros.get(i).get("people.name").asString());
                    }

                    return myusers;
                }
            });
            return users;
        }
    }

    public LinkedList<String> getCompatibleUsers(String userName) {
        try (Session session = driver.session()) {

            LinkedList<String> compatibleUsers = session.readTransaction(new TransactionWork<LinkedList<String>>() {
                @Override
                public LinkedList<String> execute(Transaction tx) {
                    Result result = tx
                            .run("MATCH (p1:Person {name:\"" + userName + "\"})-[:IDENTIFIES]->(genderP1:Gender), " +
                                    "(p2:Person)-[:WANTS]->(genderP1), " +
                                    "(p2)-[:IDENTIFIES]->(genderP2:Gender), " +
                                    "(p1)-[:WANTS]->(genderP2) " +
                                    "RETURN p2.name");
                    LinkedList<String> myusers = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                        myusers.add(registros.get(i).get("p2.name").asString());
                    }
                    return myusers;
                }
            });
            return compatibleUsers;
        }
    }

    public LinkedList<String> getUsersByInterest(String interst) {
        try (Session session = driver.session()) {

            LinkedList<String> users = session.readTransaction(new TransactionWork<LinkedList<String>>() {
                @Override
                public LinkedList<String> execute(Transaction tx) {
                    Result result = tx.run("MATCH (interest:Interest {name:\"" + interst
                            + "\"})<-[:LIKES]-(people:Person) RETURN people.name;");
                    LinkedList<String> myusers = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                        myusers.add(registros.get(i).get("people.name").asString());
                    }
                    return myusers;
                }
            });
            return users;
        }
    }

    public LinkedList<CompatibleUser> getCompatibleUsersWithSharedCounts(String userName) {
        try (Session session = driver.session()) {

            UserProfile userProfile = getUserProfile(userName);

            LinkedList<CompatibleUser> compatibleUsers = session
                    .readTransaction(new TransactionWork<LinkedList<CompatibleUser>>() {
                        @Override
                        public LinkedList<CompatibleUser> execute(Transaction tx) {
                            Result result = tx.run(
                                    "MATCH (user:Person {name: $userName})-[:IDENTIFIES]->(userGender:Gender), " +
                                            "(user)-[:LIVES]->(userRegion:Reg), " +
                                            "(potentialMatch:Person)-[:WANTS]->(userGender), " +
                                            "(potentialMatch)-[:LIVES]->(potentialMatchRegion:Reg), " +
                                            "(potentialMatch)-[:IDENTIFIES]->(potentialMatchGender:Gender), " +
                                            "(user)-[:WANTS]->(potentialMatchGender) " +
                                            "WHERE abs(user.age - potentialMatch.age) <= 3 " +
                                            "AND userRegion = potentialMatchRegion " +
                                            "WITH user, potentialMatch " +
                                            "OPTIONAL MATCH (user)-[:LIKES]->(sharedInterest:Interest)<-[:LIKES]-(potentialMatch) "
                                            +
                                            "OPTIONAL MATCH (user)-[:IS]->(sharedCharacteristic:Charac)<-[:IS]-(potentialMatch) "
                                            +
                                            "RETURN potentialMatch.name AS CompatibleUser, " +
                                            "count(DISTINCT sharedInterest) AS SharedInterestsCount, " +
                                            "count(DISTINCT sharedCharacteristic) AS SharedCharacteristicsCount " +
                                            "ORDER BY SharedInterestsCount + SharedCharacteristicsCount DESC",
                                    Values.parameters("userName", userName));

                            LinkedList<CompatibleUser> myUsers = new LinkedList<>();
                            List<Record> records = result.list();
                            for (Record record : records) {
                                String compatibleUserName = record.get("CompatibleUser").asString();
                                int sharedInterestsCount = record.get("SharedInterestsCount").asInt();
                                int sharedCharacteristicsCount = record.get("SharedCharacteristicsCount").asInt();

                                UserProfile compatibleUserProfile = getUserProfile(compatibleUserName);

                                myUsers.add(new CompatibleUser(
                                        compatibleUserName,
                                        sharedInterestsCount,
                                        sharedCharacteristicsCount,
                                        compatibleUserProfile));
                            }
                            return myUsers;
                        }
                    });
            return compatibleUsers;
        }
    }

    public UserProfile getUserProfile(String userName) {
        try (Session session = driver.session()) {

            UserProfile userProfile = session.readTransaction(new TransactionWork<UserProfile>() {
                @Override
                public UserProfile execute(Transaction tx) {
                    Result result = tx.run(
                            "MATCH (user:Person {name: $userName})-[:LIVES]->(userRegion:Reg), " +
                                    "(user)-[:IDENTIFIES]->(userGender:Gender) " +
                                    "OPTIONAL MATCH (user)-[:LIKES]->(interest:Interest) " +
                                    "OPTIONAL MATCH (user)-[:IS]->(characteristic:Charac) " +
                                    "RETURN user.age AS age, " +
                                    "userRegion.name AS region, " +
                                    "collect(DISTINCT interest.name) AS interests, " +
                                    "collect(DISTINCT characteristic.name) AS characteristics",
                            Values.parameters("userName", userName));

                    if (result.hasNext()) {
                        Record record = result.next();
                        int age = record.get("age").asInt();
                        String region = record.get("region").asString();
                        List<String> interests = record.get("interests").asList(Value::asString);
                        List<String> characteristics = record.get("characteristics").asList(Value::asString);

                        return new UserProfile(userName, age, region, interests, characteristics);
                    } else {
                        return null;
                    }
                }
            });
            return userProfile;
        }
    }

    public void printCompatibleUsers(String userName) {
        LinkedList<CompatibleUser> compatibleUsers = getCompatibleUsersWithSharedCounts(userName);

        for (CompatibleUser compatibleUser : compatibleUsers) {
            System.out.println(compatibleUser.getName().toUpperCase() + " <3");
            System.out.println("Cantidad de intereses compartidos: " + compatibleUser.getSharedInterestsCount());
            System.out.println(
                    "Cantidad de características compartidas: " + compatibleUser.getSharedCharacteristicsCount());

            UserProfile profile = compatibleUser.getUserProfile();
            if (profile != null) {
                System.out.println("  Edad: " + profile.getAge());
                System.out.println("  Región: " + profile.getRegion());
                System.out.println("  Intereses: " + String.join(", ", profile.getInterests()));
                System.out.println("  Características: " + String.join(", ", profile.getCharacteristics()));
                System.out.println("<3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3");
            } else {
                System.out.println("Infromación del perfil no disponible.");
            }

            System.out.println();
        }
    }

    public String insertUser(Scanner scanner) {
        try (Session session = driver.session()) {
            String result = session.writeTransaction(new TransactionWork<String>() {

                @Override
                public String execute(Transaction tx) {
                    System.out.println("Ingrese su nombre de usuario:");
                    String user = scanner.nextLine();

                    UserProfile temp = getUserProfile(user);
                    if(temp==null){           

                        System.out.println("Ingrese su contraseña:");
                        String password = scanner.nextLine();

                        System.out.println("Ingrese su nombre:");
                        String name = scanner.nextLine();

                        System.out.println("Ingrese su edad:");
                        int age = Integer.parseInt(scanner.nextLine());

                        // AGREGUAR PREGUNTAS PARA LAS RELACIONES

                        System.out.println("Seleccione cómo se identifica (Mujer/Hombre):");
                        String gender = scanner.nextLine().toLowerCase();

                        if(gender == "mujer"){
                            gender = "Female";
                        } else if (gender == "hombre") {
                            gender = "Male";
                        } else {
                            return "Error, género inválido";
                        }

                        System.out.println("Seleccione el género que le atrae (Mujer/Hombre):");
                        String attractedTo = scanner.nextLine();

                        if(attractedTo == "mujer"){
                            attractedTo = "Female";
                        } else if (attractedTo == "hombre") {
                            attractedTo = "Male";
                        } else {
                            return "Error, género inválido";
                        }

                        System.out
                                .println("Seleccione la región a la que pertenece (Sudamérica/América Central/el Caribe):");
                        String region = scanner.nextLine();

                        if(region != "Sudamérica" && region != "América Central" && region != "el Caribe"){
                            return "Error, región inválida";
                        }
                        
                        String[] Intereses = new String[5];

                        for(int i=0; i<5; i++){

                        System.out.println( "Seleccione 5 intereses" + 
                                            "\"Deportes\"\r\n" + //
                                            "\"Artes\"\r\n" + //
                                            "\"Música\"\r\n" + //
                                            "\"Ciencia\"\r\n" + //
                                            "\"Religión\"\r\n" + //
                                            "\"Videojuegos\"\r\n" + //
                                            "\"Política\"\r\n" + //
                                            "\"Cocina\"\r\n" + //
                                            "\"Cine/Teatro\"\r\n" + //
                                            "\"Literatura\"\r\n" + //
                                            "\"Voluntariado\"\r\n" + //
                                            "\"Viajes\"");
                        
                            Intereses[i] = scanner.nextLine();
                        }

                         
                        String[] cualidades = new String[5];

                        for(int i=0; i<5; i++){

                        System.out.println( "Seleccione 5 cualidades" + 
                                            "\"Tímida\"\r\n" + //
                                            "\"Inteligente\"\r\n" + //
                                            "\"Aventurera\"\r\n" + //
                                            "\"Extrovertida\"\r\n" + //
                                            "\"Introvertida\"\r\n" + //
                                            "\"Determinada\"\r\n" + //
                                            "\"Calmada\"\r\n" + //
                                            "\"Escandalosa\"\r\n" + //
                                            "\"Frustrada\"\r\n" + //
                                            "\"Cariñosa\"\r\n" + //
                                            "\"Empática\"\r\n" + //
                                            "\"Relajada\"\r\n" + //
                                            "\"Amistosa\"\r\n" + //
                                            "\"Ambiciosa\"\r\n" + //
                                            "\"Dedicada\"\r\n" + //
                                            "\"Ocupada\"\r\n" + //
                                            "\"Solitaria\"\r\n" + //
                                            "\"Popular\"\r\n" + //
                                            "\"Paciente\"\r\n" + //
                                            "\"Estable\"\r\n" + //
                                            "\"Inestable\"\r\n" + //
                                            "\"Afectiva\"\r\n" + //
                                            "\"Amable\"");
                        
                            cualidades[i] = scanner.nextLine();
                        }

                                            // HACER LAS RELACIONES CORRESPONDINETES A  identifies, likes

                        tx.run("CREATE (p:Person {name:'" + name + "', age:" + age + "', user:" + user + ", password:'"
                        + password + "'})," +
                        "(p)-[:IDENTIFIES]->(g1:Gender {name:'"+ gender +"'})," +
                        "(p)-[:WANTS]->(g2:Gender {name:'"+ attractedTo +"'})," +
                        "(p)-[:LIVES]->(r:Region {name: '"+ region +"'}\")," +
                        "(p)-[:LIKES]->(i0:Interest {name: '"+ Intereses[0] +"'}\")," +
                        "(p)-[:LIKES]->(i1:Interest {name: '"+ Intereses[1] +"'}\")," +
                        "(p)-[:LIKES]->(i2:Interest {name: '"+ Intereses[2] +"'}\")," +
                        "(p)-[:LIKES]->(i3:Interest {name: '"+ Intereses[3] +"'}\")," +
                        "(p)-[:LIKES]->(i4:Interest {name: '"+ Intereses[4] +"'}\")," +
                        "(p)-[:LIKES]->(c0:Charac {name: '"+ cualidades[0] +"'}\")," +
                        "(p)-[:LIKES]->(c1:Charac {name: '"+ cualidades[1] +"'}\")," +
                        "(p)-[:LIKES]->(c2:Charac {name: '"+ cualidades[2] +"'}\")," +
                        "(p)-[:LIKES]->(c3:Charac {name: '"+ cualidades[3] +"'}\")," +
                        "(p)-[:LIKES]->(c4:Charac {name: '"+ cualidades[4] +"'}\")," +
                        );
                        return "OK";
                    } else {
                        return "Nombre de Usuario ya está en uso";
                    }
                            
                            
                            


                }
            });
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String editUserAge(String username, int value) {
        try (Session session = driver.session()) {
            String result = session.writeTransaction(new TransactionWork<String>() {

                @Override
                public String execute(Transaction tx) {
                    tx.run("MATCH (n:Person {name:'" + username + "'})" +
                            "SET n.age = " + value);

                    return "OK";

                    // HACER LAS RELACIONES CORRESPONDINETES A región, identifies, wants, is, likes

                }
            });
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // Relación para región

    public LinkedList<String> getRegion() {
        try (Session session = driver.session()) {

            LinkedList<String> region = session.readTransaction(new TransactionWork<LinkedList<String>>() {
                @Override
                public LinkedList<String> execute(Transaction tx) {
                    // Result result = tx.run( "MATCH (people:Person) RETURN people.name");
                    Result result = tx.run("MATCH (region:Region) RETURN people.region");
                    LinkedList<String> myregion = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                        myregion.add(registros.get(i).get("people.region").asString());
                    }

                    return myregion;
                }
            });
            return region;
        }
    }

    public LinkedList<String> getCompatibleRegion(String userRegion) {
        try (Session session = driver.session()) {

            LinkedList<String> compatibleRegion = session.readTransaction(new TransactionWork<LinkedList<String>>() {
                @Override
                public LinkedList<String> execute(Transaction tx) {
                    Result result = tx
                            .run("MATCH (p1:Person {region:\"" + userRegion + "\"})-[:LIVES]->(regionP1:REGION), " +
                                    "(p2:Person)-[:LIVES]->(regionP1), " +
                                    "RETURN p2.region");
                    LinkedList<String> myregion = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                        myregion.add(registros.get(i).get("p2.region").asString());
                    }
                    return myregion;
                }
            });
            return compatibleRegion;
        }
    }

    public String iniciarSesion(String username, String password) {
        try (Session session = driver.session()) {
            String nombreUsuario = session.readTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    List<Record> registros = tx.run(
                            "MATCH (p:Person {user: $username, password: $password}) RETURN p.name",
                            Values.parameters("username", username, "password", password)).list();

                    if (registros.size() == 1) {
                        return registros.get(0).get("p.name").asString();
                    } else {
                        return null;
                    }
                }
            });
            return nombreUsuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}