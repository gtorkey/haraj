package org.example;

import com.github.javafaker.Faker;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Faker fake = new Faker();
        System.out.println(fake.internet().password(6,10,false,false,false));
    }
}