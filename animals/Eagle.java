package org.example.animals;

import org.example.types.TypeAnimal;
import org.example.types.TypeMove;

public class Eagle extends Zoopark implements Animal{
    public Eagle(String animal, TypeMove typemove, TypeAnimal animalT) {
        super(animal, typemove, animalT);
    }
    @Override
    public void eat(String food) {
        if (food.equals("Meet")) {
            System.out.println(animal + " eat");
        } else {
            System.out.println(animal + " not eat");
        }
    }
    @Override
    public void move() {
        System.out.println("Eagle " + Typemove);
    }
}