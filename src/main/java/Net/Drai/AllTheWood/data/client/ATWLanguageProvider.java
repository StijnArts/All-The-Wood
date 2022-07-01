package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.data.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraftforge.common.data.*;
import net.minecraftforge.fml.*;
import org.apache.logging.log4j.*;
import java.util.*;
import java.util.function.*;

import static Net.Drai.AllTheWood.block.enums.BlockTypes.SIGN;

public class ATWLanguageProvider extends LanguageProvider {
    public static final Logger LOGGER = LogManager.getLogger();
    public ATWLanguageProvider(DataGenerator gen) {
        super(gen, AllTheWood.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        for (SimpleModule module : AllTheWood.MODULES) {
            LOGGER.info("module: " + module.getModId());
            for (ATWMaterial material : module.getMATERIALS()) {
                LOGGER.info("material: " + material.getName());
                for (BlockTypes missingBlockType : material.MISSING_BLOCK_TYPES) {
                    for (RegistryObject<Block> block : module.BLOCKS_REGISTRY.getEntries()) {
                        String testLocation = "test_block";
                        LOGGER.info("RegistryObject location: "+block.getId().toString());
                        LOGGER.info("Block item: "+block.get());
                        LOGGER.info("Block Description: "+block.get().getDescriptionId());
                        LOGGER.info("Block as item: "+block.get().asItem());
                        LOGGER.info("Block registry: "+block.get().getRegistryName());
                        String primaryLocation = module.getModId()+":"+material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT);
                        if(missingBlockType == BlockTypes.STRIPPED_LOG){
                            primaryLocation = module.getModId()+":stripped_"+material.getName()+"_log";
                        } else if(missingBlockType == BlockTypes.STRIPPED_WOOD){
                            primaryLocation = module.getModId()+":stripped_"+material.getName()+"_wood";
                        } else if(missingBlockType == BlockTypes.BARREL || missingBlockType == BlockTypes.LADDER){
                            if(module.getModId() == "minecraft" && material.getName() == "oak") {
                                primaryLocation = material.getModId() + ":" + missingBlockType.name().toLowerCase(Locale.ROOT);
                            }
                        }
                        LOGGER.info("Primary location: " + primaryLocation);
                        LOGGER.info("Test location: " + testLocation);
                        boolean isInPrimaryLocation = block.getId().toString().equals(primaryLocation);
                        if(isInPrimaryLocation) {
                            LOGGER.info("Match Found.");
                            String name = material.getName() + " " + AllTheWood.BLOCK_TYPES.get(missingBlockType).getName().replaceAll("_", " ");
                            LOGGER.info(name);
                            if(missingBlockType == BlockTypes.STRIPPED_WOOD){
                                    name = "stripped "+material.getName()+" wood";
                            } else if(missingBlockType == BlockTypes.STRIPPED_LOG){
                                name = "stripped "+material.getName()+" log";
                            }

                            if(missingBlockType == BlockTypes.SIGN){
                                for (RegistryObject<Item> item :module.ITEM_REGISTRY.getEntries()){
                                    LOGGER.info("Item id: "+item.getId().toString());
                                    LOGGER.info("Item item: "+item.get());
                                    LOGGER.info("Item Description: "+item.get().getDescriptionId());
                                    LOGGER.info("Item as item: "+item.get().asItem());
                                    LOGGER.info("Item registry: "+item.get().getItem().getRegistryName());
                                    String itemLocation = module.getModId()+":"+material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT);
                                    LOGGER.info("item Primary Location: "+ itemLocation);

                                    boolean isinItemLocation = item.getId().toString().equals(itemLocation);
                                    if(isinItemLocation){
                                        for(RegistryObject<Block> wall : module.BLOCKS_REGISTRY.getEntries()) {
                                            String wallLocation = module.getModId() + ":" + material.getName() + "_wall_sign";
                                            LOGGER.info("Wall Location: " + wallLocation);
                                            LOGGER.info("Wall id: "+wall.getId().toString());
                                            LOGGER.info("Wall item: "+wall.get());
                                            LOGGER.info("Wall Description: "+wall.get().getDescriptionId());
                                            LOGGER.info("Wall as item: "+wall.get().asItem());
                                            LOGGER.info("Wall registry: "+wall.get().getRegistryName());
                                            boolean isInWallLocation = wall.getId().toString().equals(wallLocation);
                                            if (isInWallLocation) {
                                                LOGGER.info("wall Found.");
                                                LOGGER.info("item Found.");
                                                LOGGER.info(item.getId().toString());
                                                //this.add(item.get(), capitalizeString(name));
                                                this.add(block.get(), capitalizeString(name));
                                                //this.add(wall.get(), capitalizeString(name));
                                            }
                                        }

                                    }
                                }
                            } else {
                                LOGGER.info("Block Lang Generated.");
                                this.add(block.get(), capitalizeString(name));
                            }

                        }else if (block.getId().equals(new ResourceLocation(AllTheWood.MOD_ID+":"+testLocation))) {
                            LOGGER.info("Match Found.");
                            this.add(block.get(), capitalizeString("test block"));
                        }
                    }

                }
            }
        }
    }
    private static String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }
}
