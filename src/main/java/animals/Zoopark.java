package animals;

import types.TypeFood;

public class Zoopark<T extends Animal>{
    public void present(T t) {
      System.out.print("Здесь обитает животное " + t.Typeanimal + " " +  t.getClass().getSimpleName() + " по имени " + t.animal + ". Тип питания для вида такого животного - " + t.typeFood + ". Тип передвижения - " + t.Typemove);
    }
}