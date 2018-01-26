package com.dkanada.emu.core.instructions.x0000;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.Instruction;

public class Instruction_0x00E0 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        core.display.setDisplay(new byte[64][32]);
        core.updateDisplay(core.display.getDisplay());
        cpu.pc += 2;
    }
}
