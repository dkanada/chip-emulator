package com.dkanada.emu.core.instructions.xF000;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.Instruction;

public class Instruction_0xF029 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        char offset = (char) (cpu.v[opcode.getX()] * 0x5);
        cpu.index = (char) (core.memory.getFontAddress() + offset);
        cpu.pc += 2;
    }
}
