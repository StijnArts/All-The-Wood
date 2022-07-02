package Net.Drai.AllTheWood;

import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.block.blocks.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.events.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import Net.Drai.AllTheWood.tileentitites.*;
import Net.Drai.AllTheWood.tileentitites.renderer.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.item.Item;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
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

import java.rmi.registry.*;
import java.util.*;

import static java.util.Objects.isNull;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AllTheWood.MOD_ID)
public class AllTheWood {
    public static final String MOD_ID = "all_the_wood";
    public static final Logger LOGGER = LogManager.getLogger();
    public static ArrayList<WoodType> WOOD_TYPES = new ArrayList<>();
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
        registerModBlockRegistry(bus);
        registerModItemRegistry(bus);
        registerTileEntities(bus);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(logStripper.class);
        MinecraftForge.EVENT_BUS.register(this);

    }



    void client(FMLClientSetupEvent event) {
        if (!isNull(BlockGenerator.cutout)) {
            LOGGER.info("Started Cutout render type registration");
            for (RegistryObject<Block> cutoutBlock : BlockGenerator.cutout){
                LOGGER.info(cutoutBlock.getId().toString()+" Registered as a cutout render Type.");
                LOGGER.info(cutoutBlock.get().getDescriptionId()+" Registered as a cutout render Type.");
                RenderTypeLookup.setRenderLayer(cutoutBlock.get(), RenderType.cutout());
            }
        }
        if (!isNull(BlockGenerator.translucent)) {
            LOGGER.info("Started Translucent render type registration");
            for (RegistryObject<Block> cutoutBlock : BlockGenerator.translucent){
                LOGGER.info(cutoutBlock.getId().toString()+" Registered as a translucent render Type.");
                RenderTypeLookup.setRenderLayer(cutoutBlock.get(), RenderType.translucent());
            }
        }
        for (SimpleModule module : AllTheWood.MODULES) {
            for (ATWMaterial material : module.getMATERIALS()) {
                for (BlockTypes missingBlockType : material.MISSING_BLOCK_TYPES) {
                    for (RegistryObject<Block> block : module.BLOCKS_REGISTRY.getEntries()) {
                        if (block.get() instanceof ATWChestBlock) {
                            String primaryLocation = material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT);
                            boolean isInPrimaryLocation = block.getId().toString().equals(primaryLocation);
                            if(isInPrimaryLocation){
                                ATWChestTileEntityRenderer.LAYERS.put(block.get(), new RenderType[]{RenderType.entitySolid(new ResourceLocation(material.getModId(), "textures/entity/chest/" + material.getName() + "/normal.png")), RenderType.entitySolid(new ResourceLocation(material.getModId(), "textures/entity/chest/" + material.getName()  + "/left.png")), RenderType.entitySolid(new ResourceLocation(material.getModId(), "textures/entity/chest/" + material.getName()  + "/right.png"))});
                            }
                        }
                    }
                }
            }
        }

        clientRegistryEntities();
    }

    void clientRegistryEntities() {
        boolean newSignEntitiesAdded = false;
        boolean newBarrelEntitiesAdded = false;
        boolean newChestEntitiesAdded = false;
        WoodType woodType = WoodType.OAK;
        for (SimpleModule module : MODULES) {
            for (ATWMaterial material: module.getMATERIALS()) {
                for(BlockTypes missingBlockType: material.MISSING_BLOCK_TYPES){
                    if(missingBlockType == BlockTypes.SIGN){
                        newSignEntitiesAdded = true;
                        woodType = material.woodType;
                    } else if(missingBlockType == BlockTypes.BARREL){
                        //newBarrelEntitiesAdded = true;
                    }
                }
            }
        }
        if (newSignEntitiesAdded) {
            ClientRegistry.bindTileEntityRenderer(TileEntities.SIGN_TILE_ENTITIES.get(),
                    SignTileEntityRenderer::new);
            Atlases.addWoodType(woodType);
        } else if (newChestEntitiesAdded) {
            ClientRegistry.bindTileEntityRenderer(TileEntities.CHEST_TILE_ENTITIES.get(),
                    ATWChestTileEntityRenderer::new);
            Atlases.addWoodType(woodType);
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
            LOGGER.info("Registring items for module: "+module.getModId());
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
