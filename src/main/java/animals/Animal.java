package animals;

import types.*;

public class Animal {
    protected String animal;
    protected String Typemove;
    protected String Typeanimal;
    public TypeFood typeFood;
    public Animal(String animal, TypeMove typemove, TypeAnimal animalT) {
        this.animal = animal;
        this.Typeanimal = TypeAnimal.getName(animalT);
        this.Typemove = TypeMove.getName(typemove);
    }
}
