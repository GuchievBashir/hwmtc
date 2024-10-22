package types;

public enum TypeMove {
    swim, fly, walk;
    public static String getName(TypeMove move) {
        return move.toString();
    }
}