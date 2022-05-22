package com.labs.day7;
/*
Wrapperclasses provide mechnishm to convert primitive type  into object type  and object to primitvie

boxing  / autoboxing   : automatic converison of primitive datatype into its corresponding Wrapper Class object  (int to Integer)
unboxing : automatic converison of wrapper type intoits corresponding primitive type (Integer to int)

primitive   Wrapper
boolean     Boolean
 */
public class WrapperClass {
    public static void main(String[] args) {
        int x = 10;
        Integer y = x;  // autoboxing, primitive -> wrapper class (automatic)

        // y.intValue();  // access Wrapper class methods
       // y.compareTo(x)

       Float a = 20f;
       float b = a; // unboxing , wrapper class --> primitive

    }
}


