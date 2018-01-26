package com.dkanada.emu.core.instructions;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.x0000.Instruction_0x00E0;
import com.dkanada.emu.core.instructions.x0000.Instruction_0x00EE;

import java.util.HashMap;
import java.util.Map;

public class Instruction_0x0000 implements Instruction {
    private Map<Integer, Instruction> map;

    public Instruction_0x0000() {
        map = new HashMap<>();
        map.put(0x00E0, new Instruction_0x00E0());
        map.put(0x00EE, new Instruction_0x00EE());
    }

    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        map.get(opcode.opcode & 0x00FF).execute(core, cpu, opcode);
    }
}
