package com.dkanada.emu.core;

public class Register {
    private char[] v;

    private char pc;
    private char sp;
    private char index;

    public Register() {
        v = new char[16];

        pc = 0x200;
        sp = 0x000;
        index = 0x200;
    }

    public char getRegister(char index) {
        return v[index];
    }

    public void setRegister(char index, char value) {
        v[index] = (char) (value & 0xFF);
    }

    public char getPC() {
        return pc;
    }

    public void setPC(char value) {
        pc = (char) (value & 0xFFF);
    }

    public char getSP() {
        return sp;
    }

    public void setSP(char value) {
        sp = (char) (value & 0xFFF);
    }

    public char getIndex() {
        return index;
    }

    public void setIndex(char value) {
        index = (char) (value & 0xFFF);
    }
}
