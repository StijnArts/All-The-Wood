package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.data.*;
import net.minecraft.util.*;
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
                    for (RegistryObject<Block> block : module.BLOCKS_REGISTRY.getEntries()) {
                        String primaryLocation = module.getModId()+":"+material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT);
                        if(missingBlockType == BlockTypes.STRIPPED_LOG){
                            primaryLocation = module.getModId()+":stripped_"+material.getName()+"_log";
                        } else if(missingBlockType == BlockTypes.STRIPPED_WOOD){
                            primaryLocation = module.getModId()+":stripped_"+material.getName()+"_wood";
                        }
                        if((missingBlockType == BlockTypes.BARREL || missingBlockType == BlockTypes.LADDER) && (material.getModId().equals("minecraft") && material.getName().equals("oak"))){
                            primaryLocation = module.getModId()+":"+missingBlockType.name().toLowerCase(Locale.ROOT);
                        }
                        LOGGER.info("block Primary Location: "+primaryLocation);
                        LOGGER.info(block.getId().toString());
                        LOGGER.info("Found a block Registry Object.");
                        boolean isInPrimaryLocation = block.getId().toString().equals(primaryLocation);
                        if(isInPrimaryLocation) {
                            LOGGER.info("Match Found.");
                            if(missingBlockType == BlockTypes.FENCE){
                                withExistingParent(block.getId().toString(), module.getModId()+":block/" +material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT)+"_inventory");
                            } else if(missingBlockType == BlockTypes.LADDER){
                                if(material.getModId().equals("minecraft") && material.getName().equals("oak")) {
                                    LOGGER.info("Found Oak Ladder");
                                    singleTexture("ladder", mcLoc("item/generated"), "layer0",  new ResourceLocation(material.getModId(),"block/ladder"));
                                } else {
                                    LOGGER.info("Found non-Oak Ladder");
                                    singleTexture(material.getName()+"_ladder", mcLoc("item/generated"), "layer0",  new ResourceLocation(material.getModId(),"item/" + material.getName() + "_ladder"));
                                    }
                            } else if(missingBlockType == BlockTypes.SIGN){
                                singleTexture(material.getName() + "_sign", mcLoc("item/generated"), "layer0", new ResourceLocation(material.getModId(),"item/" + material.getName() + "_sign"));
                            } else if(missingBlockType == BlockTypes.BARREL){
                                if(material.getModId().equals("minecraft") && material.getName().equals("oak")) {
                                    withExistingParent(block.getId().toString(), module.getModId()+":block/"+missingBlockType.name().toLowerCase(Locale.ROOT));
                                } else {
                                    withExistingParent(block.getId().toString(), module.getModId()+":block/" + material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT));
                                    }
                            } else if(missingBlockType == BlockTypes.CHEST){
                                ((ItemModelBuilder)((ItemModelBuilder)this.getBuilder(material.getName() + "_chest")).parent(new ModelFile.UncheckedModelFile("item/chest"))).texture("particle", new ResourceLocation(material.getModId(),"block/" +material.getName()  + "_planks"));
                            }
                            else {
                                String parent = module.getModId()+":block/" +material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT);
                                if(missingBlockType == BlockTypes.STRIPPED_LOG){
                                    LOGGER.info("Parent changed for stripped block.");
                                    parent = module.getModId()+":block/stripped_" +material.getName() + "_log";
                                    LOGGER.info("New Parent: "+parent);
                                } else if (missingBlockType == BlockTypes.STRIPPED_WOOD){
                                    LOGGER.info("Parent changed for stripped block.");
                                    parent = module.getModId()+":block/stripped_" +material.getName() + "_wood";
                                    LOGGER.info("New Parent: "+parent);
                                }
                                withExistingParent(block.getId().toString(),parent);
                            }
                        }
                    }
                }
            }
        }
        //withExistingParent("test_block", modLoc("block/test_block"));
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
        builder(itemGenerated, "test_block");
    }

    private void builder(ModelFile itemGenerated, String name) {
        //getBuilder(name).parent(itemGenerated).texture("layer:0", "item/"+name);
    }
}
