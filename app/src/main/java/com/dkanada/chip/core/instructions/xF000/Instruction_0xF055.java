package com.dkanada.chip.core.instructions.xF000;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;
import com.dkanada.chip.core.instructions.Instruction;

public class Instruction_0xF055 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        for (int x = 0; x <= opcode.getX(); x++) {
            core.memory.setByte(cpu.index++, cpu.v[x]);
        }
        cpu.pc += 2;
    }
}
