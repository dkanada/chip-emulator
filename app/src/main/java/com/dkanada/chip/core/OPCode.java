package com.dkanada.chip.core;

public class OPCode {
    public char opcode;

    public OPCode(char value) {
        opcode = value;
    }

    public char getNNN() {
        return (char) (opcode & 0x0FFF);
    }

    public char getNN() {
        return (char) (opcode & 0x00FF);
    }

    public char getN() {
        return (char) (opcode & 0x000F);
    }

    public char getX() {
        return (char) ((opcode & 0x0F00) >> 8);
    }

    public char getY() {
        return (char) ((opcode & 0x00F0) >> 4);
    }
}
