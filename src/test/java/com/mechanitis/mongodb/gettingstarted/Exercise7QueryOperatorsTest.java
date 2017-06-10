package com.mechanitis.mongodb.gettingstarted;

import com.mechanitis.mongodb.gettingstarted.person.Address;
import com.mechanitis.mongodb.gettingstarted.person.Person;
import com.mechanitis.mongodb.gettingstarted.person.PersonAdaptor;
import com.mongodb.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class Exercise7QueryOperatorsTest {

    private DB database;
    private DBCollection collection;
    private MongoClient mongoClient;

    @Test
    public void shouldReturnADBObjectWithAPhoneNumberLessThan1000000000() {
        // Given
        Person charlie = new Person("charlie", "Charles", new Address("74 That Place", "LondonTown", 1_234_567_890), asList(1, 74));
        collection.insert(PersonAdaptor.toDBObject(charlie));

        Person bob = new Person("bob", "Bob The Amazing", new Address("123 Fake St", "LondonTown", 987_654_321), asList(27464, 747854));
        collection.insert(PersonAdaptor.toDBObject(bob));

        // When
        //TODO build up a query which checks the numeric value
        DBObject query = new BasicDBObject("address.phone", new BasicDBObject("$lt", 1_000_000_000));
        // TODO use this query to get a List of matching Documents from the database
        DBCursor results = collection.find(query);

        // Then
        assertThat(results.size(), is(1));
        assertThat((String) results.next().get("_id"), is(bob.getId()));
    }

    @Before
    public void setUp() throws UnknownHostException {
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        database = mongoClient.getDB("Examples");
        collection = database.getCollection("people");
    }

    @After
    public void tearDown() {
        database.dropDatabase();
        mongoClient.close();
    }
}
