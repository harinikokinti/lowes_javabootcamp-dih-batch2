package com.labs.java.day6;
/*
String Builder is also mutable string objecy
 */

public class StringBuilderExample {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder("Hello");
        stringBuilder.append("World");
        System.out.println(stringBuilder); // HelloWorld , same as String Buffer, mutable

    }

}
