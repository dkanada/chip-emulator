package com.dkanada.chip.core.instructions.xE000;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;
import com.dkanada.chip.core.instructions.Instruction;

public class Instruction_0xE0A1 implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        cpu.pc += core.keypad.getKey() != cpu.v[opcode.getX()] ? 4 : 2;
    }
}
