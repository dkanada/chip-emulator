package com.dkanada.chip.core.instructions.x8000;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;
import com.dkanada.chip.core.instructions.Instruction;

public class Instruction_0x8006 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        cpu.v[0xF] = (char) (cpu.v[opcode.getY()] & 0x000F);
        cpu.v[opcode.getY()] >>= 1;
        cpu.v[opcode.getX()] = cpu.v[opcode.getY()];
        cpu.pc += 2;
    }
}
