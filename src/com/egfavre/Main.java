package com.egfavre;

import spark.ModelAndView;
import spark.Session;
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
                    HashMap m = new HashMap();
                    m.put("people", people);
                    return new ModelAndView(m, "home.html");
                },
                new MustacheTemplateEngine()
        );





    }
}
