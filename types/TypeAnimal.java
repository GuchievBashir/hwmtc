package org.example.types;

enum TypeAnimal {
    Herbivorous, Predator;
    static String getName(TypeAnimal animal) {
        return animal.toString();
    }
}