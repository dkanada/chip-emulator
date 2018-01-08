package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;

public class SkipNotEqualVxNNInstruction implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        cpu.pc += cpu.v[opcode.getX()] != opcode.getNN() ? 4 : 2;
    }
}
