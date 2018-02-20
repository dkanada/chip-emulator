package com.dkanada.chip.core.instructions.xF000;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;
import com.dkanada.chip.core.instructions.Instruction;

public class Instruction_0xF029 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        char offset = (char) (cpu.v[opcode.getX()] * 0x5);
        cpu.index = (char) (core.memory.getFontAddress() + offset);
        cpu.pc += 2;
    }
}
