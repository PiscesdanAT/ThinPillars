package com.piscesdan.thinpillars.blocks;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
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
import java.util.EnumMap;

public class BlockThinPillar extends RotatedPillarBlock implements IWaterLoggable
{
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final EnumProperty<PillarType> PILLAR_TYPE = EnumProperty.create("pillartype", PillarType.class);

    private static final EnumMap<Direction.Axis, VoxelShape> TOP_SHAPES = Maps.newEnumMap(ImmutableMap.of(
            Direction.Axis.X, VoxelShapes.combineAndSimplify(makeCuboidShape(0, 2, 2, 12, 14, 14), makeCuboidShape(12, 0, 0, 16,16,16), IBooleanFunction.OR),
            Direction.Axis.Y, VoxelShapes.combineAndSimplify(makeCuboidShape(2, 0, 2, 14, 12, 14), makeCuboidShape(0, 12, 0, 16, 16, 16), IBooleanFunction.OR),
            Direction.Axis.Z, VoxelShapes.combineAndSimplify(makeCuboidShape(2, 2, 4, 14, 14, 16), makeCuboidShape(0, 0, 0, 16, 16, 4), IBooleanFunction.OR)
    ));

    private static final EnumMap<Direction.Axis, VoxelShape> BOTTOM_SHAPES = Maps.newEnumMap(ImmutableMap.of(
            Direction.Axis.X, VoxelShapes.combineAndSimplify(makeCuboidShape(4, 2, 2, 16, 14, 14), makeCuboidShape(0, 0, 0, 4,16,16), IBooleanFunction.OR),
            Direction.Axis.Y, VoxelShapes.combineAndSimplify(makeCuboidShape(2, 4, 2, 14, 16, 14), makeCuboidShape(0, 0, 0, 16, 4, 16), IBooleanFunction.OR),
            Direction.Axis.Z, VoxelShapes.combineAndSimplify(makeCuboidShape(2, 2, 0, 14, 14, 12), makeCuboidShape(0, 0, 12, 16, 16, 16), IBooleanFunction.OR)
    ));

    private static final EnumMap<Direction.Axis, VoxelShape> MIDDLE_SHAPES = Maps.newEnumMap(ImmutableMap.of(
            Direction.Axis.X, makeCuboidShape(0, 2, 2, 16, 14, 14),
            Direction.Axis.Y, makeCuboidShape(2, 0, 2, 14, 16, 14),
            Direction.Axis.Z, makeCuboidShape(2, 2, 0, 14, 14, 16)
    ));


    public BlockThinPillar(AbstractBlock.Properties properties)
    {
        super(properties);

        this.setDefaultState(this.getStateContainer().getBaseState().with(PILLAR_TYPE, PillarType.MIDDLE).with(WATERLOGGED, false).with(AXIS, Direction.Axis.Y));
    }

    // shapes for pillar types

    protected VoxelShape createPillarShape()
    {
        return Block.makeCuboidShape(2, 0, 2, 14, 16, 14);
    }

    @SuppressWarnings("deprecation")
    @Deprecated
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext ctx)
    {
        switch(state.get(PILLAR_TYPE))
        {
            case TOP:
                return TOP_SHAPES.get(state.get(AXIS));
            case BOTTOM:
                return BOTTOM_SHAPES.get(state.get(AXIS));
            default:
            case MIDDLE:
                return MIDDLE_SHAPES.get(state.get(AXIS));
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(PILLAR_TYPE, WATERLOGGED);
    }

    @Override
    public BlockState updatePostPlacement(BlockState thisState, Direction otherBlockFacing, BlockState otherBlockState, IWorld world, BlockPos thisPos, BlockPos otherBlockPos)
    {
        if(thisState.get(WATERLOGGED))
        {
            world.getPendingFluidTicks().scheduleTick(thisPos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return this.getThisState(world, thisPos, thisState.get(AXIS)).with(WATERLOGGED, thisState.get(WATERLOGGED)).with(AXIS, thisState.get(AXIS));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx)
    {
        BlockPos blockpos = ctx.getPos();
        World world = ctx.getWorld();
        FluidState fluidstate = world.getFluidState(blockpos);
        return this.getThisState(world, blockpos, ctx.getFace().getAxis()).with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER).with(AXIS, ctx.getFace().getAxis());
    }

    protected BlockState getThisState(IBlockReader world, BlockPos pos, Direction.Axis axis)
    {
        switch(axis)
        {
            case X:
            {
                BlockState stateEast = world.getBlockState(pos.east());
                BlockState stateWest = world.getBlockState(pos.west());
                boolean hasEast = stateEast.getBlock() instanceof BlockThinPillar && stateEast.get(AXIS) == Direction.Axis.X;
                boolean hasWest = stateWest.getBlock() instanceof BlockThinPillar && stateWest.get(AXIS) == Direction.Axis.X;
                if(hasEast)
                {
                    if(hasWest)
                    {
                        return this.getDefaultState().with(PILLAR_TYPE, PillarType.MIDDLE);
                    }
                    return this.getDefaultState().with(PILLAR_TYPE, PillarType.BOTTOM);
                } else if(hasWest)
                {
                    return this.getDefaultState().with(PILLAR_TYPE, PillarType.TOP);
                }
                return this.getDefaultState().with(PILLAR_TYPE, PillarType.MIDDLE);
            }

            case Z:
            {
                BlockState stateNorth = world.getBlockState(pos.north());
                BlockState stateSouth = world.getBlockState(pos.south());
                boolean hasNorth = stateNorth.getBlock() instanceof BlockThinPillar && stateNorth.get(AXIS) == Direction.Axis.Z;
                boolean hasSouth = stateSouth.getBlock() instanceof BlockThinPillar && stateSouth.get(AXIS) == Direction.Axis.Z;
                if(hasNorth)
                {
                    if(hasSouth)
                    {
                        return this.getDefaultState().with(PILLAR_TYPE, PillarType.MIDDLE);
                    }
                    return this.getDefaultState().with(PILLAR_TYPE, PillarType.BOTTOM);
                } else if(hasSouth)
                {
                    return this.getDefaultState().with(PILLAR_TYPE, PillarType.TOP);
                }
                return this.getDefaultState().with(PILLAR_TYPE, PillarType.MIDDLE);
            }

            default:
            case Y:
            {
                BlockState stateUp = world.getBlockState(pos.up());
                BlockState stateDown = world.getBlockState(pos.down());
                boolean hasUp = stateUp.getBlock() instanceof BlockThinPillar && stateUp.get(AXIS) == Direction.Axis.Y;
                boolean hasDown = stateDown.getBlock() instanceof BlockThinPillar && stateDown.get(AXIS) == Direction.Axis.Y;
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
        }
    }

    public FluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
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
