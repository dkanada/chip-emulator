package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;

public class CallSubroutineInstruction implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        core.memory.setWord(cpu.sp, (char) (cpu.pc + 2));
        cpu.sp += 2;
        cpu.pc = opcode.getNNN();
    }
}
