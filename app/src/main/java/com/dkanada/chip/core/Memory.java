package com.dkanada.chip.core;

public class Memory {

    private char[] memory;

    public char getFontAddress() {
        return (char) 0x050;
    }

    public char getProgramAddress() {
        return (char) 0x200;
    }

    public Memory() {
        memory = new char[4096];
    }

    public char getByte(char address) {
        return memory[address];
    }

    public void setByte(char address, char value) {
        memory[address] = (char) (value & 0xFF);
    }

    public char getWord(char address) {
        return (char) (memory[address] << 8 | memory[address + 1]);
    }

    public void setWord(char address, char value) {
        memory[address] = (char) ((value & 0xFF00) >> 8);
        memory[address + 1] = (char) (value & 0x00FF);
    }
}
