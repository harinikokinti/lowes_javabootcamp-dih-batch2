package com.assignments.arrays;
// 3) Java Program to left rotate the elements of an array

import java.util.Scanner;

public class Question3 {
    public static void main(String[] args) {
        int array[] = {1, 2, 3, 4, 5};
        System.out.println("The Array is : ");
        for (int i : array) {
            System.out.print(i + " ");
        }

        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter the number of times to left rotate the Array: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++)    // rotate for n times
        {
            int j;
            int firstElement = array[0];
            for (j = 0; j < array.length - 1; j++) {
                array[j] = array[j + 1];   // shift by one position
            }
            array[j] = firstElement;
        }

        System.out.println("The Array after left rotation  : ");
        for (int i : array) {
            System.out.print(i + " ");
        }

    }
}
