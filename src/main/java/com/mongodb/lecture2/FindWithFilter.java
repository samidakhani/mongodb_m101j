package com.mongodb.lecture2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.lecture2.util.Helper;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.*;


/**
 * Created by sdakhani on 3/26/16.
 */
public class FindWithFilter {

    public static void main(String[] args) {

        MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase db = client.getDatabase("test");
        MongoCollection<Document> collection = db.getCollection("people");

        collection.drop();

        for ( int i=0;i<10;i++){
            collection.insertOne(new Document().append("x",new Random().nextInt(2))
                                                .append("y",new Random().nextInt(100)));
        }

        Bson filter = new Document("x",0)
                    .append("y", new Document().append("$gt",10).append("$lt",40));

        //Filter selection
        Bson filter1= and(eq("x",0),gt("y",10),lt("y",40));

        Bson projection =fields(include("y","x"),excludeId());

        Bson sort =orderBy(descending("y"),ascending("x"));

        System.out.println("Find all with into");
        List<Document> list = collection.find(filter1).projection(projection).sort(sort).into(new ArrayList<Document>());
        for(Document doc : list)
            Helper.printJson(doc);

        System.out.println("Count");
        long count = collection.count(filter1);
        System.out.print(count);
    }
}
