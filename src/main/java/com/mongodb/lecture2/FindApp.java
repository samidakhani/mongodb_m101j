package com.mongodb.lecture2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.lecture2.util.Helper;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sdakhani on 3/26/16.
 */
public class FindApp {

    public static void main(String[] args) {
        MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase db = client.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("people");

        collection.drop();

        for(int i=0;i<10;i++){
            collection.insertOne(new Document("name",i));
        }

        System.out.println("Find one");
        Document document = collection.find().first();
        Helper.printJson(document);

        System.out.println("Find all with into");
        List<Document> list = collection.find().into(new ArrayList<Document>());
        for(Document doc : list)
            Helper.printJson(doc);

        System.out.println("Find all with iteration");
        MongoCursor<Document> cur = collection.find().iterator();

        while(cur.hasNext())
            Helper.printJson(cur.next());

        System.out.println("Count");
        long count = collection.count();
        System.out.print(count);
    }
}
