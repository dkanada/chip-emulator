package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;

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
