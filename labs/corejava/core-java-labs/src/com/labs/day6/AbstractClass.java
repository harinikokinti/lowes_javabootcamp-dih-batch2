package com.labs.day6;
/*
The abstract class forces the implementation classes to implement the abstract methods present in it.
Hence if any specific mandatory behaviours exist, abstract class can be used to force the user to implement it
This can be achieved using Interfaces too. Interface is more generic and precise.

This class is used when common features are shared by all object.

Why to use Abstract class?
If there are any behaviours or features common across the classes, instead of defining them multiple times,
we have abstract class which declares that feature as abstract method and forces all the implementation classes to implement that
feature .

When to use Abstract class and When to use Interfaces?
If the requirement has class with members having differenct access modifiers, no need multiple inheritance , need of abstraction ,
sharing common features, need of constructor, then  we can go for abstract class
If the requirement has, class with no members, very high level generic functionality, no constructor,
need of multiple inheritance, then we can go for Inheritance

 */

abstract class Shape{
    double length;
    double breadth;
    double radius;
    double side;

    public abstract void area();
}

class Square extends Shape {
    public Square(double side) {
        this.side = side;
    }

    public void area() {  // area must be  calculated and implemented
        System.out.println("The Area of the Sqaure is : " + (side * side));
    }

    public void shapeName() {
        System.out.println("This is a Square");
    }


}


class Rectangle extends Shape {
    public Rectangle(double length, double breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public void area() {
        System.out.println("The Area of the Rectangle is : " + (length * breadth));
    }

    public void shapeName() {
        System.out.println("This is a Rectangle");
    }
}



public class AbstractClass {
    public static void main(String[] args) {

        Square squareObj = new Square(45.55);
        squareObj.area();
        squareObj.shapeName();

        Shape shape = new Rectangle(20,40);
        shape.area();
    }
}
