package com.mongodb.finalexam;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by sdakhani on 4/30/16.
 */
public class Problem7 {
    public static void main(String[] args) throws Exception{

        MongoClient c =  new MongoClient();
        MongoDatabase db = c.getDatabase("test");
        MongoCollection<Document> albums = db.getCollection("albums");
        MongoCollection<Document> images = db.getCollection("images");

        MongoCursor<Document> cur = images.find().iterator();

        while(cur.hasNext()){
           Document imageDoc= cur.next();
            Integer imageId =(Integer) imageDoc.get("_id");
            List<Document> albumDocs = albums.find(eq("images",imageId)).into(new ArrayList<Document>());
            if(albumDocs.size()==0){
                images.deleteOne(eq("_id",imageId));
            }

        }
    }
}
