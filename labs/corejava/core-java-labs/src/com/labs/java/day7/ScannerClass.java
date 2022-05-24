package com.labs.java.day7;

import java.util.Scanner;

public class ScannerClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter First name and Last name: ");
        String firstName = sc.next();
        String lastName = sc.next();
        System.out.println("Full Name: " + firstName + " " + lastName);
    }
}


