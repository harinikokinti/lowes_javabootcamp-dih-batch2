package com.labs.day5;

import java.util.Arrays;

public class StringSplit {
    public static void main(String[] args) {
        String str1 = "jhonny@gmail.com" ;

        // create a string array and assign the splitted strings
        String str2[] = str1.split("@"); // split if @ is seen
        System.out.println(Arrays.toString(str2));
    }
}
