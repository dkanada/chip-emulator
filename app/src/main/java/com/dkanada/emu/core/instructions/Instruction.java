package com.dkanada.emu.core.instructions;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;

public interface Instruction {
    void execute(Core core, CPU cpu, OPCode opcode);
}
