package org.example;

import animals.*;
import types.*;

public class Main {
  public static void main(String[] args) {
    Eagle eagle = new Eagle("Eagle", TypeMove.SWIM, TypeAnimal.HERBIVOROUS);
    eagle.eat(TypeFood.MEET);
    eagle.move();
    Tiger tiger = new Tiger("Timmy", TypeMove.WALK, TypeAnimal.PREDATOR);
    tiger.eat(TypeFood.BEEF);
    tiger.move();
    Zoopark<Tiger> ValerTiger = new Zoopark<>();
    ValerTiger.present(tiger);
  }
}
