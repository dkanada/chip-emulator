package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;

public class ClearInstruction implements Instruction {
    @Override
    public void execute(CPU cpu, char opcode) {
        // clear display
        cpu.display.setDisplay(new byte[64][32]);
        // callback
        cpu.event.updateDisplay(cpu.display.getDisplay());
        cpu.pc += 2;
    }
}
