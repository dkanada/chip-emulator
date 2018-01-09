package com.dkanada.chip.core;

import com.dkanada.chip.core.instructions.Instruction;
import com.dkanada.chip.core.instructions.Instruction_0x0000;
import com.dkanada.chip.core.instructions.Instruction_0x1000;
import com.dkanada.chip.core.instructions.Instruction_0x2000;
import com.dkanada.chip.core.instructions.Instruction_0x3000;
import com.dkanada.chip.core.instructions.Instruction_0x4000;
import com.dkanada.chip.core.instructions.Instruction_0x5000;
import com.dkanada.chip.core.instructions.Instruction_0x6000;
import com.dkanada.chip.core.instructions.Instruction_0x7000;
import com.dkanada.chip.core.instructions.Instruction_0x8000;
import com.dkanada.chip.core.instructions.Instruction_0x9000;
import com.dkanada.chip.core.instructions.Instruction_0xA000;
import com.dkanada.chip.core.instructions.Instruction_0xB000;
import com.dkanada.chip.core.instructions.Instruction_0xC000;
import com.dkanada.chip.core.instructions.Instruction_0xD000;
import com.dkanada.chip.core.instructions.Instruction_0xE000;
import com.dkanada.chip.core.instructions.Instruction_0xF000;

import java.util.HashMap;
import java.util.Map;

public class CPU {
    private Map<Integer, Instruction> map;
    private Core core;

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

        map = new HashMap<>();
        map.put(0x0000, new Instruction_0x0000());
        map.put(0x1000, new Instruction_0x1000());
        map.put(0x2000, new Instruction_0x2000());
        map.put(0x3000, new Instruction_0x3000());
        map.put(0x4000, new Instruction_0x4000());
        map.put(0x5000, new Instruction_0x5000());
        map.put(0x6000, new Instruction_0x6000());
        map.put(0x7000, new Instruction_0x7000());
        map.put(0x8000, new Instruction_0x8000());
        map.put(0x9000, new Instruction_0x9000());
        map.put(0xA000, new Instruction_0xA000());
        map.put(0xB000, new Instruction_0xB000());
        map.put(0xC000, new Instruction_0xC000());
        map.put(0xD000, new Instruction_0xD000());
        map.put(0xE000, new Instruction_0xE000());
        map.put(0xF000, new Instruction_0xF000());
    }

    public void cycle() {
        OPCode opcode = new OPCode(core.memory.getWord(pc));
        map.get(opcode.opcode & 0xF000).execute(core, this, opcode);
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
