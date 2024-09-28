enum TypeAnimal {
  Herbivorous, Predator;
  static String getName(TypeAnimal animal) {
    return animal.toString();
  }
}

enum TypeMove {
  swim, fly, walk;
  static String getName(TypeMove move) {
    return move.toString();
  }
}

class Zoopark {
  protected String animal;
  protected String Typemove;
  protected String Typeanimal;
  public Zoopark(String animal, TypeMove typemove, TypeAnimal animalT) {
    this.animal = animal;
    this.Typeanimal = TypeAnimal.getName(animalT);
    this.Typemove = TypeMove.getName(typemove);
  }
}

interface Animal{
  void eat(String food);
  void move();
}

class Horse extends Zoopark implements Animal{
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

class Tiger extends Zoopark implements Animal{
  public Tiger(String animal, TypeMove typemove, TypeAnimal animalT) {
    super(animal, typemove, animalT);
  }
  @Override
  public void eat(String food) {
    if (food.equals("Beef")) {
      System.out.println(animal + " eat");
    } else {
      System.out.println(animal + " not eat");
    }
  }
  @Override
  public void move() {
    System.out.println("Tiger " + Typemove);
  }
}

class Dolphin extends Zoopark implements Animal{
  public Dolphin(String animal, TypeMove typemove, TypeAnimal animalT) {
    super(animal, typemove, animalT);
  }
  @Override
  public void eat(String food) {
    if (food.equals("Fish")) {
      System.out.println(animal + " eat");
    } else {
      System.out.println(animal + " not eat");
    }
  }
  @Override
  public void move() {
    System.out.println("Dolphin " + Typemove);
  }
}

class Eagle extends Zoopark implements Animal{
  public Eagle(String animal, TypeMove typemove, TypeAnimal animalT) {
    super(animal, typemove, animalT);
  }
  @Override
  public void eat(String food) {
    if (food.equals("Meet")) {
      System.out.println(animal + " eat");
    } else {
      System.out.println(animal + " not eat");
    }
  }
  @Override
  public void move() {
    System.out.println("Eagle " + Typemove);
  }
}

class Camel extends Zoopark implements Animal{
  public Camel(String animal, TypeMove typemove, TypeAnimal animalT) {
    super(animal, typemove, animalT);
  }
  @Override
  public void eat(String food) {
    if (food.equals("Meet")) {
      System.out.println(animal + " eat");
    } else {
      System.out.println(animal + " not eat");
    }
  }
  @Override
  public void move() {
    System.out.println("Camel " + Typemove);
  }
}

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