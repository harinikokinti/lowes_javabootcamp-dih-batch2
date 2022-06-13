package com.assignments.arrays;

// 18) Find Largest Number in an Array

public class Question18 {
    public static void main(String[] args) {
        int a[] = {40, 60, 30, 50, 20, 10};

        int max = a[0];

        for (int i = 0; i < a.length; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        System.out.println("The largest number is : " + max); // 60
    }

}
