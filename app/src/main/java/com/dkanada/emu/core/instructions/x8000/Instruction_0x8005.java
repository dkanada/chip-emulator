package com.dkanada.emu.core.instructions.x8000;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.Instruction;

public class Instruction_0x8005 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        int result = cpu.v[opcode.getX()] - cpu.v[opcode.getY()];
        // set v[f] = 0 if borrow
        if (result < 0) {
            cpu.v[0xF] = 0;
        } else {
            cpu.v[0xF] = 1;
        }
        cpu.v[opcode.getX()] = (char) result;
        cpu.v[opcode.getX()] &= 0xFF;
        cpu.pc += 2;
    }
}
