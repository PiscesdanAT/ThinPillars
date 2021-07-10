package com.piscesdan.thinpillars.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockRedSandstoneThinPillar extends BlockThinPillar
{

    public BlockRedSandstoneThinPillar()
    {
        super(Properties.create(Material.ROCK)
        .hardnessAndResistance(2f, 6f)
        .sound(SoundType.STONE)
        .setRequiresTool()
        .harvestTool(ToolType.PICKAXE));
    }
}