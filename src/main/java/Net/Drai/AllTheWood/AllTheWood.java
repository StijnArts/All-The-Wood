package Net.Drai.AllTheWood;

import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.events.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import Net.Drai.AllTheWood.tileentitites.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.item.Item;
import net.minecraft.tileentity.*;
import net.minecraftforge.common.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.client.registry.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.*;

import net.silentchaos512.gear.init.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

import static java.util.Objects.isNull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AllTheWood.MOD_ID)
public class AllTheWood {
    public static final String MOD_ID = "all_the_wood";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final ArrayList<DeferredRegister<TileEntityType<?>>> TILE_ENTITIE_REGISTRY = new ArrayList<>();
    public static final ArrayList<DeferredRegister<Block>> BLOCKS_REGISTRY= new ArrayList<>();
    public static final ArrayList<DeferredRegister<Item>> ITEM_REGISTRY= new ArrayList<>();
    public static final LinkedHashMap<BlockTypes, BlockType> BLOCK_TYPES = new LinkedHashMap<>();

    public static final ArrayList<SimpleModule> MODULES = new ArrayList<SimpleModule>();

    //public static final Map<String, CompatModule> ACTIVE_MODULES = new LinkedHashmap<>();
    public AllTheWood() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::client);

        // Register the setup method for modloading
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        registerModules();

        modulesToString();
        registerGenerateBlocks();
        registerTileEntities(bus);
        registerModItemRegistry(bus);
        registerModBlockRegistry(bus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(logStripper.class);
        MinecraftForge.EVENT_BUS.register(this);

    }



    void client(FMLClientSetupEvent event) {
        if (!isNull(BlockGenerator.cutout)) {
            RenderTypeLookup.setRenderLayer(BlockGenerator.cutout.get(), RenderType.cutout());
        }
        clientRegistryEntities();
    }

    void clientRegistryEntities() {
        for (SimpleModule module : MODULES) {
            for (BlockTypes tileEntity : module.TILE_ENTITIES) {
                if (tileEntity == BlockTypes.SIGN) {
                    ClientRegistry.bindTileEntityRenderer(TileEntities.SIGN_TILE_ENTITIES.get(),
                            SignTileEntityRenderer::new);
                    Atlases.addWoodType(WoodType.OAK);
                }
            }

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
            LOGGER.info(module.getBLOCK_TYPES().size()+ " New BlockTypes Added by " + module.getModId() + ":");
            for (BlockType blocktype : module.getBLOCK_TYPES()) {
                LOGGER.info("-: " + blocktype.getName());
            }
        }
    }

    public void registerModBlockRegistry(IEventBus bus){
        for (SimpleModule module: MODULES) {
            module.BLOCKS_REGISTRY.register(bus);
        }
    }

    public void registerTileEntities(IEventBus bus){
        for (SimpleModule module: MODULES) {
            module.TILE_ENTITY_REGISTRY.register(bus);
        }
    }

    public void registerModItemRegistry(IEventBus bus){
        for (SimpleModule module: MODULES) {
            module.ITEM_REGISTRY.register(bus);
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
