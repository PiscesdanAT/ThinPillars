package com.piscesdan.thinpillars.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;
import vazkii.quark.content.world.block.IMyaliteColorProvider;

public class BlockMyaliteThinPillar extends BlockThinPillar implements IMyaliteColorProvider
{

    public BlockMyaliteThinPillar()
    {
        super(Properties.create(Material.ROCK)
        .hardnessAndResistance(1.5f, 6f)
        .sound(SoundType.STONE)
        .setRequiresTool()
        .harvestTool(ToolType.PICKAXE));
    }
}