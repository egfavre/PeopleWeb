package com.egfavre;

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

            int i = 0;
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] columns = line.split(",");
                Person person = new Person(columns[0], columns[1], columns[2], columns[3], columns[4], columns[5]);
                people.add(i, person);
                i++;
            }


    }
}
