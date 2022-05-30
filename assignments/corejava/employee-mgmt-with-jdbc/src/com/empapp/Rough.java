package com.empapp;

public class Rough {
    public static void main(String[] args) {
        var value = 5.083333333333333;

        var valueTruncated =  Math.floor(value * Math.pow(10, 2)) / Math.pow(10, 2);
        System.out.println(valueTruncated);
    }
}
