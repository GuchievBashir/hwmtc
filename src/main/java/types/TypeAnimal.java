package types;

public enum TypeAnimal {
    HERBIVOROUS, PREDATOR;
    public static String getName(TypeAnimal animal) {
        return animal.toString();
    }
}