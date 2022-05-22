package com.assignments.arrays;
// 9) Java Program to print the largest element in an array

public class Question9 {
    public static void main(String[] args) {
        int a[] = {50, 60, 20, 100, 90};

        int max = a[0];

        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        System.out.println("The largest number is : " + max);
    }
}
