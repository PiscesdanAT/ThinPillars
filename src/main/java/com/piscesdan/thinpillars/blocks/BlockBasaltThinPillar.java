package com.piscesdan.thinpillars.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockBasaltThinPillar extends BlockThinPillar
{

    public BlockBasaltThinPillar()
    {
        super(Properties.create(Material.ROCK)
        .hardnessAndResistance(1.25f, 4.2f)
        .sound(SoundType.BASALT)
        .setRequiresTool()
        .harvestTool(ToolType.PICKAXE));
    }
}