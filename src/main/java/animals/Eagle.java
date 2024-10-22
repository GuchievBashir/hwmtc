package animals;

import types.*;

public class Eagle extends Animal implements AnimalActions{
    public Eagle(String animal, TypeMove typemove, TypeAnimal animalT) {
        super(animal, typemove, animalT);
    }
    @Override
    public void eat(TypeFood food) {
        if (food.equals(TypeFood.MEET)) {
            System.out.println(animal + " eat");
            typeFood = food;
        } else {
            System.out.println(animal + " not eat");
        }
    }
    @Override
    public void move() {
        System.out.println("Eagle " + Typemove);
    }
}