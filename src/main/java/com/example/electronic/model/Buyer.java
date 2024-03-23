package com.example.electronic.model;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Buyer {
    private int id;
    private String name;
    private int age;
    private java.sql.Date dateOfBirthday;
    private Date registrationDate;


    public Buyer(String name, int age, Date date) {
        this.name = name;
        this.age = age;
        this.dateOfBirthday = (java.sql.Date) date;
    }

    public Buyer(int id, String name, int age, java.sql.Date dateOfBirthday) {
        this.name = name;
        this.age = age;
        this.dateOfBirthday = dateOfBirthday;
    }
}
