package com.dkanada.emu.core.instructions.x8000;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.Instruction;

public class Instruction_0x800E implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        if (core.quirkShift) {
            cpu.v[0xF] = (char) (cpu.v[opcode.getX()] >> 7);
            cpu.v[opcode.getX()] <<= 1;
        } else {
            cpu.v[0xF] = (char) (cpu.v[opcode.getY()] >> 7);
            cpu.v[opcode.getY()] <<= 1;
            cpu.v[opcode.getX()] = cpu.v[opcode.getY()];
        }
        cpu.pc += 2;
    }
}
