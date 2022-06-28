package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.data.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.*;
import net.minecraftforge.fml.*;
import org.apache.logging.log4j.*;

import java.util.*;

public class ATWItemModelProvider extends ItemModelProvider {
    public static final Logger LOGGER = LogManager.getLogger();
    public ATWItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(generator, AllTheWood.MOD_ID,existingFileHelper);
    }

    @Override
    protected void registerModels() {

        LOGGER.info("registerStatesAndModels method Called.");
        for (SimpleModule module : AllTheWood.MODULES) {
            LOGGER.info("module: " + module.getModId());
            for (ATWMaterial material : module.getMATERIALS()) {
                LOGGER.info("material: " + material.getName());
                for (BlockTypes missingBlockType : material.MISSING_BLOCK_TYPES) {
                    LOGGER.info("missingBlockType: " + missingBlockType.name());
                    LOGGER.info(missingBlockType.getGroup().name());
                    for (RegistryObject<Block> block : AllTheWood.BLOCKS_REGISTRY.getEntries()) {
                        String name = missingBlockType.name().toLowerCase(Locale.ROOT);
                        String primaryLocation = material.getName() + "_" + name;
                        LOGGER.info(modLoc("block/"+primaryLocation));
                        LOGGER.info(block.getId().toString());
                        LOGGER.info("Found a block Registry Object.");
                        boolean isInPrimaryLocation = block.getId().equals(modLoc(primaryLocation));
                        if(isInPrimaryLocation) {
                            withExistingParent(block.getId().toString(), modLoc("block/" + primaryLocation));
                        }
                    }
                }
            }
        }
        withExistingParent("test_block", modLoc("block/test_block"));
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        //builder(itemGenerated, "test_block");
    }

    private void builder(ModelFile itemGenerated, String name) {
        //getBuilder(name).parent(itemGenerated).texture("layer:0", "item/"+name);
    }
}
