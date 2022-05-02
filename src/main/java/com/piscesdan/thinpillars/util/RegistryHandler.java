package com.piscesdan.thinpillars.util;

import com.piscesdan.thinpillars.ThinPillars;
import com.piscesdan.thinpillars.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class RegistryHandler
{
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ThinPillars.MODID);
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ThinPillars.MODID);

    public static  void init()
    {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    // blocks
    public static final RegistryObject<Block> ANDESITE_THIN_PILLAR = BLOCKS.register("andesite_thin_pillar", BlockAndesiteThinPillar::new);
    public static final RegistryObject<Block> BASALT_THIN_PILLAR = BLOCKS.register("basalt_thin_pillar", BlockBasaltThinPillar::new);
    public static final RegistryObject<Block> BLACKSTONE_THIN_PILLAR = BLOCKS.register("blackstone_thin_pillar", BlockBlackstoneThinPillar::new);
    public static final RegistryObject<Block> DIORITE_THIN_PILLAR = BLOCKS.register("diorite_thin_pillar", BlockDioriteThinPillar::new);
    public static final RegistryObject<Block> END_STONE_THIN_PILLAR = BLOCKS.register("end_stone_thin_pillar", BlockEndStoneThinPillar::new);
    public static final RegistryObject<Block> GRANITE_THIN_PILLAR = BLOCKS.register("granite_thin_pillar", BlockGraniteThinPillar::new);
    public static final RegistryObject<Block> PURPUR_THIN_PILLAR = BLOCKS.register("purpur_thin_pillar", BlockPurpurThinPillar::new);
    public static final RegistryObject<Block> QUARTZ_THIN_PILLAR = BLOCKS.register("quartz_thin_pillar", BlockQuartzThinPillar::new);
    public static final RegistryObject<Block> SANDSTONE_THIN_PILLAR = BLOCKS.register("sandstone_thin_pillar", BlockSandstoneThinPillar::new);
    public static final RegistryObject<Block> STONE_THIN_PILLAR = BLOCKS.register("stone_thin_pillar", BlockStoneThinPillar::new);
    public static final RegistryObject<Block> RED_SANDSTONE_THIN_PILLAR = BLOCKS.register("red_sandstone_thin_pillar", BlockRedSandstoneThinPillar::new);

    // quark
    public static final RegistryObject<Block> BIOTITE_THIN_PILLAR = BLOCKS.register("biotite_thin_pillar", BlockBiotiteThinPillar::new);
    public static final RegistryObject<Block> JASPER_THIN_PILLAR = BLOCKS.register("jasper_thin_pillar", BlockJasperThinPillar::new);
    public static final RegistryObject<Block> LIMESTONE_THIN_PILLAR = BLOCKS.register("limestone_thin_pillar", BlockLimestoneThinPillar::new);
    public static final RegistryObject<Block> MARBLE_THIN_PILLAR = BLOCKS.register("marble_thin_pillar", BlockMarbleThinPillar::new);
    public static final RegistryObject<Block> MIDORI_THIN_PILLAR = BLOCKS.register("midori_thin_pillar", BlockMidoriThinPillar::new);
    public static final RegistryObject<Block> SLATE_THIN_PILLAR = BLOCKS.register("slate_thin_pillar", BlockSlateThinPillar::new);
    public static final RegistryObject<Block> SOUL_SANDSTONE_THIN_PILLAR = BLOCKS.register("soul_sandstone_thin_pillar", BlockSoulSandstoneThinPillar::new);
    public static final RegistryObject<Block> VOIDSTONE_THIN_PILLAR = BLOCKS.register("voidstone_thin_pillar", BlockVoidstoneThinPillar::new);

    // itemblocks
    public static final RegistryObject<Item> ANDESITE_THIN_PILLAR_ITEM = ITEMS.register("andesite_thin_pillar", () -> new BlockItem(ANDESITE_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> BASALT_THIN_PILLAR_ITEM = ITEMS.register("basalt_thin_pillar", () -> new BlockItem(BASALT_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> BLACKSTONE_THIN_PILLAR_ITEM = ITEMS.register("blackstone_thin_pillar", () -> new BlockItem(BLACKSTONE_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> DIORITE_THIN_PILLAR_ITEM = ITEMS.register("diorite_thin_pillar", () -> new BlockItem(DIORITE_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> END_STONE_THIN_PILLAR_ITEM = ITEMS.register("end_stone_thin_pillar", () -> new BlockItem(END_STONE_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> GRANITE_THIN_PILLAR_ITEM = ITEMS.register("granite_thin_pillar", () -> new BlockItem(GRANITE_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> PURPUR_THIN_PILLAR_ITEM = ITEMS.register("purpur_thin_pillar", () -> new BlockItem(PURPUR_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> QUARTZ_THIN_PILLAR_ITEM = ITEMS.register("quartz_thin_pillar", () -> new BlockItem(QUARTZ_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> SANDSTONE_THIN_PILLAR_ITEM = ITEMS.register("sandstone_thin_pillar", () -> new BlockItem(SANDSTONE_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> STONE_THIN_PILLAR_ITEM = ITEMS.register("stone_thin_pillar", () -> new BlockItem(STONE_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));
    public static final RegistryObject<Item> RED_SANDSTONE_THIN_PILLAR_ITEM = ITEMS.register("red_sandstone_thin_pillar", () -> new BlockItem(RED_SANDSTONE_THIN_PILLAR.get(), new Item.Properties().group(ItemGroup.BUILDING_BLOCKS)));

    // quark
    static Item.Properties quarkGroup = ModList.get().isLoaded("quark") ? new Item.Properties().group(ItemGroup.BUILDING_BLOCKS) : new Item.Properties();

    public static final RegistryObject<Item> BIOTITE_THIN_PILLAR_ITEM = ITEMS.register("biotite_thin_pillar", () -> new BlockItem(BIOTITE_THIN_PILLAR.get(), quarkGroup));
    public static final RegistryObject<Item> JASPER_THIN_PILLAR_ITEM = ITEMS.register("jasper_thin_pillar", () -> new BlockItem(JASPER_THIN_PILLAR.get(), quarkGroup));
    public static final RegistryObject<Item> LIMESTONE_THIN_PILLAR_ITEM = ITEMS.register("limestone_thin_pillar", () -> new BlockItem(LIMESTONE_THIN_PILLAR.get(), quarkGroup));
    public static final RegistryObject<Item> MARBLE_THIN_PILLAR_ITEM = ITEMS.register("marble_thin_pillar", () -> new BlockItem(MARBLE_THIN_PILLAR.get(), quarkGroup));
    public static final RegistryObject<Item> MIDORI_THIN_PILLAR_ITEM = ITEMS.register("midori_thin_pillar", () -> new BlockItem(MIDORI_THIN_PILLAR.get(), quarkGroup));
    public static final RegistryObject<Item> SLATE_THIN_PILLAR_ITEM = ITEMS.register("slate_thin_pillar", () -> new BlockItem(SLATE_THIN_PILLAR.get(), quarkGroup));
    public static final RegistryObject<Item> SOUL_SANDSTONE_THIN_PILLAR_ITEM = ITEMS.register("soul_sandstone_thin_pillar", () -> new BlockItem(SOUL_SANDSTONE_THIN_PILLAR.get(), quarkGroup));
    public static final RegistryObject<Item> VOIDSTONE_THIN_PILLAR_ITEM = ITEMS.register("voidstone_thin_pillar", () -> new BlockItem(VOIDSTONE_THIN_PILLAR.get(), quarkGroup));

}
