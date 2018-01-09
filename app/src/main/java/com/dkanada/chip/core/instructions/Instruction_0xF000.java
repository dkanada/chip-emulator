package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;
import com.dkanada.chip.core.instructions.xF000.Instruction_0xF007;
import com.dkanada.chip.core.instructions.xF000.Instruction_0xF00A;
import com.dkanada.chip.core.instructions.xF000.Instruction_0xF015;
import com.dkanada.chip.core.instructions.xF000.Instruction_0xF018;
import com.dkanada.chip.core.instructions.xF000.Instruction_0xF01E;
import com.dkanada.chip.core.instructions.xF000.Instruction_0xF029;
import com.dkanada.chip.core.instructions.xF000.Instruction_0xF033;
import com.dkanada.chip.core.instructions.xF000.Instruction_0xF055;
import com.dkanada.chip.core.instructions.xF000.Instruction_0xF065;

import java.util.HashMap;
import java.util.Map;

public class Instruction_0xF000 implements Instruction {
    private Map<Integer, Instruction> map;

    public Instruction_0xF000() {
        map = new HashMap<>();
        map.put(0x000A, new Instruction_0xF00A());
        map.put(0x001E, new Instruction_0xF01E());
        map.put(0x0007, new Instruction_0xF007());
        map.put(0x0015, new Instruction_0xF015());
        map.put(0x0018, new Instruction_0xF018());
        map.put(0x0029, new Instruction_0xF029());
        map.put(0x0033, new Instruction_0xF033());
        map.put(0x0055, new Instruction_0xF055());
        map.put(0x0065, new Instruction_0xF065());
    }

    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        map.get(opcode.opcode & 0x00FF).execute(core, cpu, opcode);
    }
}
