package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.data.*;
import net.minecraft.item.*;
import net.minecraft.tags.*;
import net.minecraft.util.*;
import net.minecraftforge.common.*;
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
            modId = module.getModId();
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
                        ResourceLocation input = new ResourceLocation(material.getModId(), material.getName()+"_"+AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput.get(0).name().toLowerCase(Locale.ROOT));
                        LOGGER.info("Input item: "+input);
                        ResourceLocation output = new ResourceLocation(material.getModId(), material.getName() + "_" + AllTheWood.BLOCK_TYPES.get(missingBlockType).getName().toLowerCase(Locale.ROOT));
                        LOGGER.info("Recipe output will be: "+output);
                        LOGGER.info("missingBlockType recipe was a " + recipe.name().toLowerCase(Locale.ROOT)+" recipe.");
                        if (recipe == ATWRecipeTypes.TWOBYTWO) {
                            if(missingBlockType == BlockTypes.STRIPPED_WOOD){
                                output = new ResourceLocation(material.getModId(), "stripped_"+material.getName()+"_wood");
                                input =new ResourceLocation(material.getModId(), "stripped_"+material.getName()+"_log");
                            }
                            make2By2Recipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if(recipe == ATWRecipeTypes.SINGLE_INPUT_SHAPELESS){
                            LOGGER.info(ATWRecipeTypes.SINGLE_INPUT_SHAPELESS+" Found.");
                            if(missingBlockType == BlockTypes.PLANKS){
                                LOGGER.info(missingBlockType + " found.");
                                LOGGER.info("Amount of input items: "+ AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput.size());
                                for(BlockTypes inputBlockType :AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput){
                                    LOGGER.info(missingBlockType.getGroup().name()+" Found.");
                                    LOGGER.info("Input Item Found.");
                                    input = new ResourceLocation(material.getModId(), material.getName()+"_"+AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput.get(0).name().toLowerCase(Locale.ROOT));
                                    if(inputBlockType == BlockTypes.WOOD){
                                        if(Objects.equals(module.getModId(), "betternetherreforged") || Objects.equals(module.getModId(), "betterendforge")){
                                            input = new ResourceLocation(material.getModId(), material.getName()+"_bark");
                                        }else {
                                            input = new ResourceLocation(material.getModId(), material.getName()+"_wood");
                                        }
                                    } else if(inputBlockType == BlockTypes.STRIPPED_WOOD){
                                        if(Objects.equals(module.getModId(), "betternetherreforged") || Objects.equals(module.getModId(), "betterendforge")){
                                            input = new ResourceLocation(material.getModId(), "stripped_"+material.getName()+"_bark");
                                        } else {
                                            input = new ResourceLocation(material.getModId(), "stripped_"+material.getName()+"_wood");
                                        }
                                    } else if(inputBlockType == BlockTypes.STRIPPED_LOG){
                                        input = new ResourceLocation(material.getModId(), "stripped_"+material.getName()+"_log");
                                    }
                                    LOGGER.info("Plank input: "+input);
                                    makeShapelessRecipe(input,output,AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(),consumer);
                                }
                            }
                        } else if(recipe == ATWRecipeTypes.SLAB){
                            LOGGER.info(ATWRecipeTypes.SLAB+" Found.");
                            makeSlabRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if(recipe == ATWRecipeTypes.STAIRS){
                            LOGGER.info(ATWRecipeTypes.STAIRS+" Found.");
                            makeStairsRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if(recipe == ATWRecipeTypes.FENCE){
                            LOGGER.info(ATWRecipeTypes.FENCE+" Found.");
                            makeFenceRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if(recipe == ATWRecipeTypes.FENCE_GATE){
                            LOGGER.info(ATWRecipeTypes.FENCE_GATE+" Found.");
                            makeFenceGateRecipe(input, output, consumer);
                        } else if(recipe == ATWRecipeTypes.VERTICAL_LINE){
                            LOGGER.info(ATWRecipeTypes.VERTICAL_LINE+" Found.");
                            if(missingBlockType == BlockTypes.STRIPPED_LOG){
                                LOGGER.info("input modified for stripped variant.");
                                output = new ResourceLocation(material.getModId(), "stripped_"+material.getName()+"_log");
                                input =new ResourceLocation(material.getModId(), "stripped_"+material.getName()+"_wood");
                            }
                            makeVerticalLineRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if(recipe == ATWRecipeTypes.LADDER){
                            LOGGER.info(recipe+" Found.");
                            if(Objects.equals(material.getModId(), "minecraft") && Objects.equals(material.getName(), "oak")){
                                output = new ResourceLocation(material.getModId(), AllTheWood.BLOCK_TYPES.get(missingBlockType).getName().toLowerCase(Locale.ROOT));
                                LOGGER.info("output :"+output);
                            }
                            makeLadderRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if(recipe == ATWRecipeTypes.PRESSURE_PLATE){
                            LOGGER.info(recipe+" Found.");
                            makePressurePlateRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if(recipe == ATWRecipeTypes.SIGN){
                            LOGGER.info(recipe+" Found.");
                            makeSignRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        } else if (recipe == ATWRecipeTypes.ROUND){
                            LOGGER.info(recipe+" Found.");
                            makeRoundRecipe(input, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                        }
                    } else if(AllTheWood.BLOCK_TYPES.get(missingBlockType).getRecipeType().isInGroup(ATWRecipeTypes.Group.TWO_INPUT)){
                        LOGGER.info("missingBlockType recipe was in the " + recipe.getGroup()+" group.");
                        //String name = missingBlockType.name().toLowerCase(Locale.ROOT);
                        ResourceLocation input = new ResourceLocation(material.getModId(), material.getName()+"_"+AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput.get(0).name().toLowerCase(Locale.ROOT));
                        LOGGER.info("Input item: "+input);
                        ResourceLocation output = new ResourceLocation(material.getModId(), material.getName() + "_" + AllTheWood.BLOCK_TYPES.get(missingBlockType).getName().toLowerCase(Locale.ROOT));
                        LOGGER.info("Recipe output will be: "+output);
                        LOGGER.info("missingBlockType recipe was a " + recipe.name().toLowerCase(Locale.ROOT)+" recipe.");
                        ResourceLocation input2 = new ResourceLocation(material.getModId(), material.getName() + "_" + AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput.get(1).name().toLowerCase(Locale.ROOT));
                        if (recipe == ATWRecipeTypes.BARREL){
                            if(Objects.equals(material.getModId(), "minecraft") && Objects.equals(material.getName(), "oak")){
                                output = new ResourceLocation(material.getModId(), AllTheWood.BLOCK_TYPES.get(missingBlockType).getName().toLowerCase(Locale.ROOT));
                                LOGGER.info("output :"+output);
                            }
                            for(int i = 1; i < AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput.size();i++){
                                if(i>1){
                                    input2 = new ResourceLocation(material.getModId(), material.getName()+"_"+AllTheWood.BLOCK_TYPES.get(missingBlockType).recipeInput.get(i).name().toLowerCase(Locale.ROOT));
                                }
                                makeBarrelRecipe(input, input2, output, AllTheWood.BLOCK_TYPES.get(missingBlockType).getQuantityOut(), consumer);
                            }
                        }
                    }
                }
            }
        }
    }

    private void makeRoundRecipe(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        //logParameters(input, output, outputQuanity, consumer);
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("###").pattern("# #").pattern("###").define('#', ingredient).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result) + "_from_" + getPath(ingredient));
    }

    private void makeBarrelRecipe(ResourceLocation input, ResourceLocation slabInput, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        //logParameters(input, output, outputQuanity, consumer);
        IItemProvider plank = getRegisteredItem(input);
        IItemProvider slab = getRegisteredItem(slabInput);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("#I#").pattern("# #").pattern("#I#").define('#', plank).define('I', slab).group(modId).unlockedBy(getPath(slab), has(slab)).save(consumer, getPath(result) + "_from_" + getPath(slab));
    }

    private void makeSignRecipe(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        //logParameters(input, output, outputQuanity, consumer);
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("###").pattern("###").pattern(" I ").define('#', ingredient).define('I', Items.STICK).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result) + "_from_" + getPath(ingredient));
    }

    private void makePressurePlateRecipe(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer) {
        //logParameters(input, output, outputQuanity, consumer);
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("##").define('#', ingredient).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result) + "_from_" + getPath(ingredient));
    }

    private void makeVerticalLineRecipe(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer) {
        //logParameters(input, output, outputQuanity, consumer);
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("#").pattern("#").pattern("#").define('#', ingredient).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result) + "_from_" + getPath(ingredient));
    }

    private void makeLadderRecipe(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer) {
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("I I").pattern("I#I").pattern("I I").define('#', ingredient).define('I', Items.STICK).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result) + "_from_" + getPath(ingredient));
    }

    private void makeFenceGateRecipe(ResourceLocation input, ResourceLocation output, Consumer<IFinishedRecipe> consumer) {
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result).pattern("I#I").pattern("I#I").define('#', ingredient).define('I', Items.STICK).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result) + "_from_" + getPath(ingredient));
    }

    public static void makeShapelessRecipe(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapelessRecipeBuilder.shapeless(result, outputQuanity).requires(ingredient).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result)+"_from_"+getPath(ingredient));
    }

    public static void makeSlabRecipe(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("###").define('#', ingredient).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result)+"_from_"+getPath(ingredient));
    }

    public static void makeFenceRecipe(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("#I#").pattern("#I#").define('#', ingredient).define('I', Items.STICK).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result)+"_from_"+getPath(ingredient));
    }
    public static void make2By2Recipe(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("##").pattern("##").define('#', ingredient).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result)+"_from_"+getPath(ingredient));
    }

    public static void makeStairsRecipe(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        IItemProvider ingredient = getRegisteredItem(input);
        IItemProvider result = getRegisteredItem(output);
        ShapedRecipeBuilder.shaped(result, outputQuanity).pattern("#  ").pattern("## ").pattern("###").define('#', ingredient).group(modId).unlockedBy(getPath(ingredient), has(ingredient)).save(consumer, getPath(result)+"_from_"+getPath(ingredient));
    }

    private static IItemProvider getRegisteredItem(ResourceLocation loc) {
        return ForgeRegistries.ITEMS.getValue(loc);
    }
    private static String getPath(IItemProvider item) {
        return item.asItem().getRegistryName().getPath();
    }

    private static void logParameters(ResourceLocation input, ResourceLocation output, int outputQuanity, Consumer<IFinishedRecipe> consumer){
        LOGGER.info("Input: "+input);
        LOGGER.info("Output: "+output);
        LOGGER.info("Output Quanity: "+outputQuanity);
    }
}
