package edu.hw10.task1;

public class MyClass {
    String s;

    public MyClass(String s) {
        this.s = s;
    }

    public static MyClass create(String s) {
        return new MyClass(s);
    }
}
