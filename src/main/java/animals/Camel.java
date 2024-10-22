package animals;

import types.*;


public class Camel extends Animal implements AnimalActions{
    public Camel(String animal, TypeMove typemove, TypeAnimal animalT) {
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
        System.out.println("Camel " + Typemove);
    }
}