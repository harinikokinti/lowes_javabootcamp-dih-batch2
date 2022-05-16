package com.assignments.day1;

/*
Question 5: Write a program to find the factorial of a given number using for loops.
Question 6: Repeat problem 5 using a while loop.
 */
public class Question6 {
    public static void main(String[] args) {
        int fact = 1;
        int n = 5;
        while (n > 1) {
            fact = fact * n;
            n--;
        }
        System.out.println("The factorial of the number 5 is : " + fact); // 120
    }
}
