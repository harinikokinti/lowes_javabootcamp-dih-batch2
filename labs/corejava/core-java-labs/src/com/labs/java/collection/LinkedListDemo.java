package com.labs.java.collection;
/*
Linked list for faster manipulation of objects(insertion/deleetion) compared to ArrayList
It is same as ArrayList
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LinkedListDemo {
    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();

        list.add(100);
        list.add(200);
        list.add(300);
        list.add(400);
        list.add(500);
        System.out.println("Array List elements with generics");
        for(Object item: list) {
            System.out.println(item);
        }
        // remove element
        list.remove(2);  // remove at the index
        System.out.println("AFter removal");
        for(Object item: list) {
            System.out.println(item);
        }

        // update element
        list.set(1,1000);  // update at given index
        System.out.println("After updation");
        for(Object item: list) {
            System.out.println(item);
        }

        System.out.println("Using Iterator"); // the pointer moves only in 1 direction , forward
        Iterator<Integer> itr = list.iterator();
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }

    }
}
