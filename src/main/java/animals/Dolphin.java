package animals;

import types.*;

public class Dolphin extends Animal implements AnimalActions{
    public Dolphin(String animal, TypeMove typemove, TypeAnimal animalT) {
        super(animal, typemove, animalT);
    }
    @Override
    public void eat(TypeFood food) {
        if (food.equals(TypeFood.FISH)) {
            System.out.println(animal + " eat");
            typeFood = food;
        } else {
            System.out.println(animal + " not eat");
        }
    }
    @Override
    public void move() {
        System.out.println("Dolphin " + Typemove);
    }
}