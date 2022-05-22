package com.assignments.arrays;

// 14) Java Program to sort the elements of an array in ascending order

public class Question14 {
    public static void main(String[] args) {
        int a[] = {40, 60, 30, 50, 20, 10};
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

        System.out.println("Print the array in Ascending Order: ");

        for (int i : a) {
            System.out.print(i + " ");
        }
    }
}
