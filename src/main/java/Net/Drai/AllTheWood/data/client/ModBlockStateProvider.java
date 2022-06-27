package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.BlockGenerator;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.data.*;
import net.minecraft.item.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.*;
import net.minecraftforge.common.data.*;

import static Net.Drai.AllTheWood.block.BlockGenerator.createBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, AllTheWood.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(createBlock("test_block",
                () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F))
                , ItemGroup.TAB_FOOD).get());
        /*
        for (ATWMaterial material : module.getMATERIALS()) {

                for(BlockTypes missingBlockType: material.MISSING_BLOCK_TYPES){
                    String name = missingBlockType.name().toLowerCase(Locale.ROOT);
                    ItemGroup itemGroup;
                    if(AllTheWood.BLOCK_TYPES.get(missingBlockType).getItemGroup() != null){
                        itemGroup= AllTheWood.BLOCK_TYPES.get(missingBlockType).getItemGroup();
                    } else {
                        itemGroup= material.getItemGroup();
                    }
                    /* Example
                     * cutout = createBlock("" + material + "_" + name,
                            () -> new PaneBlock(), itemGroup);
                        * */

        //TODO Add a register script for each block type
                    /*if (missingBlockType == BlockTypes.BARS || missingBlockType == BlockTypes.BARS_CROSSED) {
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new PaneBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()).noOcclusion().isValidSpawn(BlockGenerator::never).isRedstoneConductor(BlockGenerator::never).isSuffocating(BlockGenerator::never).isViewBlocking(BlockGenerator::never))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.BARS_CROSSED_HORIZONTAL || missingBlockType == BlockTypes.BARS_HORIZONTAL){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new HorizontalBarsSlabBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()).noOcclusion().isValidSpawn(BlockGenerator::never).isRedstoneConductor(BlockGenerator::never).isSuffocating(BlockGenerator::never).isViewBlocking(BlockGenerator::never))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.BAR_STOOL) {
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new BNBarStool(new SlabBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper())))
                                , itemGroup);
                    } else if (missingBlockType == BlockTypes.CHAIR){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new BNNormalChair(new SlabBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper())))
                                , itemGroup);
                    } else if (missingBlockType == BlockTypes.PLANKS) {
                        solid = createBlock("" + material.getName() + "_" + name,
                                () -> new Block(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.SLAB || missingBlockType == BlockTypes.PARQUET_SLAB){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new SlabBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.STAIRS || missingBlockType == BlockTypes.PARQUET_STAIRS){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new StairsBlock(() -> material.getBlockState(), AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.FENCE){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new FenceBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.FENCE_GATE){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F))
                                , itemGroup);
                    }
    }
}
         */
    }
}
