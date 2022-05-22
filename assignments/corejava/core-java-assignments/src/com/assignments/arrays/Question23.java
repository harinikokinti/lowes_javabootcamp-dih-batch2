package com.assignments.arrays;

// 23) Multiply Two Matrices

public class Question23 {
    public static void main(String[] args) {
        // create 2 matrices
        int a[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int b[][] = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        // create a 2d array to store the result
        int c[][] = new int[3][3];

        System.out.println("The Multiplcation of two matrices: ");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                c[i][j] = 0;
                for (int k = 0; k < 3; k++) {
                    c[i][j] = c[i][j] + (a[i][k] * b[k][j]);
                }
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
    }
}
