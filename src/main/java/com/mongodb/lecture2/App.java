package com.mongodb.lecture2;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.lecture2.util.Helper;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by sdakhani on 3/26/16.
 */
public class App {
    public static void main(String[] args) {

        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(100).build();
        MongoClient client = new MongoClient(new ServerAddress("localhost",27017));
        MongoDatabase db= client.getDatabase("test");
        MongoCollection<Document> coll =db.getCollection("employee");

        Document document = new Document()
                            .append("name","Sami")
                            .append("age",32)
                            .append("long",1L)
                            .append("double",1.2)
                            .append("bool",true)
                            .append("date",new Date())
                            .append("objectID",new ObjectId())
                            .append("null",null)
                            .append("embededDocument",new Document("x",1))
                            .append("list", Arrays.asList(1,2,3));


        String name = document.getString("name");
        Integer  age = document.getInteger("age");

        BsonDocument bsonDoc = new BsonDocument()
                               .append("name",new BsonString("Sami"));

        Helper.printJson(document);
    }
}
