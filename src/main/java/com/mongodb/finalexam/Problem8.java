package com.mongodb.finalexam;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by sdakhani on 4/30/16.
 */
public class Problem8 {

    public static void main(String[] args) {
        MongoClient c =  new MongoClient();
        MongoDatabase db = c.getDatabase("test");
        MongoCollection<Document> animals = db.getCollection("animals");

        Document animal = new Document("animal", "monkey");

        animals.insertOne(animal);
        animal.remove("animal");
        animal.append("animal", "cat");
        animals.insertOne(animal);
        animal.remove("animal");
        animal.append("animal", "lion");
        animals.insertOne(animal);
    }
}
