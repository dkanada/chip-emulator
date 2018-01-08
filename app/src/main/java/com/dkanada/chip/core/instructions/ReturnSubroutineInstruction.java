package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;

public class ReturnSubroutineInstruction implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        cpu.pc = core.memory.getWord((char) (cpu.sp - 2));
        cpu.sp -= 2;
        core.memory.setWord(cpu.sp, (char) 0x0);
    }
}
