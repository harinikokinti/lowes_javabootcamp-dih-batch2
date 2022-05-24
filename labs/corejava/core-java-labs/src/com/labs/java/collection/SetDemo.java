package com.labs.java.collection;
/*
Set allows duplicates, no ordering
HashSet:   Uses Hash technique
LinkedHashSet: sorts the data
TreeSet: sorts the data, The  objects must implement Comparable
 */

import java.util.*;

public class SetDemo {
    public static void main(String[] args) {
        // HashSet

        Set<String> countries = new HashSet<>();
        countries.add("India");
        countries.add("USA");
        countries.add("India");
        countries.add("Japan");
        countries.add("UK");
        countries.add(null);  // it allows null

        System.out.println("----- Hash Set----");
        for(String i : countries) {
            System.out.println(i);
        }

        // TreeSet
        Set<String>  countries2 = new TreeSet<>();
        countries2.add("India");
        countries2.add("USA");
        countries2.add("India");
        countries2.add("INDIA");
        countries2.add("Japan");
        countries2.add("UK");
        // countries2.add(null);  // treeset does not allow null, it throws nullpointerexception

        System.out.println("---- Tree Set---");
        for(String i : countries2) {
            System.out.println(i);  // sorted output
        }


        // Employee Set
        System.out.println("---Employee Set ------");
        Set<Employee> employees = new HashSet<>();
        employees.add(new Employee(1,"Ramu","21","Dev","IT","India"));
        employees.add(new Employee(2,"Seetha","25","Manager","IT","USA"));

        for(Employee e : employees) {
            System.out.println(e);  // it prints the employees but no order
        }
/*
        Comparator<Employee> ageComparator = new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {

            }
        }
*/

        System.out.println("---Employee LinkedHash Set & Tree Set ------");
       //Set<Employee> employees2 = new LinkedHashSet<>();  // sorts the data even without comparator
        Set<Employee> employees2 = new TreeSet<>();

        employees2.add(new Employee(1,"Ramu","21","Dev","IT","India"));
        employees2.add(new Employee(2,"Seetha","25","Manager","IT","USA"));

        for(Employee e : employees2) {
            System.out.println(e);  // it prints the employees in no order because of LinkedHashSet or Tree Set
        }

    }
}


