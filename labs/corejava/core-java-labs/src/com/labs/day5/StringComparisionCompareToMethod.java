package com.labs.day5;
/*
compareTo() method return a positive number, negative number or 0
         if s1 > s2 return positive number
         if s1 < s2 return negative number
         if s1==s2 return 0
 */
public class StringComparisionCompareToMethod {
    public static void main(String[] args) {
        String str1 = "Siva";
        String str2 = "Siva";

        String str3 = new String("Siva");
        String str4 = "Parvathi";

        System.out.println(str1.compareTo(str2)); // returns 0
        System.out.println(str1.compareTo(str3)); // returns 0 , though str1 == str3 , it checks the value of the strings,
                                // equals() and compareTo()  have little bit logic inside the classes, character comparision
        System.out.println(str1.compareTo(str4)); // returns 3
    }
}
