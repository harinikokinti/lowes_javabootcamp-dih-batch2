package com.assignments.day1;
/*
Question 9: Write a program to calculate the sum of the numbers occurring in the multiplication table of 8.
 */

public class Question9 {
    public static void main(String[] args) {

        int n = 8;
        int sum = 0;

        for (int i = 1; i <= 10; i++) {
            sum = sum + (n * i);   // 8+16+24+32+40+48+56+64+72+80   =  440
        }
        System.out.println("The sum of the numbers occurring in the multiplication table 8 is : " + sum);

    }
}
