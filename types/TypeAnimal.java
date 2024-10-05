package org.example.types;

public enum TypeAnimal {
    Herbivorous, Predator;
    static String getName(TypeAnimal animal) {
        return animal.toString();
    }
}