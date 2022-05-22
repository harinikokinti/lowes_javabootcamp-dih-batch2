package com.assignments.arrays;

// 17) Find 2nd Largest Number in an Array

public class Question17 {
    public static void main(String[] args) {
        int a[] = {40, 60, 30, 50, 20, 10};

        // First sort out the Array
        int temp;
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }

        System.out.println("The 2nd largest number in the Array is : " + a[a.length-2]);  //50
    }
}
