package com.dkanada.chip.core;

public class Display {
    private byte[][] display;

    public Display() {
        display = new byte[64][32];
    }

    public byte[][] getDisplay() {
        return display;
    }

    public void clearDisplay() {
        display = new byte[64][32];
    }

    public byte getPixel(int x, int y) {
        return display[x][y];
    }

    public void setPixel(int x, int y, byte value) {
        display[x][y] = value;
    }
}
