package org.example;

public class Main {
    public static void main(String[] args) {
        var classFromService = new ClassFromService(42);
        System.out.println("Hello and welcome! Value from library: " + classFromService.getValue());
    }
}