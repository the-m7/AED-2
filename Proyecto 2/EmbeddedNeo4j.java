import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Record;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import org.neo4j.driver.summary.ResultSummary;

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

    public LinkedList<String> getUsersByInterest(String interst) {
        try (Session session = driver.session()) {

            LinkedList<String> users = session.readTransaction(new TransactionWork<LinkedList<String>>() {
                @Override
                public LinkedList<String> execute(Transaction tx) {
                    Result result = tx.run("MATCH (interest:Interest {name:\"" + interst
                            + "\"})<-[:LIKES]-(people:Person) RETURN people.name;");
                    LinkedList<String> myusers = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size() / 2; i++) {
                        myusers.add(registros.get(i).get("people.name").asString());
                    }
                    return myusers;
                }
            });
            return users;
        }
    }

    public String insertUser(String name, int age, String tagline) {
        try (Session session = driver.session()) {

            String result = session.writeTransaction(new TransactionWork<String>()

            {
                @Override
                public String execute(Transaction tx) {
                    tx.run("CREATE (Test:Movie {title:'" + name + "', released:" + age + ", tagline:'"
                            + tagline + "'})");

                    return "OK";
                }
            }

            );

            return result;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}