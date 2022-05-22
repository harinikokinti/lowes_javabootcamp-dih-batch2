package com.assignments.arrays;

// 1) Java Program to copy all elements of one array into another array

public class Question1 {
    public static void main(String[] args) {
        int sourceArray[] = {1, 2, 3, 4, 5};  // declare and initialise the array
        int destinationArray[] = new int[sourceArray.length];

        // copy using arraycopy
        System.arraycopy(sourceArray, 0, destinationArray, 0, destinationArray.length);
        System.out.println("Print the destination array after copying using arraycopy method ");
        for (int i : destinationArray) {
            System.out.println(i);
        }

        // copy using clone
        int destinationArray2[] = sourceArray.clone();
        System.out.println("Print the destination array 2 after copying using clone method ");
        for (int i : destinationArray2) {
            System.out.println(i);
        }

        // copy manually
        int destinationArray3[] = new int[sourceArray.length];
        for (int i = 0; i < sourceArray.length; i++) {
            destinationArray3[i] = sourceArray[i];
        }
        System.out.println("Print the destination array 3 after copying manually");
        for (int i : destinationArray3) {
            System.out.println(i);
        }

    }
}
