package com.dkanada.chip.core;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import com.dkanada.chip.interfaces.EventListener;

public class CPU {
    private Memory memory;
    private Display display;
    private Keypad keypad;
    private EventListener event;

    private char[] v;

    private char pc;
    private char sp;
    private char i;

    private char delay;
    private char sound;

    public CPU(Memory memoryCore, Display displayCore, Keypad keypadCore, EventListener eventListener) {
        memory = memoryCore;
        display = displayCore;
        keypad = keypadCore;
        event = eventListener;

        v = new char[16];

        pc = 0x200;
        sp = 0x000;
        i = 0x200;

        sound = 0;
        delay = 0;

        initTimer();
    }

    public void cycle() {
        char opcode = memory.getWord(pc);
        switch (opcode & 0xF000) {
            case 0x0000:
                switch (opcode & 0x000F) {
                    case 0x0000:
                        // clear display
                        display.clearDisplay();
                        // callback
                        event.updateDisplay(display.getDisplay());
                        pc += 2;
                        break;
                    case 0x000E:
                        // return from subroutine
                        pc = memory.getWord(sp);
                        sp -= 2;
                        break;
                }
                break;
            case 0x1000:
                // jump to address
                pc = getNNN(opcode);
                break;
            case 0x2000:
                // call subroutine
                pc = getNNN(opcode);
                memory.setByte(sp++, (char) ((getNNN(opcode) & 0xFF00) >> 8));
                memory.setByte(sp++, (char) (getNNN(opcode) & 0x00FF));
                break;
            case 0x3000:
                pc += 2;
                if (v[getX(opcode)] == getNN(opcode)) {
                    pc += 2;
                }
                break;
            case 0x4000:
                pc += 2;
                if (v[getX(opcode)] != getNN(opcode)) {
                    pc += 2;
                }
                break;
            case 0x5000:
                pc += 2;
                if (v[getX(opcode)] != v[getY(opcode)]) {
                    pc += 2;
                }
                break;
            case 0x6000:
                v[getX(opcode)] = getNN(opcode);
                pc += 2;
                break;
            case 0x7000:
                v[getX(opcode)] += getNN(opcode);
                pc += 2;
                break;
            case 0x8000:
                switch (opcode & 0x000F) {
                    case 0x0000:
                        v[getX(opcode)] = v[getY(opcode)];
                        pc += 2;
                        break;
                    case 0x0001:
                        v[getX(opcode)] = (char) (v[getX(opcode)] | v[getY(opcode)]);
                        pc += 2;
                        break;
                    case 0x0002:
                        v[getX(opcode)] = (char) (v[getX(opcode)] & v[getY(opcode)]);
                        pc += 2;
                        break;
                    case 0x0003:
                        v[getX(opcode)] = (char) (v[getX(opcode)] ^ v[getY(opcode)]);
                        pc += 2;
                        break;
                    case 0x0004:
                        int add = v[getX(opcode)] + v[getY(opcode)];
                        if (add > 255) {
                            v[0xF] = 1;
                        } else {
                            v[0xF] = 0;
                        }
                        v[getX(opcode)] = (char) add;
                        pc += 2;
                        break;
                    case 0x0005:
                        int subtract = v[getX(opcode)] - v[getY(opcode)];
                        if (subtract < 0) {
                            v[0xF] = 1;
                        } else {
                            v[0xF] = 0;
                        }
                        v[getX(opcode)] = (char) subtract;
                        pc += 2;
                        break;
                    case 0x0006:
                        v[0xF] = (char) (v[getY(opcode)] & 0x000F);
                        v[getY(opcode)] >>= 1;
                        v[getX(opcode)] = v[getY(opcode)];
                        pc += 2;
                        break;
                    case 0x0007:
                        int subInverse = v[getY(opcode)] - v[getX(opcode)];
                        if (subInverse < 0) {
                            v[0xF] = 0;
                        } else {
                            v[0xF] = 1;
                        }
                        v[getX(opcode)] = (char) subInverse;
                        pc += 2;
                        break;
                    case 0x000E:
                        v[0xF] = (char) (v[getY(opcode)] & 0xF00);
                        v[getY(opcode)] <<= 1;
                        v[getX(opcode)] = v[getY(opcode)];
                        pc += 2;
                        break;
                }
                break;
            case 0x9000:
                pc += 2;
                if (v[getX(opcode)] != v[getY(opcode)]) {
                    pc += 2;
                }
                break;
            case 0xA000:
                i = getNNN(opcode);
                pc += 2;
                break;
            case 0xB000:
                pc = (char) (v[0] + getNNN(opcode));
                break;
            case 0xC000:
                Random random = new Random();
                int r = random.nextInt(255);
                v[getX(opcode)] = (char) (r & getNNN(opcode));
                pc += 2;
                break;
            case 0xD000:
                char startX = getX(getX(opcode));
                char startY = getX(getX(opcode));
                for (int n = 0; n < getN(opcode); n++) {
                    char line = memory.getByte((char) (i + n));
                    for (int inc = 0; inc < 8; inc++) {
                        if (display.getPixel(startX + inc, startY + n) == 1) {
                            v[0xF] = 1;
                        } else {
                            v[0xF] = 0;
                        }
                        char pixel = (char) (line & (0x80 >> inc));
                        if (pixel != 0) {
                            display.setPixel(startX + inc, startY + n, (byte) 1);
                        } else {
                            display.setPixel(startX + inc, startY + n, (byte) 0);
                        }
                    }
                }
                // callback
                event.updateDisplay(display.getDisplay());
                pc += 2;
                break;
            case 0xE000:
                switch (opcode & 0x00FF) {
                    case 0x009E:
                        if (keypad.getKey(v[getX(opcode)]) == 1) {
                            pc += 2;
                        }
                        pc += 2;
                        break;
                    case 0x00A1:
                        if (keypad.getKey(v[getX(opcode)]) == 0) {
                            pc += 2;
                        }
                        pc += 2;
                        break;
                }
                break;
            case 0xF000:
                switch (opcode & 0x00FF) {
                    case 0x0007:
                        v[getX(opcode)] = delay;
                        break;
                    case 0x000A:
                        // TODO
                        break;
                    case 0x0015:
                        delay = v[getX(opcode)];
                        break;
                    case 0x0018:
                        sound = v[getX(opcode)];
                        break;
                    case 0x001E:
                        i += v[getX(opcode)];
                        break;
                    case 0x0029:
                        // TODO
                        break;
                    case 0x0033:
                        // TODO
                        break;
                    case 0x0055:
                        // dump registers
                        for (int x = 0; x <= (opcode & 0x0F00); x++) {
                            memory.setByte(i++, v[x]);
                        }
                        pc += 2;
                        break;
                    case 0x0065:
                        // load registers
                        for (int x = 0; x <= (opcode & 0x0F00); x++) {
                            v[x] = memory.getByte(i++);
                        }
                        pc += 2;
                        break;
                }
                break;
        }
    }

    public char getNNN(char opcode) {
        return (char) (opcode & 0x0FFF);
    }

    public char getNN(char opcode) {
        return (char) (opcode & 0x00FF);
    }

    public char getN(char opcode) {
        return (char) (opcode & 0x000F);
    }

    public char getX(char opcode) {
        return (char) ((opcode & 0x0F00) >> 8);
    }

    public char getY(char opcode) {
        return (char) ((opcode & 0x00F0) >> 4);
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
}
