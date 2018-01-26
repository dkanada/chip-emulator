package com.dkanada.emu.core.instructions;

import com.dkanada.emu.core.CPU;
import com.dkanada.emu.core.Core;
import com.dkanada.emu.core.OPCode;
import com.dkanada.emu.core.instructions.xE000.Instruction_0xE0A1;
import com.dkanada.emu.core.instructions.xE000.Instruction_0xE09E;

import java.util.HashMap;
import java.util.Map;

public class Instruction_0xE000 implements Instruction {
    private Map<Integer, Instruction> map;

    public Instruction_0xE000() {
        map = new HashMap<>();
        map.put(0x00A1, new Instruction_0xE0A1());
        map.put(0x009E, new Instruction_0xE09E());
    }

    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        map.get(opcode.opcode & 0x00FF).execute(core, cpu, opcode);
    }
}
