package org.example;

import org.example.animals.*;
import org.example.types.*;

public class Main {
  public static void main(String[] args) {
    Eagle eagle = new Eagle("Eagle", TypeMove.swim, TypeAnimal.Herbivorous);
    eagle.eat("Meet");
    eagle.move();
    Tiger tiger = new Tiger("Tiger", TypeMove.walk, TypeAnimal.Predator);
    tiger.eat("Beef");
    tiger.move();
  }
}
