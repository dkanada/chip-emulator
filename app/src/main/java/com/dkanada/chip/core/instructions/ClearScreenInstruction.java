package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;

public class ClearScreenInstruction implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        core.display.setDisplay(new byte[64][32]);
        core.updateDisplay(core.display.getDisplay());
        cpu.pc += 2;
    }
}
