package com.labs.day6;
/*
Arrays: to store similar type of data with fixed length
Advantage:
1. code optimization
2. random access  (since arrays are index based)
3.type safe : can save specific type of values

Disadvantage:
1. fixed size

Types of Arrays:
1.Single Dimensional array
2. multi dimensional array

Syntax:
int[] numbers;
int numbers[];
int []numbers;
 */

public class ArraysExample {
    public static void main(String[] args) {
        int a[] = new int[3]; // declaration and instantiation
        a[0] = 10;  // initialization
        a[1] = 20;
        a[2] = 30;
        //a[3] = 40;  gives ArrayIndexOutOfBoundsException

        for(int i=0; i<a.length; i++) {
            System.out.println(a[i]);
        }
    }
}


