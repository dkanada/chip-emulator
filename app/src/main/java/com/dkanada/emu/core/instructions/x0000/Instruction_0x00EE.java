package com.dkanada.emu.core.instructions.x0000;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.Instruction;

public class Instruction_0x00EE implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        cpu.pc = core.memory.getWord((char) (cpu.sp - 2));
        cpu.sp -= 2;
        core.memory.setWord(cpu.sp, (char) 0x0);
    }
}
