package com.mongodb.lecture5;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * Created by sdakhani on 4/17/16.
 */
public class AggregateJavaDriver {

    public static void main(String[] args) {

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("zipcodes");
        MongoCollection<Document> collection = db.getCollection("zips");

/*      List<Document> documents = collection.find().into(new ArrayList<Document>());

        for (Document doc:documents) {
           System.out.println(doc.toJson());
        }*/

        //  { $group: { _id: "$state", totalPop: { $sum: "$pop" } } },
        //  { $match: { totalPop: { $gte: 10*1000*1000 } } }

/*        List<Document> pipeline = asList(new Document("$group",
                                                        new Document("_id","$state")
                                                                .append("totalPop",new Document("$sum","$pop"))),
                                         new Document("$match",new Document("totalPop",new Document("$gte",10000000))));*/


         //Using builders
/*        List<Bson> pipeline = asList(group("$state",sum("totalPop","$pop")),
                                     match(gt("totalPop",10000000)));
          List<Document> documents = collection.aggregate(pipeline).into(new ArrayList<Document>());*/

         //using parse
          List<Document> pipeline = asList( Document.parse("{ $group: { _id: \"$state\", totalPop: { $sum: \"$pop\" } } }"),
                                            Document.parse("{ $match: { totalPop: { $gte: 10000000 } } }"));

          List<Document> documents = collection.aggregate(pipeline).into(new ArrayList<Document>());

        for (Document doc:documents) {
            System.out.println(doc.toJson());
        }

    }
}
