package com.labs.day6;

public class ArrayExample2 {
    public static void main(String[] args) {
        int a[] = {1,2,3,4,5,6,7} ; // declaration , instatntiation and intialization

        for(int i=0; i<a.length; i++) {
            System.out.println(a[i]);
        }

        for(int number : a) {
            System.out.println(number);
        }
    }
}
