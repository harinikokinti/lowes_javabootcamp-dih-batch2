package com.assignments.arrays;

// 12) Java Program to print the sum of all the items of the array

public class Question12 {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5};

        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum = sum + a[i];
        }
        System.out.println("The sum of the array elements: " + sum);
    }
}
