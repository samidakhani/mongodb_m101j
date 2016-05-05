package com.mongodb.lecture3;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by sdakhani on 4/3/16.
 */
public class HomeWork31 {

    public static void main(String[] args) {

        MongoClient client = new MongoClient("localhost", 27017);
        MongoDatabase db = client.getDatabase("school");
        MongoCollection<Document> collection = db.getCollection("students");

        List<Document> documents = collection
                .find()
                .into(new ArrayList<Document>());

        for (Document doc : documents) {

            List<Document> scoreList = (List<Document>) doc.get("scores");
            List<Document> updatedScoreList = new ArrayList<Document>();

            Document homework1 = new Document();
            Document homework2 = new Document();
            Boolean homework1IsSet = false;

            for (Document scoreObj : scoreList) {

                if(scoreObj.getString("type").contains("exam")  || scoreObj.getString("type").contains("quiz")){
                    updatedScoreList.add(scoreObj);
                }else if(!homework1IsSet){
                    homework1IsSet =true;
                    homework1 = scoreObj;
                }else {
                    homework2 = scoreObj;
                }
            }

            if(homework1.getDouble("score") > homework2.getDouble("score")){
                  updatedScoreList.add(homework1);
            }else{
                updatedScoreList.add(homework2);
            }

            Integer objectId =(Integer) doc.get("_id");
            Bson filters = eq("_id",objectId);
            Bson update = new Document("$set", new Document("scores", updatedScoreList));
            collection.updateOne(filters,update);
           }

        }
    }
