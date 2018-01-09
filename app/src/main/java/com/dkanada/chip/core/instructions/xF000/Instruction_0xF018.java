package com.dkanada.chip.core.instructions.xF000;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;
import com.dkanada.chip.core.instructions.Instruction;

public class Instruction_0xF018 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        core.sound = cpu.v[opcode.getX()];
        cpu.pc += 2;
    }
}
