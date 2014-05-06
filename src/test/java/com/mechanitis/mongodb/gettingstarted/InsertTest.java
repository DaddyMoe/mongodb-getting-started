package com.mechanitis.mongodb.gettingstarted;

import com.mechanitis.mongodb.gettingstarted.person.Address;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.junit.Test;

import java.net.UnknownHostException;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class InsertTest {
    @Test
    public void shouldTurnAPersonIntoADBObject() {
        // Given
        Person bob = new Person("bob", "Bob The Amazing", new Address("123 Fake St", "LondonTown", 1234567890), asList(27464, 747854));

        // When
        DBObject bobAsDBObject = bob.toDBObject();

        // Then
        String expectedDBObject = "{" +
                                  " \"_id\" : \"bob\" ," +
                                  " \"name\" : \"Bob The Amazing\" ," +
                                  " \"address\" : {" +
                                    " \"street\" : \"123 Fake St\" ," +
                                    " \"city\" : \"LondonTown\" ," +
                                    " \"phone\" : 1234567890" +
                                  "} ," +
                                  " \"books\" : [ 27464 , 747854]" +
                                  "}";
        assertThat(bobAsDBObject.toString(), is(expectedDBObject));
    }

    @Test
    public void shouldBeAbleToSaveAPerson() throws UnknownHostException {
        // Given
        MongoClient mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        DB database = mongoClient.getDB("JAXDatabase");
        DBCollection collection = database.getCollection("people");

        Person charlie = new Person("charlie", "Charles", new Address("74 That Place", "LondonTown", 1234567890), asList(1, 74));

        // When
        collection.save(charlie.toDBObject());

        // Then
        assertThat(collection.find().count(), is(1));

        // Clean up
        database.dropDatabase();
    }
}