package com.mongodb.lecture1;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

/**
 * Created by sdakhani on 3/19/16.
 */
public class SparkGetApp {

    public static void main(String[] args){

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello Sami";
            }
        });

        Spark.get(new Route("/test") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello Test";
            }
        });

        Spark.get(new Route("/echo/:thing") {
            @Override
            public Object handle(Request request, Response response) {
                return request.params(":thing");
            }
        });
    }
}
