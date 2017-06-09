package com.mechanitis.mongodb.gettingstarted;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

public class Exercise1ConnectingTest {

	@Test
	public void shouldCreateANewMongoClientConnectedToLocalhost() throws Exception {
		// When
		// TODO: get/create the MongoClient
		MongoClient mongoClient = null;
		MongoClientURI mongoClientURI = new MongoClientURI("mongodb://localhost:27017");
		mongoClient = new MongoClient(mongoClientURI);

		// Then
		assertThat(mongoClient, is(notNullValue()));
	}
}
