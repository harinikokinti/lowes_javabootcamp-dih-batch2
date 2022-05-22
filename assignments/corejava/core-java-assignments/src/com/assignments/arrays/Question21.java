package com.assignments.arrays;

// 21) Remove Duplicate Element in an Array

public class Question21 {
    public static void main(String[] args) {
        int a[] = {10, 20, 20, 30, 30, 40, 50};
        int n = a.length;

        int j = 0;
        int count;

        for (int i = 0; i < n - 1; i++) {
            if (a[i] != a[i + 1]) {  // check if the element is duplicated
                a[j++] = a[i];   // if not duplicated, add into the array wiht new index
            }
        }

        a[j++] = a[n - 1]; // adds the last element of the array, no need to check since it is th last one
        count = j;

        for (int i = 0; i < count; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
