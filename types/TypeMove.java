package org.example.types;

enum TypeMove {
    swim, fly, walk;
    static String getName(TypeMove move) {
        return move.toString();
    }
}