package org.example.animals;

import org.example.types.TypeAnimal;
import org.example.types.TypeMove;

public class Dolphin extends Zoopark implements Animal{
    public Dolphin(String animal, TypeMove typemove, TypeAnimal animalT) {
        super(animal, typemove, animalT);
    }
    @Override
    public void eat(String food) {
        if (food.equals("Fish")) {
            System.out.println(animal + " eat");
        } else {
            System.out.println(animal + " not eat");
        }
    }
    @Override
    public void move() {
        System.out.println("Dolphin " + Typemove);
    }
}