package com.mongodb.lecture2;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.orderBy;

/**
 * Created by sdakhani on 3/27/16.
 */
public class HomeWork23App {
    public static void main(String[] args) {

        MongoClient client = new MongoClient("localhost",27017);
        MongoDatabase db = client.getDatabase("students");
        MongoCollection<Document> collection = db.getCollection("grades");

        Bson sorts = orderBy(ascending("student_id","score"));

        List<Document> documents = collection
                                    .find()
                                    .sort(sorts)
                                    .into(new ArrayList<Document>());

        List<Document> updatedDocuments = new ArrayList<Document>();

        int previous_student_id=-1;
        int current_student_id=0;

        boolean homeworkDeleted = false;


        for (Document doc: documents) {

            current_student_id=(Integer) doc.get("student_id");


            if(previous_student_id != current_student_id) {
                previous_student_id = current_student_id;
                homeworkDeleted =false;
            }

            if(!homeworkDeleted){

                String examType = doc.getString("type");

                if(examType.equals("homework")){
                    homeworkDeleted = true;
                    updatedDocuments.add(doc);
                }
            }

        }



        for(Document doc : updatedDocuments) {

            ObjectId objectId =doc.getObjectId("_id");
            Bson filters = eq("_id",objectId);
            collection.deleteOne(filters);
        }


        System.out.println("Count");
        long count = collection.count();
        System.out.println(count);
    }
}
