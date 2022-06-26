package Net.Drai.AllTheWood.misc;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.modules.*;
import com.mcwfences.kikoz.objects.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import com.google.common.base.Supplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import someoneelse.betternetherreforged.blocks.*;

public class BlockGenerator {
    public static RegistryObject<Block> solid, cutout_mipped, cutout, translucent, translucent_moving_block, translucent_no_crumbling;

    public static void registry(SimpleModule module) {
           //Method is called
            for (ATWMaterial material : module.getMATERIALS()) {
                //Method is not called^
                for(BlockTypes missingBlockType: material.MISSING_BLOCK_TYPES){
                    String name = AllTheWood.BLOCK_TYPES.get(missingBlockType).getName();
                    ItemGroup itemGroup = AllTheWood.BLOCK_TYPES.get(missingBlockType).getItemGroup();
                    /* Example
                     * cutout = createBlock("" + material + "_" + name,
                            () -> new PaneBlock(), itemGroup);
                        * */

                    //TODO Add a register script for each block type
                    if (missingBlockType == BlockTypes.BARS || missingBlockType == BlockTypes.BARS_CROSSED) {
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new PaneBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F).noOcclusion().isValidSpawn(BlockGenerator::never).isRedstoneConductor(BlockGenerator::never).isSuffocating(BlockGenerator::never).isViewBlocking(BlockGenerator::never))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.BARS_CROSSED_HORIZONTAL || missingBlockType == BlockTypes.BARS_HORIZONTAL){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new PaneBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F).noOcclusion().isValidSpawn(BlockGenerator::never).isRedstoneConductor(BlockGenerator::never).isSuffocating(BlockGenerator::never).isViewBlocking(BlockGenerator::never))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.BAR_STOOL) {
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new BNBarStool(new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F)))
                                , itemGroup);
                    } else if (missingBlockType == BlockTypes.CHAIR){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new BNNormalChair(new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F)))
                                , itemGroup);
                    } else if (missingBlockType == BlockTypes.PLANKS) {
                        solid = createBlock("" + material.getName() + "_" + name,
                                () -> new Block(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.SLAB || missingBlockType == BlockTypes.PARQUET_SLAB){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new SlabBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.STAIRS || missingBlockType == BlockTypes.PARQUET_STAIRS){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new StairsBlock(() -> material.getBlockState(), AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.FENCE){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new FenceBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.FENCE_GATE){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F))
                                , itemGroup);
                    }
                }
            }
    }




    private static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier, ItemGroup tab)
    {
        RegistryObject<Block> block = AllTheWood.BLOCKS_REGISTRY.register(name, supplier);
        AllTheWood.ITEMS_REGISTRY.register(name, () -> new FuelItemBlock(block.get(), new Item.Properties().tab(tab)));
        return block;
    }

    private static Boolean never(BlockState blockState, IBlockReader blockView, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static boolean never(BlockState blockState, IBlockReader blockView, BlockPos blockPos) {
        return false;
    }

}