package com.assignments.arrays;

// 10) Java Program to print the smallest element in an array

public class Question10 {
    public static void main(String[] args) {
        int a[] = {50, 60, 20, 100, 90};

        int min = a[0];

        for (int i = 0; i < a.length; i++) {
            if (min > a[i]) {
                min = a[i];
            }
        }
        System.out.println("The Smallest number is : " + min);
    }
}
