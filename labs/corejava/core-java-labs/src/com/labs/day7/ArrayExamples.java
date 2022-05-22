package com.labs.day7;
/*
copyarray
clonearray
compare array

 */

// copyArray

public class ArrayExamples {
    public static void main(String[] args) {

        int sourceArray[] = {5,6,7,8};
        int destinationArray[] = new int[sourceArray.length];

        System.arraycopy(sourceArray, 0, destinationArray, 0,destinationArray.length);

        for(int i : destinationArray) {
            System.out.println(i);   // 5,6,7,8
        }

        // copy from diff positon
        System.out.println("In diff position");
        System.arraycopy(sourceArray, 1, destinationArray, 0,destinationArray.length-1 );

        for(int i : destinationArray) {
            System.out.println(i);
        }

    }
}

// clone an array
class  CloneArray {
    public static void main(String[] args) {

        int array[] = {10,20,30,40};

        System.out.println("print original array");
        for(int i: array) {
            System.out.println(i);
        }

        int newArray[] = array.clone();

        System.out.println("print clone array");
        for(int i: newArray) {
            System.out.println(i);
        }

        System.out.println(array == newArray); // compare and returns false, because == operator compares the reference not the values
                                        // cloned array refers diff reference ans hence returns false

        System.out.println(array.equals(newArray)); // true

    }
}



// Array Example

class ArrayExample2 {
    public static void main(String[] args) {
        int a[] = {1,2,3,4,5};
        a[0] = 3;

        for(int i: a) {
            System.out.println(i);  //  3,2,3,4,5   ( arrays are mutable, changes its value)
        }
    }
}

