package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;

public class Instruction_0x1000 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        cpu.pc = opcode.getNNN();
    }
}
