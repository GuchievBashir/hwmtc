package org.example.animals;

import org.example.types.TypeAnimal;
import org.example.types.TypeMove;

public class Zoopark {
    protected String animal;
    protected String Typemove;
    protected String Typeanimal;
    public Zoopark(String animal, TypeMove typemove, TypeAnimal animalT) {
        this.animal = animal;
        this.Typeanimal = TypeAnimal.getName(animalT);
        this.Typemove = TypeMove.getName(typemove);
    }
}