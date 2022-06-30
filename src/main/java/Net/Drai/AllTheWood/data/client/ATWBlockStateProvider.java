package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.data.*;
import net.minecraft.util.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.*;
import net.minecraftforge.fml.*;
import org.apache.logging.log4j.*;

import java.util.*;

public class ATWBlockStateProvider extends BlockStateProvider {
    public static final Logger LOGGER = LogManager.getLogger();

    public ATWBlockStateProvider(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, AllTheWood.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        LOGGER.info("registerStatesAndModels method Called.");
        for (SimpleModule module : AllTheWood.MODULES) {
            LOGGER.info("module: " + module.getModId());
            for (ATWMaterial material : module.getMATERIALS()) {
                LOGGER.info("material: " + material.getName());
                for (BlockTypes missingBlockType : material.MISSING_BLOCK_TYPES) {
                    LOGGER.info("missingBlockType: " + missingBlockType.name());
                    LOGGER.info(missingBlockType.getGroup().name());
                    for (RegistryObject<Block> block : module.BLOCKS_REGISTRY.getEntries()) {
                        LOGGER.info("RegistryObject location: " + block.getId().toString());
                        String testLocation = "test_block";
                        String primaryLocation = material.getModId()+":"+material.getName() + "_" + missingBlockType.name().toLowerCase(Locale.ROOT);
                        if(missingBlockType == BlockTypes.STRIPPED_LOG){
                            primaryLocation = material.getModId()+":stripped_"+material.getName()+"_log";
                        } else if(missingBlockType == BlockTypes.STRIPPED_WOOD){
                            primaryLocation = material.getModId()+":stripped_"+material.getName()+"_wood";
                        }
                        LOGGER.info("Primary location: " + primaryLocation);
                        LOGGER.info("Test location: " + testLocation);
                        boolean isInPrimaryLocation = block.getId().toString().equals(primaryLocation);
                        ResourceLocation planks = new ResourceLocation(material.getModId(), "block/"+material.getName() + "_planks");
                        ResourceLocation log = new ResourceLocation(material.getModId(), "block/"+material.getName() + "_log");
                        if (missingBlockType.isInGroup(BlockTypes.Group.BLOCKS) && isInPrimaryLocation) {
                            LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());
                            simpleBlock(block.get());
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.SLABS) && isInPrimaryLocation) {
                            LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());
                            if (missingBlockType == BlockTypes.PARQUET_SLAB) {
                                //ResourceLocation planks = new ResourceLocation(module.getModId(), material.getName()+"_planks");
                            }
                            slabBlock((SlabBlock) block.get(), planks, planks);
                        } else if (missingBlockType.isInGroup(BlockTypes.Group.STAIRS) && isInPrimaryLocation){
                            LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());
                            stairsBlock((StairsBlock) block.get(), planks);
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.FENCES) && isInPrimaryLocation){
                            LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());
                            fenceBlock((FenceBlock) block.get(), planks);
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.FENCE_GATES) && isInPrimaryLocation){
                            LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());
                            fenceGateBlock((FenceGateBlock) block.get(), planks);
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.AXIS_BLOCKS) && isInPrimaryLocation){
                            if(missingBlockType == BlockTypes.LOG){
                                axisBlock((RotatedPillarBlock) block.get(), log, new ResourceLocation(material.getModId(),"block/"+material.getName() + "_log_top"));
                            } else if(missingBlockType == BlockTypes.STRIPPED_LOG) {
                                axisBlock((RotatedPillarBlock) block.get(), new ResourceLocation(material.getModId(),"block/stripped_"+material.getName()+"_log"), new ResourceLocation(material.getModId(),"block/stripped_"+material.getName()+"_log_top"));
                            } else if(missingBlockType == BlockTypes.WOOD){
                                axisBlock((RotatedPillarBlock) block.get(), log, log);
                            } else if(missingBlockType == BlockTypes.STRIPPED_WOOD){
                                ResourceLocation stripped = new ResourceLocation(material.getModId(), "block/stripped_"+material.getName()+"_log");
                                axisBlock((RotatedPillarBlock) block.get(), stripped, stripped);

                            }
                            LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());

                        }


                        else if(missingBlockType.isInGroup(BlockTypes.Group.NO_GROUP) && isInPrimaryLocation){
                            LOGGER.info("missingBlockType Was in " + BlockTypes.Group.BLOCKS.name());
                            if(missingBlockType == BlockTypes.LADDER){
                                LOGGER.info("missingBlockType Was in " + missingBlockType);
                                ladderBlock((LadderBlock) block.get(),material.getName(), material.getModId());
                            } else if(missingBlockType == BlockTypes.PRESSURE_PLATE){
                                LOGGER.info("missingBlockType Was in " + missingBlockType);
                                pressurePlateBlock((PressurePlateBlock) block.get(),material.getName(),planks);
                            } else if(missingBlockType == BlockTypes.SIGN){
                                LOGGER.info("missingBlockType Was in " + missingBlockType);
                                signBlock(block.get(),material.getName(),module.getModId());
                            }
                        }
                        else if (block.getId().equals(modLoc(testLocation))) {
                            LOGGER.info("Match Found in Test");
                            simpleBlock(block.get());
                        } else{
                            LOGGER.info("No Match Found.");
                        }
                    }
                }
            }
        }
    }

    private void signBlock(Block block, String material, String modId) {
        ModelFile texture = (models().getBuilder(material + "_sign")).texture("particle", new ResourceLocation(modId, "block/" + material + "_planks"));
        this.simpleBlock(block, texture);
    }

    private void pressurePlateBlock(PressurePlateBlock block, String material, ResourceLocation texture){
        ModelFile plate = models().singleTexture(material+"_pressure_plate", mcLoc("block/pressure_plate_up"),texture);
        ModelFile plateDown = models().singleTexture(material+"_pressure_plate_down", mcLoc("block/pressure_plate_down"),texture);
        getVariantBuilder(block).forAllStates(blockState -> {
            boolean powered = blockState.getValue(PressurePlateBlock.POWERED);
            return ConfiguredModel.builder().modelFile(powered ? plateDown : plate).build();
                });
    }

    private void ladderBlock(LadderBlock block, String material, String modId){
        LOGGER.info("Ladder Block Method Called.");
        LOGGER.info("name: "+material + "_ladder");
        LOGGER.info("parent: "+ modId+":block/ladder");
        LOGGER.info("texture: "+ modId+":block/" + material + "_ladder");
        ModelFile ladder;
        if(modId != "minecraft" && material != "oak") {
            ladder = (this.models().withExistingParent(material + "_ladder", modId + ":block/ladder")).texture("texture", modId + ":block/" + material + "_ladder");
        } else {
            ladder = (this.models().withExistingParent(material + "_ladder", modId + ":block/ladder")).texture("texture", modId + ":block/ladder");
        }
        this.horizontalBlock(block, ladder);
    }
}
