package com.assignments.day1;
/*
Question 2: Write a program to sum first n even numbers using a while loop.
 */

import java.util.Scanner;

// Get input using Scanner and print the sum of the  n even numbers

class Question2SecondWay {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter n value");
        int n = scanner.nextInt();
        int sum = 0;
        int even = 2;

        for (int i = 1; i <= n; i++) {
            sum = sum + even;  // add the 1st even number 2
            even = even + 2;  // get next even number
        }
        System.out.println("The sum of first" + n + " numbers is : " + sum);

    }
}

// Print sum of the even numbers below 10
public class Question2 {
    public static void main(String[] args) {

        int n = 1;
        int sum = 0;

        while (n <= 10) {
            if ((n % 2) == 0)
                sum = sum + n;  // total of the even numbers (2,4,6,8,10) =  30
            n++;
        }
        System.out.println("Sum of n even numbers : " + sum);
    }
}