package com.dkanada.emu.core.instructions.xF000;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.Instruction;

public class Instruction_0xF065 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        for (int x = 0; x <= opcode.getX(); x++) {
            char address = core.quirkRegister ? (char) (cpu.index + x) : cpu.index++;
            cpu.v[x] = core.memory.getByte(address);
        }
        cpu.pc += 2;
    }
}
