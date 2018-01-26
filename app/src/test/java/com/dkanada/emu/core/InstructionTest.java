package com.dkanada.emu.core;

import com.dkanada.emu.core.instructions.Instruction_0x1000;
import com.dkanada.emu.core.instructions.Instruction_0x2000;
import com.dkanada.emu.core.instructions.Instruction_0x3000;
import com.dkanada.emu.core.instructions.Instruction_0x4000;
import com.dkanada.emu.core.instructions.Instruction_0x5000;
import com.dkanada.emu.core.instructions.Instruction_0x6000;
import com.dkanada.emu.core.instructions.Instruction_0x7000;
import com.dkanada.emu.core.instructions.x0000.Instruction_0x00EE;

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

        core.cpu.pc = 0x230;

        new Instruction_0x3000().execute(core, core.cpu, new OPCode((char) 0x3001));
        Assert.assertEquals(0x232, core.cpu.pc);

        new Instruction_0x3000().execute(core, core.cpu, new OPCode((char) 0x3005));
        Assert.assertEquals(0x236, core.cpu.pc);
    }

    @Test
    public void x4000() throws Exception {
        core.cpu.v[0] = 0x5;

        core.cpu.pc = 0x230;

        new Instruction_0x4000().execute(core, core.cpu, new OPCode((char) 0x4001));
        Assert.assertEquals(0x234, core.cpu.pc);

        new Instruction_0x4000().execute(core, core.cpu, new OPCode((char) 0x4005));
        Assert.assertEquals(0x236, core.cpu.pc);
    }

    @Test
    public void x5000() throws Exception {
        core.cpu.v[0] = 0x40;
        core.cpu.v[7] = 0xA3;

        core.cpu.pc = 0x230;

        new Instruction_0x5000().execute(core, core.cpu, new OPCode((char) 0x5070));
        Assert.assertEquals(0x232, core.cpu.pc);

        core.cpu.v[7] = 0x40;

        new Instruction_0x5000().execute(core, core.cpu, new OPCode((char) 0x5070));
        Assert.assertEquals(0x236, core.cpu.pc);
    }

    @Test
    public void x6000() throws Exception {
        core.cpu.v[0] = 0x40;

        new Instruction_0x6000().execute(core, core.cpu, new OPCode((char) 0x604F));
        Assert.assertEquals(0x4F, core.cpu.v[0]);
    }

    @Test
    public void x7000() throws Exception {
        core.cpu.v[0] = 0x40;

        new Instruction_0x7000().execute(core, core.cpu, new OPCode((char) 0x70FF));
        Assert.assertEquals(0x3F, core.cpu.v[0]);
    }
}
