package com.labs.java.day8;
/*

 */

public class RuntimeException {
    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        int c = 0;

        try {
            c = a/b;
        }
        catch(ArithmeticException e) {
            System.out.println("Arithmetic Exception ");
        }

        System.out.println(c);
    }
}
