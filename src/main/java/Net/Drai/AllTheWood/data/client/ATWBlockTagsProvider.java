package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.data.*;
import net.minecraft.tags.*;
import net.minecraftforge.common.data.*;
import net.minecraftforge.fml.*;
import org.apache.logging.log4j.*;

import javax.annotation.*;
import java.util.*;

public class ATWBlockTagsProvider extends BlockTagsProvider {
    public static final Logger LOGGER = LogManager.getLogger();
    public ATWBlockTagsProvider(DataGenerator p_i48256_1_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_i48256_1_, AllTheWood.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        for (SimpleModule module : AllTheWood.MODULES) {
            LOGGER.info("module: " + module.getModId());
            for (ATWMaterial material : module.getMATERIALS()) {
                LOGGER.info("material: " + material.getName());
                for (BlockTypes missingBlockType : material.MISSING_BLOCK_TYPES) {
                    for (RegistryObject<Block> block : module.BLOCKS_REGISTRY.getEntries()) {
                        String primaryLocation = material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT);
                        LOGGER.info("Primary location: " + primaryLocation);
                        LOGGER.info(block.getId());
                        LOGGER.info(material.getModId()+":"+primaryLocation);
                        boolean isInPrimaryLocation = block.getId().toString().equals(material.getModId()+":"+primaryLocation);
                        LOGGER.info(isInPrimaryLocation);
                        if(isInPrimaryLocation){
                            LOGGER.info("missingBlockType: " + missingBlockType.name());
                            LOGGER.info(missingBlockType.getGroup().name());
                            if(missingBlockType == BlockTypes.LADDER){
                                this.tag(BlockTags.CLIMBABLE).add(new Block[]{block.get()});
                            }
                           // tag(blocktype.blockTag).add(block.get());
                        }
                    }

                }
            }
        }
    }
}



