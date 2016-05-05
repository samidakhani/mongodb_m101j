package com.mongodb.lecture1;

import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sdakhani on 3/19/16.
 */
public class SparkFreemarkerApp {

    public static void main(String[] args){

        final Configuration config = new Configuration();
        config.setClassForTemplateLoading(SparkFreemarkerApp.class,"/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {

                StringWriter writer = new StringWriter();
                try{

                    Template template=config.getTemplate("hello.ftl");
                    Map<String,Object> map = new HashMap<String, Object>();
                    map.put("name","Sami");
                    template.process(map,writer);

                }catch(Exception e){
                    halt(500);
                    e.printStackTrace();
                }
                return writer;
            }
        });
    }
}
