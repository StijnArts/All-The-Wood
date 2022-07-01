package Net.Drai.AllTheWood.block;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.blocks.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.misc.*;
import Net.Drai.AllTheWood.modules.*;
//import com.codenamerevy.additionalbars.common.content.block.*;

import Net.Drai.AllTheWood.tileentitites.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;

import com.google.common.base.Supplier;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.*;
import org.apache.logging.log4j.*;
//import someoneelse.betternetherreforged.blocks.*;

import java.util.*;

public class BlockGenerator {
    public static ArrayList<RegistryObject<Block>> cutout_mipped, translucent_moving_block, translucent_no_crumbling;
    public static ArrayList<RegistryObject<Block>> solid = new ArrayList<>();
    public static ArrayList<RegistryObject<Block>> cutout = new ArrayList<>();
    public static ArrayList<RegistryObject<Block>> translucent = new ArrayList<>();
    public static final Logger LOGGER = LogManager.getLogger();
    public static void registry(SimpleModule module) {
        LOGGER.info("Block Generation started.");
        LOGGER.info("module: " + module.getModId());
//        cutout = createBlock("test_block",
//                () -> new Block(AbstractBlock.Properties.of(Material.WOOD, MaterialColor.WOOD).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F))
//                , ItemGroup.TAB_FOOD, module.BLOCKS_REGISTRY,module.ITEM_REGISTRY);
            for (ATWMaterial material : module.getMATERIALS()) {
                LOGGER.info("material: " + material.getName());
                for(BlockTypes missingBlockType: material.MISSING_BLOCK_TYPES){
                    LOGGER.info("missingBlockType: " + missingBlockType.name());
                    String name = AllTheWood.BLOCK_TYPES.get(missingBlockType).getName().toLowerCase(Locale.ROOT);
                    ItemGroup itemGroup;
                    if(AllTheWood.BLOCK_TYPES.get(missingBlockType).getItemGroup() != null){
                        itemGroup= AllTheWood.BLOCK_TYPES.get(missingBlockType).getItemGroup();
                    } else {
                        itemGroup= material.getItemGroup();
                    }
                    String blockname = material.getName() + "_" + name;
                    if((missingBlockType == BlockTypes.BARREL || missingBlockType == BlockTypes.LADDER) && (material.getModId().equals("minecraft") && material.getName().equals("oak"))){
                        blockname = name;
                    }
                    LOGGER.info("Missing Block Type in group: "+missingBlockType.getGroup());
                    if (missingBlockType.isInGroup(BlockTypes.Group.BLOCKS)) {
                        LOGGER.info(missingBlockType.getGroup().name()+" Found.");
                        solid.add(createBlock(blockname,
                                () -> new Block(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()))
                                , itemGroup, module.BLOCKS_REGISTRY,module.ITEM_REGISTRY));

                    } else if (missingBlockType.isInGroup(BlockTypes.Group.SLABS)){
                        LOGGER.info(missingBlockType.getGroup().name()+" Found.");
                        cutout.add(createBlock(blockname,
                                () -> new SlabBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()))
                                , itemGroup, module.BLOCKS_REGISTRY,module.ITEM_REGISTRY));

                    } else if (missingBlockType.isInGroup(BlockTypes.Group.STAIRS)){
                        LOGGER.info(missingBlockType.getGroup().name()+" Found.");
                        cutout.add(createBlock(blockname,
                                () -> new StairsBlock(() -> material.getBlockState(), AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()))
                                , itemGroup, module.BLOCKS_REGISTRY,module.ITEM_REGISTRY));

                    } else if (missingBlockType.isInGroup(BlockTypes.Group.FENCES)){
                        LOGGER.info(missingBlockType.getGroup().name()+" Found.");
                        cutout.add(createBlock(blockname,
                                () -> new FenceBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()))
                                , itemGroup, module.BLOCKS_REGISTRY,module.ITEM_REGISTRY));

                    } else if (missingBlockType.isInGroup(BlockTypes.Group.FENCE_GATES)) {
                        LOGGER.info(missingBlockType.getGroup().name() + " Found.");
                        cutout.add(createBlock(blockname,
                                () -> new FenceGateBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F))
                                , itemGroup, module.BLOCKS_REGISTRY, module.ITEM_REGISTRY));

                    } else if(missingBlockType.isInGroup(BlockTypes.Group.AXIS_BLOCKS)) {
                        LOGGER.info(missingBlockType.getGroup().name() + " Found.");
                        LOGGER.info(missingBlockType.name() + " Found.");
                        if(missingBlockType==BlockTypes.STRIPPED_LOG) {
                            blockname = "stripped_"+material.getName() + "_log";
                        } else if(missingBlockType==BlockTypes.STRIPPED_WOOD){
                            blockname = "stripped_"+material.getName() + "_wood";
                        }
                        LOGGER.info("Blockname: "+blockname);
                        solid.add(createBlock(blockname,
                                () -> new RotatedPillarBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()))
                                , itemGroup, module.BLOCKS_REGISTRY,module.ITEM_REGISTRY));
                    }

                    else if(missingBlockType.isInGroup(BlockTypes.Group.NO_GROUP)){
                        LOGGER.info(missingBlockType.getGroup().name()+" block Found.");
                        if (missingBlockType == BlockTypes.LADDER){
                            LOGGER.info(missingBlockType.name() + " Found.");
                            //LOGGER.info("Added block to Translucent RenderType");
                            LOGGER.info(blockname);
                            cutout.add(createBlock(blockname,
                                    () -> new LadderBlock(AbstractBlock.Properties.copy(Blocks.LADDER).harvestTool(ToolType.AXE))
                                    , itemGroup, module.BLOCKS_REGISTRY,module.ITEM_REGISTRY));
                            } else if(missingBlockType == BlockTypes.PRESSURE_PLATE){
                            cutout.add(createBlock(blockname,
                                    () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING,AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F).noCollission())
                                    , itemGroup, module.BLOCKS_REGISTRY,module.ITEM_REGISTRY));
                            } else if(missingBlockType == BlockTypes.SIGN){
                                TileEntities.SIGN_TILE_ENTITIES = createSignTileEntity(blockname, module, material, itemGroup);
                            } else if(missingBlockType == BlockTypes.BARREL){
                                TileEntities.BARREL_TILE_ENTITIES = createBarrelBlock(blockname, module, material, itemGroup);
                            }
                        }

                    /* Example
                     * cutout = createBlock("" + material + "_" + name,
                            () -> new PaneBlock(), itemGroup);
                        * */

                    //TODO Add a register script for each block type
                    /*if (missingBlockType == BlockTypes.BARS || missingBlockType == BlockTypes.BARS_CROSSED) {
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new PaneBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()).noOcclusion().isValidSpawn(BlockGenerator::never).isRedstoneConductor(BlockGenerator::never).isSuffocating(BlockGenerator::never).isViewBlocking(BlockGenerator::never))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.BARS_CROSSED_HORIZONTAL || missingBlockType == BlockTypes.BARS_HORIZONTAL){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new HorizontalBarsSlabBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper()).noOcclusion().isValidSpawn(BlockGenerator::never).isRedstoneConductor(BlockGenerator::never).isSuffocating(BlockGenerator::never).isViewBlocking(BlockGenerator::never))
                                , itemGroup);

                    } else if (missingBlockType == BlockTypes.BAR_STOOL) {
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new BNBarStool(new SlabBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper())))
                                , itemGroup);
                    } else if (missingBlockType == BlockTypes.CHAIR){
                        cutout = createBlock("" + material.getName() + "_" + name,
                                () -> new BNNormalChair(new SlabBlock(AbstractBlock.Properties.of(material.getMaterial(), material.getMaterialColor()).sound(material.getSoundType()).harvestTool(material.getToolType()).harvestLevel(material.getHarvestLevel()).strength(material.getStrengthLower(),material.getStrengthUpper())))
                                , itemGroup);
                    } else */
                }
            }
    }
    public static RegistryObject<TileEntityType<ATWBarrelTileEntity>> createBarrelBlock(String blockname, SimpleModule module, ATWMaterial material, ItemGroup itemGroup){
        RegistryObject<Block> block = createBlock(blockname,
                () -> new ATWBarrel(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F))
                ,itemGroup,module.BLOCKS_REGISTRY,module.ITEM_REGISTRY);
        solid.add(block);
        RegistryObject<TileEntityType<ATWBarrelTileEntity>> barrel = module.TILE_ENTITY_REGISTRY.register(blockname, () -> TileEntityType.Builder.of(ATWBarrelTileEntity::new,
                block.get()
        ).build(null));
        return(barrel);
    }

    public static RegistryObject<TileEntityType<ATWSignTileEntity>> createSignTileEntity(String blockname, SimpleModule module, ATWMaterial material, ItemGroup itemGroup){
        RegistryObject<Block> wall = createSignBlock(material.getName()+"_wall_sign",
                () -> new ATWWallSignBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F).noCollission().noOcclusion(), material.woodType)
                , module.BLOCKS_REGISTRY);
        RegistryObject<Block> standing = createSignBlock(blockname,
                () -> new ATWStandingSignBlock(AbstractBlock.Properties.of(Material.WOOD, material.getMaterialColor()).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F).noCollission().noOcclusion(), material.woodType)
                , module.BLOCKS_REGISTRY);
        createSignItem(blockname,itemGroup,module.ITEM_REGISTRY, wall, standing);
        cutout.add(wall);
        cutout.add(standing);
        RegistryObject<TileEntityType<ATWSignTileEntity>> sign = module.TILE_ENTITY_REGISTRY.register(blockname, () -> TileEntityType.Builder.of(ATWSignTileEntity::new,
                        standing.get(),
                        wall.get()
                ).build(null));
        return(sign);
    }
    public static void createSignItem(String name, ItemGroup tab, DeferredRegister<Item> ITEM_REGISTER, RegistryObject<Block> wall, RegistryObject<Block> standing)
    {
        ITEM_REGISTER.register(name,() -> new SignItem(new Item.Properties().stacksTo(16).tab(tab),standing.get(),wall.get()));
    }
    public static RegistryObject<Block> createSignBlock(String name, Supplier<? extends Block> supplier, DeferredRegister<Block> BLOCK_REGISTER)
    {
        RegistryObject<Block> block = BLOCK_REGISTER.register(name, supplier);
        return block;
    }
    public static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier, ItemGroup tab, DeferredRegister<Block> BLOCK_REGISTER,DeferredRegister<Item> ITEM_REGISTER)
    {
        RegistryObject<Block> block = BLOCK_REGISTER.register(name, supplier);
        ITEM_REGISTER.register(name, () -> new FuelItemBlock(block.get(), new Item.Properties().tab(tab)));
        return block;
    }

    private static Boolean never(BlockState blockState, IBlockReader blockView, BlockPos blockPos, EntityType<?> entityType) {
        return false;
    }

    private static boolean never(BlockState blockState, IBlockReader blockView, BlockPos blockPos) {
        return false;
    }
}
