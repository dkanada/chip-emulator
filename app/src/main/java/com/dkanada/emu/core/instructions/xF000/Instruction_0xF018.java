package com.dkanada.emu.core.instructions.xF000;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.Instruction;

public class Instruction_0xF018 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        core.sound = cpu.v[opcode.getX()];
        cpu.pc += 2;
    }
}
