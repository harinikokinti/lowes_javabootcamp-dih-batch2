package com.assignments.arrays;

// 7) Java Program to print the elements of an array present on even position
public class Question7 {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5, 6};
        System.out.println("Print the array elements at even position");

        for (int i = 0; i < a.length; i++) {
            if ((i % 2) != 0) {
                System.out.print(a[i] + " ");
            }
        }

    }
}

// Method 2

class Question7Method2 {
    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5, 6};
        System.out.println("Print the array elements at even position");

        for (int i = 1; i < a.length; i = i + 2) {
            System.out.print(a[i] + " ");
        }

    }
}