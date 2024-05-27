import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.Values;

import static org.neo4j.driver.Values.parameters;

import java.util.LinkedList;
import java.util.List;

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
                                myUsers.add(new CompatibleUser(
                                        record.get("CompatibleUser").asString(),
                                        record.get("SharedInterestsCount").asInt(),
                                        record.get("SharedCharacteristicsCount").asInt()));
                            }
                            return myUsers;
                        }
                    });
            return compatibleUsers;
        }
    }

    public String insertUser(String name, int age, String user, String password) {
        try (Session session = driver.session()) {
            String result = session.writeTransaction(new TransactionWork<String>() {
                @Override
                public String execute(Transaction tx) {
                    tx.run("CREATE (p:Person {name:'" + name + "', age:" + age + "', user:" + user + ", password:'"
                            + password + "'})");
                    return "OK";

                    // AGREGUAR PREGUNTAS PARA LAS RELACIONES

                }
            });
            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}