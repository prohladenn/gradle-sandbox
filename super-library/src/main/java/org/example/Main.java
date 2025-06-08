package org.example;

public class Main {
    public static void main(String[] args) {
        var classFromLibrary = new ClassFromLibrary(42);
        System.out.println("Hello and welcome! Value from library: " + classFromLibrary.value());
    }
}