package Net.Drai.AllTheWood;

import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.misc.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.common.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static java.util.Objects.isNull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AllTheWood.MOD_ID)
public class AllTheWood {
    public static final String MOD_ID = "all_the_wood";
    public static final Logger LOGGER = LogManager.getLogger();

    public static final DeferredRegister<Block> BLOCKS_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, AllTheWood.MOD_ID);
    public static final LinkedHashMap<BlockTypes, BlockType> BLOCK_TYPES = new LinkedHashMap<>();
    public static final DeferredRegister<Item> ITEMS_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, AllTheWood.MOD_ID);
    public static final ArrayList<SimpleModule> MODULES = new ArrayList<SimpleModule>();

    //public static final Map<String, CompatModule> ACTIVE_MODULES = new LinkedHashmap<>();
    public AllTheWood() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::client);

        // Register the setup method for modloading
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        registerModules();
        registerGenerateBlocks();
        BLOCKS_REGISTRY.register(bus);
        ITEMS_REGISTRY.register(bus);
        modulesToString();
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    void client(FMLClientSetupEvent event) {
        if (!isNull(BlockGenerator.cutout)) {
            RenderTypeLookup.setRenderLayer(BlockGenerator.cutout.get(), RenderType.cutout());
        }
    }

    public void modulesToString() {
        for (SimpleModule module : MODULES
        ) {
            LOGGER.info("All The Wood Modules");
            LOGGER.info("New Materials Added by " + module.getModId() + ":");
            for (ATWMaterial material : module.getMATERIALS()) {
                LOGGER.info("-: " + material.getName());
            }
            LOGGER.info("New BlockTypes Added by" + module.getModId() + ":");
            for (BlockType blocktype : module.getBLOCK_TYPES()) {
                LOGGER.info("-: " + BlockType.getName());
            }
        }
    }



    public void registerModules(){
        new Minecraft("minecraft");
        //MODULES.add(new BiomesOPlenty("biomesoplenty"));
    }
    public void registerGenerateBlocks(){
        for (SimpleModule module: MODULES) {
            BlockGenerator.registry(module);
        }

    }
}
