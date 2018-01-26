package com.dkanada.emu.core.instructions;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;

import java.util.Random;

public class Instruction_0xC000 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        Random random = new Random();
        int r = random.nextInt(255);
        cpu.v[opcode.getX()] = (char) (r & opcode.getNN());
        cpu.pc += 2;
    }
}
