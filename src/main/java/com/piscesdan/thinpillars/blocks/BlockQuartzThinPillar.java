package com.piscesdan.thinpillars.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockQuartzThinPillar extends BlockThinPillar
{
    public BlockQuartzThinPillar()
    {
        super(Properties.create(Material.ROCK)
        .hardnessAndResistance(0.8f, 0.8f)
        .sound(SoundType.STONE)
        .setRequiresTool()
        .harvestTool(ToolType.PICKAXE));
    }
}
