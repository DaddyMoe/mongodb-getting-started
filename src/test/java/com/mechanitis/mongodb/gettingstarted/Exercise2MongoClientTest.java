package com.mechanitis.mongodb.gettingstarted;

import com.mongodb.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class Exercise2MongoClientTest {

	private static MongoClient mongoClient;

	@Before
	public void setUp() throws Exception {
		MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localhost:27017");
		mongoClient = new MongoClient(mongoClientURI);
	}

	@After
	public void tearDown() throws Exception {
		mongoClient.close();
	}

	@Test
	public void shouldGetADatabaseFromTheMongoClient() throws Exception {
		// Given
		// TODO any setup

		// When
		//TODO get the database from the client
		DB database = mongoClient.getDB("local");

		// Then
		assertThat(database, is(notNullValue()));
	}

	@Test
	public void shouldGetACollectionFromTheDatabase() throws Exception {
		// Given
		// TODO any setup

		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("mongo-getting-started");

		// When
		// TODO get collection
		DBCollection collection = database.getCollection("my-collection");

		// Then
		assertThat(collection, is(notNullValue()));
		mongoClient.close();
	}

	@Test(expected = Exception.class)
	public void shouldNotBeAbleToUseMongoClientAfterItHasBeenClosed() throws UnknownHostException {
		// Given
		MongoClient mongoClient = new MongoClient();

		// When
		// TODO close the mongoClient
		mongoClient.close();

		// Then
		mongoClient.getDB("SomeDatabase").getCollection("coll").insert(new BasicDBObject("field", "value"));
	}

}
