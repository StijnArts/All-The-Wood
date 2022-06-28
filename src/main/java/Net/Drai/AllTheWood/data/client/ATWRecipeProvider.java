package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.data.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraftforge.registries.*;
import org.apache.logging.log4j.*;

import java.util.*;
import java.util.function.Consumer;

public class ATWRecipeProvider extends RecipeProvider {
    public static final Logger LOGGER = LogManager.getLogger();
    private static String modId = "all_the_wood";
    public ATWRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        LOGGER.info("buildShapelessRecipes method Called.");
        for (SimpleModule module : AllTheWood.MODULES) {
            LOGGER.info("module: " + module.getModId());
            for (ATWMaterial material : module.getMATERIALS()) {
                LOGGER.info("material: " + material.getName());

                for (BlockTypes missingBlockType : material.MISSING_BLOCK_TYPES) {
                    LOGGER.info("missingBlockType: " + missingBlockType.name());
                    LOGGER.info(missingBlockType.getGroup().name());
                    //boolean isInPrimaryLocation = block.getId().equals(AllTheWood.MOD_ID+":"+primaryLocation);
                    //if(isInPrimaryLocation) {
                    ATWRecipeTypes recipe = AllTheWood.BLOCK_TYPES.get(missingBlockType).getRecipeType();
                    if(recipe.isInGroup(ATWRecipeTypes.Group.SINGLE_INPUT)) {
                        LOGGER.info("missingBlockType recipe was in the " + recipe.getGroup()+" group.");
                        String name = missingBlockType.name().toLowerCase(Locale.ROOT);
                        ResourceLocation input = new ResourceLocation(material.getModId(), material.getName()+"_"+AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput.get(0).name().toLowerCase(Locale.ROOT));
                        LOGGER.info("Input item: "+input);
                        String output = material.getName() + "_" + AllTheWood.BLOCK_TYPES.get(missingBlockType).getName().toLowerCase(Locale.ROOT);
                        LOGGER.info("Recipe output will be: "+output);
                        LOGGER.info("missingBlockType recipe was a " + recipe.name().toLowerCase(Locale.ROOT)+" recipe.");
                        if (recipe == ATWRecipeTypes.TWOBYTWO) {
                            make2By2Recipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
//TODO change from "Testblock" to output
                        } else if(recipe == ATWRecipeTypes.SINGLE_INPUT_SHAPELESS){
                            if(missingBlockType == BlockTypes.PLANKS){
                                LOGGER.info(missingBlockType + " found.");
                                LOGGER.info("Amount of input items: "+ AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput.size());
                                for(BlockTypes inputBlockType :AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput){
                                    LOGGER.info("Input Item Found.");
                                    input = new ResourceLocation(material.getModId(), material.getName()+"_"+AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput.get(0).name().toLowerCase(Locale.ROOT));
                                    if(inputBlockType == BlockTypes.WOOD){
                                        if(module.getModId() == "betternetherreforged"|| module.getModId() == "betterendforge"){
                                            input = new ResourceLocation(material.getModId(), material.getName()+"_bark");
                                        }else {
                                            input = new ResourceLocation(material.getModId(), material.getName()+"_wood");
                                        }
                                    } else if(inputBlockType == BlockTypes.STRIPPED_WOOD){
                                        if(module.getModId() == "betternetherreforged"|| module.getModId() == "betterendforge"){
                                            input = new ResourceLocation(material.getModId(), "stripped_"+material.getName()+"_bark");
                                        } else {
                                            input = new ResourceLocation(material.getModId(), "stripped_"+material.getName()+"_wood");
                                        }
                                    } else if(inputBlockType == BlockTypes.STRIPPED_LOG){
                                        input = new ResourceLocation(material.getModId(), "stripped_"+material.getName()+"_log");
                                    }
                                    LOGGER.info("Plank input: "+input);
                                    makeShapelessRecipe(input,output,AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(),4,consumer);
                                }
                            }
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.SLABS)){
                            makeSlabRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.STAIRS)){
                            makeStairsRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.FENCES)){
                            makeFenceRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if(missingBlockType.isInGroup(BlockTypes.Group.FENCE_GATES)){
                            LOGGER.info("Fence Gate Found.");
                            makeFenceGateRecipe(input, output, consumer);
                        }
                    } else if(AllTheWood.BLOCK_TYPES.get(missingBlockType).getRecipeType().isInGroup(ATWRecipeTypes.Group.TWO_INPUT)){

                    }
                }
            }
        }
    }

    private void makeFenceGateRecipe(ResourceLocation input, String output, Consumer<IFinishedRecipe> consumer) {
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(modLoc(output));
        ShapedRecipeBuilder.shaped(result).pattern("I#I").pattern("I#I").define('#', ingredient).define('I', Items.STICK).group("betternether").unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result) + "_from_" + getPath(ingredient));
    }

    public static void makeShapelessRecipe(ResourceLocation input, String output, int outputQuanity, int inputQuanity, Consumer<IFinishedRecipe> consumer){
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(modLoc(output));
        ShapelessRecipeBuilder.shapeless(result, outputQuanity).requires(ingredient, inputQuanity).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result)+"_from_"+getPath(ingredient));
    }

    public static void makeSlabRecipe(ResourceLocation input, String output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(modLoc(output));
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("###").define('#', ingredient).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result)+"_from_"+getPath(ingredient));
    }

    public static void makeFenceRecipe(ResourceLocation input, String output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(modLoc(output));
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("#I#").pattern("#I#").define('#', ingredient).define('I', Items.STICK).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result)+"_from_"+getPath(ingredient));
    }
    public static void make2By2Recipe(ResourceLocation input, String output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(modLoc(output));
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("##").pattern("##").define('#', ingredient).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result)+"_from_"+getPath(ingredient));
    }

    public static void makeStairsRecipe(ResourceLocation input, String output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(modLoc(output));
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("#  ").pattern("## ").pattern("###").define('#', ingredient).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result)+"_from_"+getPath(ingredient));
    }

    private static ResourceLocation modLoc(String name){
        return new ResourceLocation(modId,name);
    }

    private static IItemProvider getRegisteredItem(ResourceLocation loc) {
        return ForgeRegistries.ITEMS.getValue(loc);
    }
    private static String getPath(IItemProvider item) {
        return item.asItem().getRegistryName().getPath();
    }
}
