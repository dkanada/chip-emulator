package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;

public class Instruction_0x7000 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        cpu.v[opcode.getX()] += opcode.getNN();
        cpu.v[opcode.getX()] &= 0xFF;
        cpu.pc += 2;
    }
}
