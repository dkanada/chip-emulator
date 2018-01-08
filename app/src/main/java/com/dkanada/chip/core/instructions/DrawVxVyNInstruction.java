package com.dkanada.chip.core.instructions;

import com.dkanada.chip.core.CPU;
import com.dkanada.chip.core.Core;
import com.dkanada.chip.core.OPCode;

public class DrawVxVyNInstruction implements Instruction {
    @Override
    public void execute(Core core, CPU cpu, OPCode opcode) {
        char startX = cpu.v[opcode.getX()];
        char startY = cpu.v[opcode.getY()];
        cpu.v[0xF] = 0;

        for (int y = 0; y < opcode.getN(); y++) {
            char line = core.memory.getByte((char) (cpu.index + y));
            for (int x = 0; x < 8; x++) {
                char pixel = (char) (line & (0x80 >> x));
                if (pixel != 0) {
                    if (core.display.getPixel((startX + x) % 64, (startY + y) % 32) != 0) {
                        cpu.v[0xF] = 1;
                    }
                    core.display.setPixel((startX + x) % 64, (startY + y) % 32, (byte) 1);
                }
            }
        }
        core.updateDisplay(core.display.getDisplay());
        cpu.pc += 2;
    }
}
