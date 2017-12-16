package com.dkanada.chip.core;

public class Memory {
    private char fontAddress = 0x050;
    private char programAddress = 0x200;

    private char[] memory;

    public char getFontAddress() {
        return fontAddress;
    }

    public char getProgramAddress() {
        return programAddress;
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
}
