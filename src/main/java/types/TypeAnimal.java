package types;

public enum TypeAnimal {
    Herbivorous, Predator;
    public static String getName(TypeAnimal animal) {
        return animal.toString();
    }
}