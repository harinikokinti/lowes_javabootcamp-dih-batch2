package com.labs.java.day7;

import java.util.Arrays;

/*
the min or max of numbers in an array are calculated baesd on the array index
 */
public class PassArraytoMethod {
    static void minNumber(int array[]){
        int min = array[0];
        for(int i=0; i<array.length; i++) {
            if(min > array[i]) {
                min = array[i];
            }
        }
        System.out.println(min);
    }

    static void minNumberMethod2(int array[]) {
        Arrays.sort(array);
        System.out.println(array[0]);
    }

    public static void main(String[] args) {

        int a[] = {10,6,22,56};
        minNumber(a);  // using index
        minNumberMethod2(a); // 1st sort the array using Arrays.sort() and return the 1st value

    }
}


