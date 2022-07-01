package Net.Drai.AllTheWood.modules;

import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.tileentitites.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.registries.*;
import org.apache.logging.log4j.*;

import java.util.*;

public class Minecraft extends SimpleModule {
    public static DeferredRegister<TileEntityType<?>> TILE_ENTITY_REGISTRY;
    public static DeferredRegister<Block> BLOCKS_REGISTRY;
    public static DeferredRegister<Item> ITEMS_REGISTRY;
    public static ArrayList<BlockTypes> TILE_ENTITIES = new ArrayList<>();
    public static ArrayList<ATWMaterial> MATERIALS = new ArrayList<>();
    public static final Logger LOGGER = LogManager.getLogger();
    public static ArrayList<BlockType> BLOCK_TYPES = new ArrayList<>();
    public Minecraft(String modId) {

        super(modId);
        BLOCKS_REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, modId);
        ITEMS_REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, modId);
        TILE_ENTITY_REGISTRY = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, modId);
        setBlockRegister(BLOCKS_REGISTRY);
        setItemRegister(ITEMS_REGISTRY);

        registerModBlockTypes();
        setBLOCK_TYPES(BLOCK_TYPES);
        registerMaterials();
        setMATERIALS(MATERIALS);
        registerTileEntities();
        setTILE_ENTITIES(TILE_ENTITIES);
        setTileEntityRegistry(TILE_ENTITY_REGISTRY);
    }

    @Override
    public void registerModBlockTypes() {
        //Planks
        ArrayList<BlockTypes> planksRecipeInput = new ArrayList<>();
        planksRecipeInput.add(BlockTypes.LOG);
        planksRecipeInput.add(BlockTypes.WOOD);
        planksRecipeInput.add(BlockTypes.STRIPPED_WOOD);
        planksRecipeInput.add(BlockTypes.STRIPPED_LOG);
        BLOCK_TYPES.add( new BlockType("planks",BlockTypes.PLANKS, RenderType.solid()
                , ItemGroup.TAB_BUILDING_BLOCKS
                , ATWRecipeTypes.SINGLE_INPUT_SHAPELESS, planksRecipeInput));
        //Slabs
        ArrayList<BlockTypes> slabRecipeInput = new ArrayList<>();
        slabRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("slab",BlockTypes.SLAB, RenderType.cutout()
                , ItemGroup.TAB_BUILDING_BLOCKS, ATWRecipeTypes.SLAB, slabRecipeInput, 6));
        //Stairs
        ArrayList<BlockTypes> stairsRecipeInput = new ArrayList<>();
        stairsRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("stairs",BlockTypes.STAIRS, RenderType.cutout()
                , ItemGroup.TAB_BUILDING_BLOCKS, ATWRecipeTypes.STAIRS, stairsRecipeInput, 4));
        //Fence
        ArrayList<BlockTypes> fenceRecipeInput = new ArrayList<>();
        fenceRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("fence",BlockTypes.FENCE, RenderType.cutout()
                , ItemGroup.TAB_DECORATIONS, ATWRecipeTypes.FENCE, fenceRecipeInput, 3));
        //Fence Gate
        ArrayList<BlockTypes> fenceGateRecipeInput = new ArrayList<>();
        fenceGateRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("fence_gate",BlockTypes.FENCE_GATE, RenderType.cutout()
                , ItemGroup.TAB_DECORATIONS, ATWRecipeTypes.FENCE_GATE, fenceGateRecipeInput));
        //Fence Gate
        ArrayList<BlockTypes> LadderRecipeInput = new ArrayList<>();
        LadderRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("ladder",BlockTypes.LADDER, RenderType.cutout()
                , ItemGroup.TAB_DECORATIONS, ATWRecipeTypes.LADDER, LadderRecipeInput,3));
        //Log
        ArrayList<BlockTypes> LogRecipeInput = new ArrayList<>();
        LogRecipeInput.add(BlockTypes.WOOD);
        BLOCK_TYPES.add(new BlockType("log",BlockTypes.LOG, RenderType.solid()
                , ItemGroup.TAB_BUILDING_BLOCKS, ATWRecipeTypes.VERTICAL_LINE, LogRecipeInput, 4));
        //Stripped Log
        ArrayList<BlockTypes> strippedLogRecipeInput = new ArrayList<>();
        strippedLogRecipeInput.add(BlockTypes.STRIPPED_WOOD);
        BLOCK_TYPES.add(new BlockType("stripped_log",BlockTypes.STRIPPED_LOG, RenderType.solid()
                , ItemGroup.TAB_BUILDING_BLOCKS, ATWRecipeTypes.VERTICAL_LINE, strippedLogRecipeInput, 4));
        ArrayList<BlockTypes> woodRecipeInput = new ArrayList<>();
        woodRecipeInput.add(BlockTypes.LOG);
        BLOCK_TYPES.add(new BlockType("wood",BlockTypes.WOOD, RenderType.solid()
                , ItemGroup.TAB_BUILDING_BLOCKS, ATWRecipeTypes.TWOBYTWO, woodRecipeInput, 3));
        //Stripped Log
        ArrayList<BlockTypes> strippedWoodRecipeInput = new ArrayList<>();
        strippedWoodRecipeInput.add(BlockTypes.STRIPPED_LOG);
        BLOCK_TYPES.add(new BlockType("stripped_wood",BlockTypes.STRIPPED_WOOD, RenderType.solid()
                , ItemGroup.TAB_BUILDING_BLOCKS, ATWRecipeTypes.TWOBYTWO, strippedWoodRecipeInput,3));
        //Pressure plate
        ArrayList<BlockTypes> pressurePlateRecipeInput = new ArrayList<>();
        pressurePlateRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("pressure_plate",BlockTypes.PRESSURE_PLATE, RenderType.cutout()
                , ItemGroup.TAB_REDSTONE, ATWRecipeTypes.PRESSURE_PLATE, pressurePlateRecipeInput));
        //Sign
        ArrayList<BlockTypes> signRecipeInput = new ArrayList<>();
        signRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("sign",BlockTypes.SIGN, RenderType.cutout()
                , ItemGroup.TAB_DECORATIONS, ATWRecipeTypes.SIGN, signRecipeInput));
        //Barrel
        ArrayList<BlockTypes> barrelRecipeInput = new ArrayList<>();
        barrelRecipeInput.add(BlockTypes.PLANKS);
        barrelRecipeInput.add(BlockTypes.SLAB);
        barrelRecipeInput.add(BlockTypes.PARQUET_SLAB);
        BLOCK_TYPES.add(new BlockType("barrel",BlockTypes.BARREL, RenderType.solid()
                , ItemGroup.TAB_DECORATIONS, ATWRecipeTypes.BARREL, barrelRecipeInput));
    }

    @Override
    public void registerMaterials() {
        MATERIALS.add(new ATWWood(getModId(),"oak", MaterialColor.WOOD, ItemGroup.TAB_BUILDING_BLOCKS,
                new ArrayList<>(Arrays.asList(BlockTypes.PRESSURE_PLATE, BlockTypes.LADDER))));
        MATERIALS.add(new ATWWood(getModId(),"spruce", MaterialColor.WOOD, ItemGroup.TAB_BUILDING_BLOCKS,
                new ArrayList<>(Arrays.asList(BlockTypes.LADDER))));
    }

    public void registerTileEntities(){
        TILE_ENTITIES.add(BlockTypes.SIGN);
        TILE_ENTITIES.add(BlockTypes.BARREL);
    }
//    static {
//        MODTILES = new ArrayList();
//        //CHEST = registerTileEntity("chest", TileEntityType.Builder.of(BNChestTileEntity::new, getChests()).build((Type)null));
//        //BARREL = registerTileEntity("barrel", TileEntityType.Builder.of(BNBarrelTileEntity::new, getBarrels()).build((Type)null));
//        SIGN = registerTileEntity("sign", TileEntityType.Builder.of(ATWSignTileEntity::new, ).build((Type)null));
//    }
//
//    public static <T extends TileEntity> TileEntityType<T> registerTileEntity(String name, TileEntityType<T> type) {
//        MODTILES.add(Pair.of(name, type));
//        return type;
//    }
}
