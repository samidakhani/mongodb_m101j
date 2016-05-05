package com.mongodb.lecture6;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static java.util.Arrays.asList;

/**
 * Created by sdakhani on 4/23/16.
 */
public class ReplicaSet {

    public static void main(String[] args) throws InterruptedException {

        MongoClient client = new MongoClient(asList(new ServerAddress("localhost",27017),
                                                    new ServerAddress("localhost",27018)),
                MongoClientOptions.builder().requiredReplicaSetName("rs1")
                        .readPreference(ReadPreference.nearest())
                        .writeConcern(WriteConcern.ACKNOWLEDGED)
                        .build());
        MongoDatabase db = client.getDatabase("course");
        MongoCollection<Document> collection = db.getCollection("replication");

        collection.drop();

        for (int i=0;i<Integer.MAX_VALUE;i++){
            try {
                collection.insertOne(new Document("_id", i));
                System.out.println("Inserted document " + i);
            }catch (MongoException e){
                System.out.println("Error inserting document " + i);
            }
            Thread.sleep(500);
        }
    }
}
