package com.mongodb.lecture2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.bson.Document;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;

/**
 * Created by sdakhani on 3/27/16.
 */
public class SFMApp {
    public static void main(String[] args) {

        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(SFMApp.class,"/");

        MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase db = client.getDatabase("test");
        final MongoCollection<Document> collection = db.getCollection("people");
        collection.drop();

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {

                StringWriter writer = new StringWriter();
                try {

                    Template template = configuration.getTemplate("hello.ftl");

                    collection.insertOne(new Document("name","MongoDB"));
                    Document document = collection.find().first();

                    template.process(document,writer);

                }catch (Exception e){
                    halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }
}
