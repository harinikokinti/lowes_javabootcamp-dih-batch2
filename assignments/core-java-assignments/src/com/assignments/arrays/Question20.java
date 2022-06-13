package com.assignments.arrays;

// 20) Find Smallest Number in an Array

public class Question20 {
    public static void main(String[] args) {
        int a[] = {40, 60, 30, 50, 20, 10};

        int min = a[0];

        for (int i = 0; i < a.length; i++) {
            if (min > a[i]) {
                min = a[i];
            }
        }
        System.out.println("The smallest number is : " + min); // 10
    }
}
