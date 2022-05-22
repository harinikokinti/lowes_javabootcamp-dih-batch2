package com.assignments.arrays;

// 6) Java Program to print the elements of an array in reverse order

public class Question6 {
    public static void main(String[] args) {
        // Reersing an integer array
        int a[] = {1, 2, 3, 4, 5};
        for (int i = a.length - 1; i >= 0; i--) { // decrement the counter and print in reverse
            System.out.print(a[i] + " ");
        }
    }
}


