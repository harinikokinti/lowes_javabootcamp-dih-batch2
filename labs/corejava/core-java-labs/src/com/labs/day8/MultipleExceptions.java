package com.labs.day8;


import java.io.IOException;

class Test3 {
    public void foo()  throws ArithmeticException, IOException, NullPointerException {


    }
}


public class MultipleExceptions {
    public static void main(String[] args) throws IOException {
        Test3 test = new Test3();
        test.foo();

    }
}
