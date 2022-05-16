package com.assignments.day1;
/*
Question 4: Write a program to print a multiplication table of 10 in reverse order.
 */

public class Question4 {
    public static void main(String[] args) {
        int n = 10;
        for (int j = 10; j >= 1; j--) {
            System.out.println(n + "*" + j + "=" + n * j); // print the table in reverse order by decrementing the counter
        }
    }
}
