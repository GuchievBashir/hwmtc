package org.example.types;

public enum TypeMove {
    swim, fly, walk;
    static String getName(TypeMove move) {
        return move.toString();
    }
}