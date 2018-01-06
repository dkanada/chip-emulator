package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;

public interface Instruction {
    void execute(CPU cpu, char opcode);
}
