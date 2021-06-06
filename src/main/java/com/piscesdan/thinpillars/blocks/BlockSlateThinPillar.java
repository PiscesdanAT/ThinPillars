package com.piscesdan.thinpillars.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockSlateThinPillar extends BlockThinPillar
{

    public BlockSlateThinPillar()
    {
        super(Properties.create(Material.ROCK)
        .hardnessAndResistance(1.5f, 6f)
        .sound(SoundType.STONE)
        .setRequiresTool()
        .harvestTool(ToolType.PICKAXE));
    }
}