package com.mongodb.lecture1;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sdakhani on 3/19/16.
 */
public class FreeMarkerApp {
    public static void main(String[] args){
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(FreeMarkerApp.class,"/");

        try{

            Template helloTemplate=configuration.getTemplate("hello.ftl");
            StringWriter writer = new StringWriter();
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("name","Sami");

            helloTemplate.process(map,writer);
            System.out.println(writer);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
