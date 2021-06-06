package com.piscesdan.thinpillars.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockThinPillar extends Block implements IWaterLoggable
{
    public static final BooleanProperty WATTERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<PillarType> PILLAR_TYPE = EnumProperty.create("pillartype", PillarType.class);

    private final VoxelShape middleShape, bottomShape, topShape;

    public BlockThinPillar(AbstractBlock.Properties properties)
    {
        super(properties);

        this.setDefaultState(this.getStateContainer().getBaseState().with(PILLAR_TYPE, PillarType.MIDDLE).with(WATTERLOGGED, false));
        this.middleShape    = createPillarShape();
        this.bottomShape    = createPillarBottomShape();
        this.topShape       = createPillarTopShape();
    }

    // shapes for pillar types

    protected VoxelShape createPillarShape()
    {
        return Block.makeCuboidShape(2, 0, 2, 14, 16, 14);
    }

    protected VoxelShape createPillarBottomShape()
    {
        VoxelShape column = Block.makeCuboidShape(2, 4, 2, 14, 16, 14);
        VoxelShape bottom = Block.makeCuboidShape(0, 0, 0, 16, 4, 16);
        return VoxelShapes.combine(column, bottom, IBooleanFunction.OR);
    }

    protected VoxelShape createPillarTopShape()
    {
        VoxelShape column = Block.makeCuboidShape(2, 0, 2, 14, 12, 14);
        VoxelShape top = Block.makeCuboidShape(0, 12, 0, 16, 16, 16);
        return VoxelShapes.combine(column, top, IBooleanFunction.OR);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
    {
        switch(state.get(PILLAR_TYPE))
        {
            case TOP:
                return topShape;
            case BOTTOM:
                return  bottomShape;
            default:
            case MIDDLE:
                return middleShape;
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(PILLAR_TYPE, WATTERLOGGED);
    }

    @Override
    public BlockState updatePostPlacement(BlockState thisState, Direction otherBlockFacing, BlockState otherBlockState, IWorld world, BlockPos thisPos, BlockPos otherBlockPos)
    {
        if(thisState.get(WATTERLOGGED))
        {
            world.getPendingFluidTicks().scheduleTick(thisPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return this.getThisState(world, thisPos).with(WATTERLOGGED, thisState.get(WATTERLOGGED));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx)
    {
        BlockPos blockpos = ctx.getPos();
        World world = ctx.getWorld();
        FluidState fluidstate = world.getFluidState(blockpos);
        return this.getThisState(world, blockpos).with(WATTERLOGGED, fluidstate.getFluid() == Fluids.WATER);
    }

    protected BlockState getThisState(IBlockReader world, BlockPos pos)
    {
        boolean hasUp = world.getBlockState(pos.up()).getBlock() instanceof BlockThinPillar;
        boolean hasDown = world.getBlockState(pos.down()).getBlock() instanceof BlockThinPillar;
        if(hasUp)
        {
            if(hasDown)
            {
                return this.getDefaultState().with(PILLAR_TYPE, PillarType.MIDDLE);
            }
            return this.getDefaultState().with(PILLAR_TYPE, PillarType.BOTTOM);
        } else if(hasDown)
        {
            return this.getDefaultState().with(PILLAR_TYPE, PillarType.TOP);
        }
        return this.getDefaultState().with(PILLAR_TYPE, PillarType.MIDDLE);
    }

    public FluidState getFluidState(BlockState state)
    {
        return state.get(WATTERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }


    public enum PillarType implements IStringSerializable
    {
        TOP,
        MIDDLE,
        BOTTOM;

        @Override
        public String getString()
        {
            return name().toLowerCase();
        }

        @Override
        public String toString()
        {
            return this.getString();
        }
    }
}
