package com.dkanada.emu.core.instructions.xF000;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.Instruction;

public class Instruction_0xF00A implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        if (core.keypad.getKey() != 1000) {
            cpu.v[opcode.getX()] = core.keypad.getKey();
            cpu.pc += 2;
        }
    }
}
