package com.dkanada.chip.core.instructions.xF000;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;
import com.dkanada.chip.core.instructions.Instruction;

public class Instruction_0xF055 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        for (int x = 0; x <= opcode.getX(); x++) {
            char address = core.quirkRegister ? (char) (cpu.index + x) : cpu.index++;
            core.memory.setByte(address, cpu.v[x]);
        }
        cpu.pc += 2;
    }
}
