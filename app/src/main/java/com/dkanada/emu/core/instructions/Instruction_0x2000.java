package com.dkanada.emu.core.instructions;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;

public class Instruction_0x2000 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        core.memory.setWord(cpu.sp, (char) (cpu.pc + 2));
        cpu.sp += 2;
        cpu.pc = opcode.getNNN();
    }
}
