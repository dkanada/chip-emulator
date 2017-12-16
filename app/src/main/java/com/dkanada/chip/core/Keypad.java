package com.dkanada.chip.core;

public class Keypad {
    private char[] keypad;

    public Keypad() {
        keypad = new char[16];
    }

    public char getKey(char index) {
        return keypad[index];
    }

    public void setKey(char index, char value) {
        keypad[index] = value;
    }
}
