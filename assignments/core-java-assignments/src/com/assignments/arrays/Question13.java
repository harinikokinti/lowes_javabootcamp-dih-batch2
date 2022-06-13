package com.assignments.arrays;

// 13) Java Program to right rotate the elements of an array

import java.util.Scanner;

public class Question13 {
    public static void main(String[] args) {
        int array[] = {1, 2, 3, 4, 5};
        System.out.println("The Array is : ");
        for (int i : array) {
            System.out.print(i + " ");
        }

        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter the number of times to right rotate the Array: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++)    // rotate for n times
        {
            int j;
            int lastElement = array[array.length-1];
            for (j = array.length-1; j > 0; j--) {
                array[j] = array[j-1];   // shift by one position at the end of the array
            }
            array[j] = lastElement;
        }

        System.out.println("The Array after right rotation  : ");
        for (int i : array) {
            System.out.print(i + " ");
        }

    }
}
