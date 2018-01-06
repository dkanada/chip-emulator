package com.dkanada.chip.core;

public class Keypad {
    private int[] keypad;

    public Keypad() {
        keypad = new int[16];
    }

    public char getKey(char index) {
        return (char) keypad[index];
    }

    public void setKey(char index, int value) {
        keypad[index] = value;
    }
}
