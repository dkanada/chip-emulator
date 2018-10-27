package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;
import com.dkanada.chip.core.instructions.x8000.Instruction_0x8001;
import com.dkanada.chip.core.instructions.x8000.Instruction_0x8002;
import com.dkanada.chip.core.instructions.x8000.Instruction_0x8003;
import com.dkanada.chip.core.instructions.x8000.Instruction_0x8004;
import com.dkanada.chip.core.instructions.x8000.Instruction_0x8005;
import com.dkanada.chip.core.instructions.x8000.Instruction_0x8006;
import com.dkanada.chip.core.instructions.x8000.Instruction_0x8007;
import com.dkanada.chip.core.instructions.x8000.Instruction_0x800E;
import com.dkanada.chip.core.instructions.x8000.Instruction_0x8FF0;

import java.util.HashMap;
import java.util.Map;

public class Instruction_0x8000 implements Instruction {
    private Map<Integer, Instruction> map;

    public Instruction_0x8000() {
        map = new HashMap<>();
        map.put(0x0000, new Instruction_0x8FF0());
        map.put(0x000E, new Instruction_0x800E());
        map.put(0x0001, new Instruction_0x8001());
        map.put(0x0002, new Instruction_0x8002());
        map.put(0x0003, new Instruction_0x8003());
        map.put(0x0004, new Instruction_0x8004());
        map.put(0x0005, new Instruction_0x8005());
        map.put(0x0006, new Instruction_0x8006());
        map.put(0x0007, new Instruction_0x8007());
    }

    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        map.get(opcode.opcode & 0x000F).execute(core, cpu, opcode);
    }
}
