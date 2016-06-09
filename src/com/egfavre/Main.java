package com.egfavre;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
	// import Json and Spark libraries
    //Parse people.csv to ArrayList<Person>
        File f = new File("people.csv");
        Scanner fileScanner = new Scanner(f);
        ArrayList<Person> people = new ArrayList<>();

        fileScanner.nextLine();
        while (fileScanner.hasNextLine()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split(",");
            Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
            people.add(person);
        }

    //create GET route that will display all of the names
        Spark.init();

        Spark.get(
                "/",
                (request, response) -> {
                    int offset = 0;
                    String offsetStr = request.queryParams("offset");


                    if (offsetStr != null) {
                        offset = Integer.valueOf(offsetStr);
                    }

                    int next = offset + 20;
                    int prev = offset - 20;
                    boolean previousCondition = false;
                    boolean nextCondition = false;
                    if(offset > 0) {
                        previousCondition = true;
                    }
                    if(offset < people.size()-20){
                        nextCondition = true;
                    }
//...
                    HashMap m = new HashMap();
                    ArrayList<Person> temp = new ArrayList<Person>(people.subList(offset, 20+offset));
                    m.put("people", temp);
                    m.put("offset", offset);
                    m.put("next", next);
                    m.put("prev", prev);
                    m.put("previousCondition", previousCondition);
                    m.put("nextCondition", nextCondition);

                    return new ModelAndView(m, "home.html");
                },
                new MustacheTemplateEngine()
        );

        Spark.get(
                "/person",
                (request, response) -> {
                    HashMap p = new HashMap();
                    int id = Integer.valueOf(request.queryParams("id"));
                    Person selection = people.get(id);
                    p.put("selection", selection);

                    return new ModelAndView(p, "person.html");
                },
                new MustacheTemplateEngine()
        );
    }
}
