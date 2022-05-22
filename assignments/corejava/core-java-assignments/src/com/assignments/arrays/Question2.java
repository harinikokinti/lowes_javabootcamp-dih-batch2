package com.assignments.arrays;

// 2) Java Program to find the frequency of each element in the array

public class Question2 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 3, 4, 5, 3};  // declare and initialise array
        int[] frequency = new int[arr.length];  // frequncy array
        int visited = -1;
        for (int i = 0; i < arr.length; i++) {
            int count = 1;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    count++;
                    frequency[j] = visited; //skips the element to count again
                }
            }
            if (frequency[i] != visited)
                frequency[i] = count;   // set the count value into the frequency array for every calculated iteration
        }

        System.out.println(" ArrayElement   Frequency  ");  //Prints the frequency of each element present in array
        for (int i = 0; i < frequency.length; i++) {
            if (frequency[i] != visited)
                System.out.println("       " + arr[i] + "           " + frequency[i]);
        }

    }
}






