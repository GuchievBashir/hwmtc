package types;

public enum TypeMove {
    SWIM, FLY, WALK;
    public static String getName(TypeMove move) {
        return move.toString();
    }
}