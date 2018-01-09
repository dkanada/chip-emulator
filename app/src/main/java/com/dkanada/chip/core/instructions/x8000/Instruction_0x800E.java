package com.dkanada.chip.core.instructions.x8000;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;
import com.dkanada.chip.core.instructions.Instruction;

public class Instruction_0x800E implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        cpu.v[0xF] = (char) (cpu.v[opcode.getY()] >> 7);
        cpu.v[opcode.getY()] <<= 1;
        cpu.v[opcode.getX()] = cpu.v[opcode.getY()];
        cpu.pc += 2;
    }
}
