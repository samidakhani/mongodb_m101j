package com.mongodb.lecture2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.lecture2.util.Helper;
import org.bson.Document;

import java.util.Arrays;

/**
 * Created by sdakhani on 3/26/16.
 */
public class InsertApp {
    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase db = client.getDatabase("test");
        MongoCollection<Document> coll = db.getCollection("people");

        coll.drop();
        Document sami = new Document()
                .append("name","Sami")
                .append("age",23);

        Document suraj = new Document()
                .append("name","Suraj")
                .append("age",25);

        coll.insertMany(Arrays.asList(sami,suraj));

        Helper.printJson(sami);
        Helper.printJson(suraj);


    }
}
