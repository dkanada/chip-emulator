package com.dkanada.emu.core.instructions.xF000;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.Instruction;

public class Instruction_0xF033 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        int num = cpu.v[opcode.getX()];
        for (int i = 2; i >= 0; i--) {
            core.memory.setByte((char) (cpu.index + i), (char) (num % 10));
            num /= 10;
        }
        cpu.pc += 2;
    }
}
