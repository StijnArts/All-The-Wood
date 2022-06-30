package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.data.*;
import net.minecraft.util.*;
import net.minecraftforge.common.data.*;
import net.minecraftforge.fml.*;
import org.apache.logging.log4j.*;
import java.util.*;

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
                        String primaryLocation = module.getModId()+":"+material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT);
                        if(missingBlockType == BlockTypes.STRIPPED_LOG){
                            primaryLocation = module.getModId()+":stripped_"+material.getName()+"_log";
                        } else if(missingBlockType == BlockTypes.STRIPPED_WOOD){
                            primaryLocation = module.getModId()+":stripped_"+material.getName()+"_wood";
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
                            this.add(block.get(), capitalizeString(name));
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
