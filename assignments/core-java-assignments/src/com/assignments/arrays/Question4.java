package com.assignments.arrays;

// 4) Java Program to print the duplicate elements of an array

import java.util.Arrays;

public class Question4 {
    public static void main(String[] args) {
        int a[] = {3, 4, 5, 3, 4, 2};
        for (int i = 0; i < a.length; i++)
        {
            for (int j = i + 1; j < a.length; j++)
            {
                if (a[i] == a[j]) {
                    System.out.println(a[j]);  // print the duplicate element from the array
                }
            }
        }
    }
}


