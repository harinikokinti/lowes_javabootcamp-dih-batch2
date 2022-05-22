package com.assignments.arrays;
// 5) Java Program to print the elements of an array

public class Question5 {
    public static void main(String[] args) {
        int array[] = {1,2,3,4,5};
        char charArray[] = {'A', 'B', 'C', 'D', 'E'};

        System.out.println("Print the Array of Integers");
        for(int i=0; i<array.length; i++) {
            System.out.print(array[i] + " ");
        }

        System.out.println();
        System.out.println("Print the Array of Characters");
        for(int i=0; i<charArray.length; i++) {
            System.out.print(charArray[i] + " ");
        }

        // print using for each
        System.out.println();
        System.out.println("Print the Array of integers using foreach");
        for(int i: array) {
            System.out.print(i + " ");
        }

        System.out.println();
        System.out.println("Print the Array of characters using foreach");
        for(char c: charArray) {
            System.out.print(c + " ");
        }
    }
}
