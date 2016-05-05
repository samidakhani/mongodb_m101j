package com.mongodb.lecture2.util;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * Created by sdakhani on 3/27/16.
 */
public class PeopleApp {

    public static void main(String[] args) {

        MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase db = client.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("people");

        Document document = new Document("_id","sana")
                                    .append("password","xyz");

         document.append("eamil","samidkahani@gmail.com");

        collection.insertOne(document);
    }
}
