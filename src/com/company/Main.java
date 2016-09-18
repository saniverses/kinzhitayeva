package com.company;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Person p1 = new Person(true, "Adam");
        Person p2 = new Person(false, "Eva");
        Person p3 = new Person(true, "Sam");
        p1.marry(p2);
        p3.marry(p2);
    }
}
