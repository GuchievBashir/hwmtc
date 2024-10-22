package animals;

import types.TypeAnimal;
import types.TypeMove;

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