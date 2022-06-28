package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.data.*;
import net.minecraft.util.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.*;
import net.minecraftforge.fml.*;
import org.apache.logging.log4j.*;

import java.util.*;

public class ATWBlockStateProvider extends BlockStateProvider {
    public static final Logger LOGGER = LogManager.getLogger();

    public ATWBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
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
                    for (RegistryObject<Block> block : AllTheWood.BLOCKS_REGISTRY.getEntries()) {
                        LOGGER.info("RegistryObject location: " + block.getId().toString());
                        String testLocation = "test_block";
                        String primaryLocation = material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT);
                        LOGGER.info("Primary location: " + primaryLocation);
                        LOGGER.info("Test location: " + testLocation);
                        boolean isInPrimaryLocation = block.getId().equals(modLoc(primaryLocation));
                        ResourceLocation planks = new ResourceLocation(material.getModId(), material.getName() + "_planks");
                        ResourceLocation log = new ResourceLocation(material.getModId(), material.getName() + "_log");
                        if (missingBlockType.isInGroup(BlockTypes.Group.BLOCKS) && isInPrimaryLocation) {
                            LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());
                            simpleBlock(block.get());
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.SLABS) && isInPrimaryLocation) {
                            LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());
                            if (missingBlockType == BlockTypes.PARQUET_SLAB) {
                                //ResourceLocation planks = new ResourceLocation(module.getModId(), material.getName()+"_planks");
                            }
                            slabBlock((SlabBlock) block.get(), planks, planks);
                        } else if (missingBlockType.isInGroup(BlockTypes.Group.STAIRS) && isInPrimaryLocation){
                            LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());
                            stairsBlock((StairsBlock) block.get(), planks);
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.FENCES) && isInPrimaryLocation){
                            fenceBlock((FenceBlock) block.get(), planks);
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.FENCE_GATES) && isInPrimaryLocation){
                            fenceGateBlock((FenceGateBlock) block.get(), planks);
                        }

                        else if (block.getId().equals(modLoc(testLocation))) {
                            LOGGER.info("Match Found in Test");
                            simpleBlock(block.get());
                        } else{
                            LOGGER.info("No Match Found.");
                        }
                    }
                }
            }
        }
    }
}
