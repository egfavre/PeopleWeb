package com.egfavre;

/**
 * Created by user on 6/8/16.
 */
public class Person {
    //id,first_name,last_name,email,country,ip_address
    String id;
    String firstName;
    String lastName;
    String email;
    String counry;
    String ipAddress;

    public Person(String id, String firstName, String lastName, String email, String counry, String ipAddress) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.counry = counry;
        this.ipAddress = ipAddress;
    }
}
