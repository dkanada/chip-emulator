package com.dkanada.chip.core;

import android.os.Environment;
import android.util.Log;

import com.dkanada.chip.interfaces.EventListener;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Core {
    private Memory memoryCore;
    private Display displayCore;
    private Keypad keypadCore;

    private CPU cpuCore;

    public Core(EventListener eventListener) {
        memoryCore = new Memory();
        displayCore = new Display();
        keypadCore = new Keypad();

        cpuCore = new CPU(memoryCore, displayCore, keypadCore, eventListener);

        loadFont();
        loadProgram(Environment.getExternalStorageDirectory().toString() + "/Download/c8games/BRIX");
    }

    public void step() {
        cpuCore.cycle();
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
            Log.e("core.loadFile: ", "file not found");
        } catch (IOException e) {
            Log.e("core.loadFile: ", "error reading file data");
        }
    }
}
