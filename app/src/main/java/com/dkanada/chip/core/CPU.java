package com.dkanada.chip.core;

import java.util.Random;

public class CPU {
    public Core core;

    public char[] v;

    public char pc;
    public char sp;
    public char index;

    public CPU(Core core) {
        this.core = core;

        v = new char[16];

        pc = 0x200;
        sp = 0x000;
        index = 0x200;
    }

    public void cycle() {
        char opcode = core.memory.getWord(pc);
        switch (opcode & 0xF000) {
            case 0x0000:
                switch (opcode & 0x000F) {
                    case 0x0000:
                        core.display.setDisplay(new byte[64][32]);
                        core.updateDisplay(core.display.getDisplay());
                        pc += 2;
                        break;
                    case 0x000E:
                        pc = core.memory.getWord((char) (sp - 2));
                        sp -= 2;
                        core.memory.setWord(sp, (char) 0x0);
                        break;
                }
                break;
            case 0x1000:
                pc = getNNN(opcode);
                break;
            case 0x2000:
                core.memory.setWord(sp, (char) (pc + 2));
                sp += 2;
                pc = getNNN(opcode);
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
                if (v[getX(opcode)] == v[getY(opcode)]) {
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
                        // set v[f] = 1 if carry
                        if (add > 255) {
                            v[0xF] = 1;
                        } else {
                            v[0xF] = 0;
                        }
                        v[getX(opcode)] = (char) add;
                        v[getX(opcode)] &= 0xFF;
                        pc += 2;
                        break;
                    case 0x0005:
                        int subtract = v[getX(opcode)] - v[getY(opcode)];
                        // set v[f] = 1 if not borrow
                        if (subtract < 0) {
                            v[0xF] = 0;
                        } else {
                            v[0xF] = 1;
                        }
                        v[getX(opcode)] = (char) subtract;
                        v[getX(opcode)] &= 0xFF;
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
                        // set v[f] = 1 if not borrow
                        if (subInverse < 0) {
                            v[0xF] = 0;
                        } else {
                            v[0xF] = 1;
                        }
                        v[getX(opcode)] = (char) subInverse;
                        v[getX(opcode)] &= 0xFF;
                        pc += 2;
                        break;
                    case 0x000E:
                        v[0xF] = (char) (v[getY(opcode)] >> 7);
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
                index = getNNN(opcode);
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
                char startX = v[getX(opcode)];
                char startY = v[getY(opcode)];
                v[0xF] = 0;

                for (int y = 0; y < getN(opcode); y++) {
                    char line = core.memory.getByte((char) (index + y));
                    for (int x = 0; x < 8; x++) {
                        char pixel = (char) (line & (0x80 >> x));
                        if (pixel != 0) {
                            if (core.display.getPixel((startX + x) % 64, (startY + y) % 32) != 0) {
                                v[0xF] = 1;
                            }
                            core.display.setPixel((startX + x) % 64, (startY + y) % 32, (byte) 1);
                        }
                    }
                }
                core.updateDisplay(core.display.getDisplay());
                pc += 2;
                break;
            case 0xE000:
                switch (opcode & 0x00FF) {
                    case 0x009E:
                        pc += core.keypad.getKey() == v[getX(opcode)] ? 4 : 2;
                        break;
                    case 0x00A1:
                        pc += core.keypad.getKey() != v[getX(opcode)] ? 4 : 2;
                        break;
                }
                break;
            case 0xF000:
                switch (opcode & 0x00FF) {
                    case 0x0007:
                        v[getX(opcode)] = core.delay;
                        pc += 2;
                        break;
                    case 0x000A:
                        if (core.keypad.getKey() != 1000) {
                            v[getX(opcode)] = core.keypad.getKey();
                            pc += 2;
                        }
                        break;
                    case 0x0015:
                        core.delay = v[getX(opcode)];
                        pc += 2;
                        break;
                    case 0x0018:
                        core.sound = v[getX(opcode)];
                        pc += 2;
                        break;
                    case 0x001E:
                        index += v[getX(opcode)];
                        pc += 2;
                        break;
                    case 0x0029:
                        char offset = (char) (v[getX(opcode)] * 0x5);
                        index = (char) (core.memory.getFontAddress() + offset);
                        pc += 2;
                        break;
                    case 0x0033:
                        int num = v[getX(opcode)];
                        for (int i = 2; i >= 0; i--) {
                            core.memory.setByte((char) (index + i), (char) (num % 10));
                            num /= 10;
                        }
                        pc += 2;
                        break;
                    case 0x0055:
                        for (int x = 0; x <= getX(opcode); x++) {
                            core.memory.setByte(index++, v[x]);
                        }
                        pc += 2;
                        break;
                    case 0x0065:
                        for (int x = 0; x <= getX(opcode); x++) {
                            v[x] = core.memory.getByte(index++);
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
}
