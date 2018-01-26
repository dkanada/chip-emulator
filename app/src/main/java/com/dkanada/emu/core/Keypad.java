package com.dkanada.emu.core;

public class Keypad {
    private char key;

    public Keypad() {
        key = 1000;
    }

    public char getKey() {
        return key;
    }

    public void setKey(char index) {
        key = index;
    }
}
