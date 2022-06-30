package Net.Drai.AllTheWood.events;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.*;
import org.apache.logging.log4j.*;

public class logStripper {
    public static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void rightClicked(BlockEvent.BlockToolInteractEvent event) {
        LOGGER.info("All The Wood eventBlockUsed on getBlock() : " + event.getState().getBlock().getDescriptionId());
        if (event.getToolType() == ToolType.AXE) {
            LOGGER.info("Axe was used.");
            for (SimpleModule module : AllTheWood.MODULES) {
                LOGGER.info("module: " + module.getModId());
                for (ATWMaterial material : module.getMATERIALS()) {
                    for (BlockTypes missingBlockType : material.MISSING_BLOCK_TYPES) {
                        String log = "";
                        String target = "";
                        if (missingBlockType == BlockTypes.STRIPPED_LOG) {
                            LOGGER.info("missingBlockType: " + missingBlockType.name());
                            log = "block." + module.getModId() + "." + material.getName() + "_log";
                            target = module.getModId() + ":stripped_" + material.getName() + "_log";
                            LOGGER.info("Log: " + log);
                        } else if (missingBlockType == BlockTypes.STRIPPED_WOOD) {
                            LOGGER.info("missingBlockType: " + missingBlockType.name());
                            if (module.getModId().equals("betternetherreforged") || module.getModId().equals("betterendforge")) {
                                log = "block." + module.getModId() + "." + material.getName() + "_bark";
                                target = module.getModId() + ":stripped_" + material.getName() + "_bark";
                            } else {
                                log = "block." + module.getModId() + "." + material.getName() + "_bark";
                                target = module.getModId() + ":stripped_" + material.getName() + "_bark";
                            }
                            LOGGER.info("Log: " + log);
                        }
                        if (event.getState().getBlock().getDescriptionId().equals(log)) {
                            LOGGER.info("Strippable Log Found");
                            for (RegistryObject<Block> block : module.BLOCKS_REGISTRY.getEntries()) {
                                LOGGER.info("RegistryObject location: " + block.getId().toString());
                                if (block.getId().toString().equals(target)) {
                                    LOGGER.info("Block found.");
                                    event.setFinalState(block.get().defaultBlockState().setValue(RotatedPillarBlock.AXIS, event.getState().getValue(RotatedPillarBlock.AXIS)));
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


