package com.mongodb.lecture1;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by sdakhani on 3/19/16.
 */
public class SparkApp {

    public static void main(String[] args){
        Spark.get(new Route("/"){
            @Override
            public Object handle(final Request request, final Response response){
                 return "Welcome to Spark";
            }
        });
    }
}
