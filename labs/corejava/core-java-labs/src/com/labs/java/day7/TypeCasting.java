package com.labs.java.day7;
/*
typeCasting is a process to convert a datatype into another datatype

It can be done in 2 types
1. manually (explicit or narrowing type casting)  , done by the programmer,
hihger ---> lower
double -> float -> long -> int -> char -> short -> byte.
2. automatically (implicit or widening type casting)  , done by the compiler , to convert lower to higher
lower  ---> higher
byte -> short -> char -> int -> long -> float -> double.



 */

public class TypeCasting {
    public static void main(String[] args) {
        // implicit or widening or automatic type casting
        int a = 10;
        long b = a;
        double c = b;

        System.out.println(a + " " + b + " " + c );  // 10  10  10.0

        // explicit or narrowing or manual type casting
        double p = 50.50;
        long q = (long)p; // converting double into long explicitly
        int r = (int) q; // converting long into int explicitly

        System.out.println(p + " " + q + " " + r );// 50.5   50   50

    }
}
