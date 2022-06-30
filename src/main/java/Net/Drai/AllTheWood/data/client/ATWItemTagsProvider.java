package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.data.*;
import net.minecraftforge.common.data.*;
import net.minecraftforge.fml.*;
import org.apache.logging.log4j.*;

import java.util.*;

public class ATWItemTagsProvider extends ItemTagsProvider {
    public static final Logger LOGGER = LogManager.getLogger();
    public ATWItemTagsProvider(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(gen, blockTagProvider, AllTheWood.MOD_ID, existingFileHelper);
    }
    @Override
    protected void addTags() {
        for (SimpleModule module : AllTheWood.MODULES) {
            LOGGER.info("module: " + module.getModId());
            for (ATWMaterial material : module.getMATERIALS()) {
                LOGGER.info("material: " + material.getName());
                for (BlockTypes missingBlockType : material.MISSING_BLOCK_TYPES) {
                    for (RegistryObject<Block> block : module.BLOCKS_REGISTRY.getEntries()) {
                        String testLocation = "test_block";
                        String primaryLocation = material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT);
                        LOGGER.info("Primary location: " + primaryLocation);
                        LOGGER.info("Test location: " + testLocation);
                        boolean isInPrimaryLocation = block.getId().equals(AllTheWood.MOD_ID+":"+primaryLocation);
                        if(isInPrimaryLocation){
                            LOGGER.info("missingBlockType: " + missingBlockType.name());
                            LOGGER.info(missingBlockType.getGroup().name());
                            BlockType blocktype = AllTheWood.BLOCK_TYPES.get(missingBlockType);
                            //copy(blocktype.blockTag, blocktype.itemTag);
                        }
                    }
                }
            }
        }
    }
}
