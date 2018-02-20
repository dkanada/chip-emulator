package com.dkanada.chip.core.instructions.x8000;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;
import com.dkanada.chip.core.instructions.Instruction;

public class Instruction_0x8002 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        cpu.v[opcode.getX()] = (char) (cpu.v[opcode.getX()] & cpu.v[opcode.getY()]);
        cpu.pc += 2;
    }
}
