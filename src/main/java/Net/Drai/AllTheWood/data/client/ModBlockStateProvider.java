package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.misc.*;
import Net.Drai.AllTheWood.modules.*;
import com.google.common.base.*;
import com.google.common.base.Supplier;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.data.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.*;
import net.minecraftforge.common.data.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.registries.*;
import org.apache.logging.log4j.*;

import java.util.*;
import java.util.function.*;

import static Net.Drai.AllTheWood.block.BlockGenerator.createBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public static final Logger LOGGER = LogManager.getLogger();

    public ModBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, AllTheWood.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        LOGGER.info("registerStatesAndModels method Called.");
        for (SimpleModule module : AllTheWood.MODULES) {
            LOGGER.info("module: " + module.getModId());
            for (ATWMaterial material : module.getMATERIALS()) {
                LOGGER.info("material: " + material.getName());
                for (BlockTypes missingBlockType : material.MISSING_BLOCK_TYPES) {
                    LOGGER.info("missingBlockType: " + missingBlockType.name());
                    LOGGER.info(missingBlockType.getGroup().name());
                    if (missingBlockType.isInGroup(BlockTypes.Group.BLOCKS)) {
                        LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());
                        String name = missingBlockType.name().toLowerCase(Locale.ROOT);
                        for (RegistryObject<Block> block : AllTheWood.BLOCKS_REGISTRY.getEntries()) {
                            LOGGER.info("Found a block Registry Object.");
                            LOGGER.info("RegistryObject location: "+block.getId().toString());
                            String primaryLocation = material.getName() + "_" + name;
                            String testLocation = "test_block";
                            LOGGER.info("Primary location: "+new ResourceLocation(AllTheWood.MOD_ID, primaryLocation));
                            LOGGER.info("Test location: "+new ResourceLocation(AllTheWood.MOD_ID, testLocation));
                            /*if (block.getId().equals(modLoc(primaryLocation))) {
                                LOGGER.info("Match Found.");
                                simpleBlock(block.get());
                            } else */
                            if (block.getId().equals(modLoc(testLocation))) {
                                LOGGER.info("Match Found.");
                                simpleBlock(block.get());
                            } else{
                                LOGGER.info("No Match Found.");
                            }
                        }
                    }
                }
            }
        }
        /* Example
                     * cutout = createBlock("" + material + "_" + name,
                            () -> new PaneBlock(), itemGroup);
                        *

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
