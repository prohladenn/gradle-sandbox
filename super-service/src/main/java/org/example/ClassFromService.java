package org.example;

public class ClassFromService {
    private final ClassFromLibrary classFromLibrary;

    public ClassFromService(int value) {
        this.classFromLibrary = new ClassFromLibrary(value);
    }

    public int getValue() {
        return classFromLibrary.value();
    }
}
