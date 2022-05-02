package com.piscesdan.thinpillars.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockEndStoneThinPillar extends BlockThinPillar
{

    public BlockEndStoneThinPillar()
    {
        super(Properties.create(Material.ROCK)
        .hardnessAndResistance(3f, 9f)
        .sound(SoundType.STONE)
        .setRequiresTool()
        .harvestTool(ToolType.PICKAXE));
    }
}