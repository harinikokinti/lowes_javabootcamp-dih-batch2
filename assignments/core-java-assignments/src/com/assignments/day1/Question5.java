package com.assignments.day1;

/*
Question 5: Write a program to find the factorial of a given number using for loops.
 */
public class Question5 {
    public static void main(String[] args) {
        int fact = 1;
        int n = 5;
        for (int i = n; i > 1; i--) {
            fact = fact * i;
        }
        System.out.println("The Factorial of 5 is : " + fact); // 5*4*3*2*1 =  120
    }
}
