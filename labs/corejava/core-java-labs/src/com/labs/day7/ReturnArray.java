package com.labs.day7;

public class ReturnArray {
    static int[] getArray() {
        return new int[]{10,20,30,40};
    }
    public static void main(String[] args) {
        int a[] = getArray();

        for(int i=0;i<a.length;i++) {
            System.out.println(a[i]);
        }
    }
}

