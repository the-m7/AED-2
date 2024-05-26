
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

import java.util.LinkedList;
import java.util.List;

import static org.neo4j.driver.Values.parameters;

public class EmbeddedNeo4j implements AutoCloseable {

    private final Driver driver;

    public EmbeddedNeo4j(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    @Override
    public void close() throws Exception {
        driver.close();
    }
    // NO estoy seguro que va aqui.

    // public void printGreeting( final String message )
    // {
    // try ( Session session = driver.session() )
    // {
    // String greeting = session.writeTransaction( new TransactionWork<String>()
    // {
    // @Override
    // public String execute( Transaction tx )
    // {
    // Result result = tx.run( "CREATE (a:Greeting) " +
    // "SET a.message = $message " +
    // "RETURN a.message + ', from node ' + id(a)",
    // parameters( "message", message ) );
    // return result.single().get( 0 ).asString();
    // }
    // } );
    // System.out.println( greeting );
    // }
    // Los nodos se ingresan en linkedLists

    public LinkedList<String> getPerson() {
        try (Session session = driver.session()) {

            LinkedList<String> people = session.readTransaction(new TransactionWork<LinkedList<String>>() {
                @Override
                public LinkedList<String> execute(Transaction tx) {
                    // Result result = tx.run( "MATCH (people:Person) RETURN people.name");
                    Result result = tx.run("MATCH (people:Person) RETURN people.name");
                    LinkedList<String> mypeople = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                        // mypeople.add(registros.get(i).toString());
                        mypeople.add(registros.get(i).get("people.name").asString());
                    }

                    return mypeople;
                }
            });

            return people;
        }
    }

    public LinkedList<String> getInterest() {
        try (Session session = driver.session()) {

            LinkedList<String> interests = session.readTransaction(new TransactionWork<LinkedList<String>>() {
                @Override
                public LinkedList<String> execute(Transaction tx) {
                    // Result result = tx.run( "MATCH (people:Person) RETURN people.name");
                    Result result = tx.run("MATCH (interest:Interest) RETURN interest.name");
                    LinkedList<String> myinterests = new LinkedList<String>();
                    List<Record> registros = result.list();
                    for (int i = 0; i < registros.size(); i++) {
                        // mypeople.add(registros.get(i).toString());
                        myinterests.add(registros.get(i).get("interest.name").asString());
                    }

                    return myinterests;
                }
            });

            return interests;
        }
    }

    // En los siguientes métodos se busca a la persona por gustos, características o
    // género
    // public LinkedList<String> getPersonByInterest(String Interest)
    // {
    // try ( Session session = driver.session() )
    // {

    // LinkedList<String> actors = session.readTransaction( new
    // TransactionWork<LinkedList<String>>()
    // {
    // @Override
    // public LinkedList<String> execute( Transaction tx )
    // {
    // Result result = tx.run( "MATCH (tom:Person {name: \"" + actor +
    // "\"})-[:ACTED_IN]->(actorMovies) RETURN actorMovies.title");
    // LinkedList<String> myactors = new LinkedList<String>();
    // List<Record> registros = result.list();
    // for (int i = 0; i < registros.size(); i++) {
    // //myactors.add(registros.get(i).toString());
    // myactors.add(registros.get(i).get("actorMovies.title").asString());
    // }

    // return myactors;
    // }
    // } );

    // return actors;
    // }
    // }

    public String insertMovie(String title, int releaseYear, String tagline) {
        try (Session session = driver.session()) {

            String result = session.writeTransaction(new TransactionWork<String>()

            {
                @Override
                public String execute(Transaction tx) {
                    tx.run("CREATE (Test:Movie {title:'" + title + "', released:" + releaseYear + ", tagline:'"
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
