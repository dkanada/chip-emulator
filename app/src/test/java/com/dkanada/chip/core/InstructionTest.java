package com.dkanada.chip.core;

import com.dkanada.chip.core.instructions.Instruction_0x1000;
import com.dkanada.chip.core.instructions.Instruction_0x2000;
import com.dkanada.chip.core.instructions.Instruction_0x3000;
import com.dkanada.chip.core.instructions.x0000.Instruction_0x00EE;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InstructionTest {
    public Core core;

    @Before
    public void setUp() throws Exception {
        core = new Core();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void x00EE() throws Exception {
        core.memory.setWord((char) 0, (char) 0x230);
        core.cpu.pc = 0x244;
        core.cpu.sp = 0x2;

        new Instruction_0x00EE().execute(core, core.cpu, new OPCode((char) 0x00EE));

        Assert.assertEquals(0x230, core.cpu.pc);
    }

    @Test
    public void x1000() throws Exception {
        core.cpu.pc = 0x230;

        new Instruction_0x1000().execute(core, core.cpu, new OPCode((char) 0x1244));

        Assert.assertEquals(0x244, core.cpu.pc);
    }

    @Test
    public void x2000() throws Exception {
        core.cpu.pc = 0x230;

        new Instruction_0x2000().execute(core, core.cpu, new OPCode((char) 0x2244));

        Assert.assertEquals(0x244, core.cpu.pc);
        Assert.assertEquals(0x232, core.memory.getWord((char) (core.cpu.sp - 2)));
    }

    @Test
    public void x3000() throws Exception {
        core.cpu.v[0] = 0x5;

        core.cpu.pc = 540;

        new Instruction_0x3000().execute(core, core.cpu, new OPCode((char) 0x3001));
    }
}
