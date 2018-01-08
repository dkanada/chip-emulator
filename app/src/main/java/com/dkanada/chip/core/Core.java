package com.dkanada.chip.core;

import android.util.Log;

import com.dkanada.chip.interfaces.DisplayListener;
import com.dkanada.chip.interfaces.KeypadListener;
import com.dkanada.chip.views.DisplayView;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Core extends Thread implements DisplayListener, KeypadListener {
    private DisplayView displayView;
    private boolean load;

    public Memory memory;
    public Display display;
    public Keypad keypad;
    public CPU cpu;

    public char delay;
    public char sound;

    public Core(DisplayView displayView) {
        this.displayView = displayView;
        reset();
    }

    public void reset() {
        load = false;

        memory = new Memory();
        display = new Display();
        keypad = new Keypad();

        cpu = new CPU(this);

        initTimer();
    }

    public void step() {
        cpu.cycle();
    }

    public void load(String file) {
        reset();

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

        char address = memory.getFontAddress();
        for (char value : font) {
            memory.setByte(address, value);
            address++;
        }
    }

    public void loadProgram(String file) {
        FileInputStream fileInputStream;
        DataInputStream dataInputStream;

        try {
            fileInputStream = new FileInputStream(file);
            dataInputStream = new DataInputStream(fileInputStream);

            char address = memory.getProgramAddress();
            while (dataInputStream.available() > 0) {
                memory.setByte(address, (char) dataInputStream.readByte());
                address++;
            }
        } catch (FileNotFoundException e) {
            Log.e("Core.loadFile", "file not found :: " + file);
        } catch (IOException e) {
            Log.e("Core.loadFile", "error reading file :: " + file);
        }
    }

    public void initTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                decrementTimer();
            }
        }, 60, 60);
    }

    public void decrementTimer() {
        if (delay != 0) {
            delay--;
        }
        if (sound != 0) {
            sound--;
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
        keypad.setKey(key);
    }

    @Override
    public void keyUp(char key) {
        keypad.setKey((char) 1000);
    }
}
