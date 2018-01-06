package com.dkanada.chip.core;

import android.util.Log;

import com.dkanada.chip.interfaces.DisplayListener;
import com.dkanada.chip.interfaces.KeypadListener;
import com.dkanada.chip.views.DisplayView;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Core extends Thread implements DisplayListener, KeypadListener {
    private Memory memoryCore;
    private Display displayCore;
    private Keypad keypadCore;
    private CPU cpuCore;

    private DisplayView displayView;

    private int delay;
    private int sound;

    public boolean load;
    public int time;

    public Core(DisplayView display) {
        displayView = display;

        reset();
    }

    public void reset() {
        load = false;

        memoryCore = new Memory();
        displayCore = new Display();
        keypadCore = new Keypad();

        cpuCore = new CPU(memoryCore, displayCore, keypadCore, this);
    }

    public void step() {
        cpuCore.cycle();
    }

    public void load(String file) {
        if (load) {
            reset();
        }

        loadFont();
        loadProgram(file);

        load = true;
    }

    public void loadFont() {
        char[] font = {
                0xF0, 0x90, 0x90, 0x90, 0xF0,
                0x20, 0x60, 0x20, 0x20, 0x70,
                0xF0, 0x10, 0xF0, 0x80, 0xF0,
                0xF0, 0x10, 0xF0, 0x10, 0xF0,
                0x90, 0X90, 0xF0, 0x10, 0x10,
                0xF0, 0x80, 0xF0, 0x10, 0xF0,
                0xF0, 0x80, 0xF0, 0x90, 0xF0,
                0xF0, 0x10, 0x20, 0x40, 0x40,
                0xF0, 0x90, 0xF0, 0x90, 0xF0,
                0xF0, 0x90, 0xF0, 0x10, 0xF0,
                0xF0, 0x90, 0xF0, 0x90, 0x90,
                0xE0, 0x90, 0xE0, 0x90, 0xE0,
                0xF0, 0x80, 0x80, 0x80, 0xF0,
                0xE0, 0x90, 0x90, 0x90, 0xE0,
                0xF0, 0x80, 0xF0, 0x80, 0xF0,
                0xF0, 0x80, 0xF0, 0x80, 0x80
        };

        char address = memoryCore.getFontAddress();
        for (char value : font) {
            memoryCore.setByte(address, value);
            address++;
        }
    }

    public void loadProgram(String file) {
        FileInputStream fileInputStream;
        DataInputStream dataInputStream;

        try {
            fileInputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(fileInputStream);

            char address = memoryCore.getProgramAddress();
            while (dataInputStream.available() > 0) {
                memoryCore.setByte(address, (char) dataInputStream.readByte());
                address++;
            }
        } catch (FileNotFoundException e) {
            Log.e("Core.loadFile", "file not found :: " + file);
        } catch (IOException e) {
            Log.e("Core.loadFile", "error reading file :: " + file);
        }
    }

    @Override
    public void run() {
        while (true) {
            if (load) {
                step();
            }
            try {
                sleep(4);
            } catch (Exception e) {
                // nothing
            }
        }
    }

    @Override
    public void updateDisplay(byte[][] array) {
        displayView.setDisplay(array);
        displayView.postInvalidate();
    }

    @Override
    public void keyDown(char key) {
        keypadCore.setKey(key);
    }

    @Override
    public void keyUp(char key) {
        keypadCore.setKey((char) 1000);
    }
}
