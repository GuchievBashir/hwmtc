package animals;

import types.TypeAnimal;
import types.TypeMove;

public class Horse extends Zoopark implements Animal{
    public Horse(String animal, TypeMove typemove, TypeAnimal animalT) {
        super(animal, typemove, animalT);
    }
    @Override
    public void eat(String food) {
        if (food.equals("Grass")) {
            System.out.println(animal + " eat");
        } else {
            System.out.println(animal + " not eat");
        }
    }
    @Override
    public void move() {
        System.out.println("Horse " + Typemove);
    }
}