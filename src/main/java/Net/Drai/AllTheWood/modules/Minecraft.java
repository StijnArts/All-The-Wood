package Net.Drai.AllTheWood.modules;

import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.material.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public class Minecraft extends SimpleModule {
    public static ArrayList<ATWMaterial> MATERIALS = new ArrayList<>();
    public ArrayList<BlockType> BLOCK_TYPES = new ArrayList<>();
    public Minecraft(String modId) {
        super(modId);
        registerModBlockTypes();
        setBLOCK_TYPES(BLOCK_TYPES);
        registerMaterials();
        setMATERIALS(MATERIALS);
    }

    @Override
    public void registerModBlockTypes() {
        //Planks
        ArrayList<BlockTypes> planksRecipeInput = new ArrayList<>();
        planksRecipeInput.add(BlockTypes.LOG);
        planksRecipeInput.add(BlockTypes.WOOD);
        planksRecipeInput.add(BlockTypes.STRIPPED_WOOD);
        planksRecipeInput.add(BlockTypes.STRIPPED_LOG);
        BLOCK_TYPES.add(new BlockType("planks",BlockTypes.PLANKS, RenderType.solid(), ItemGroup.TAB_BUILDING_BLOCKS, ATWRecipeTypes.SINGLE_INPUT_SHAPELESS, planksRecipeInput));
        //Slabs
        ArrayList<BlockTypes> slabRecipeInput = new ArrayList<>();
        slabRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("slab",BlockTypes.SLAB, RenderType.cutout(), ItemGroup.TAB_BUILDING_BLOCKS, ATWRecipeTypes.SLAB, slabRecipeInput));
        //Stairs
        ArrayList<BlockTypes> stairsRecipeInput = new ArrayList<>();
        stairsRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("stairs",BlockTypes.STAIRS, RenderType.cutout(), ItemGroup.TAB_BUILDING_BLOCKS, ATWRecipeTypes.STAIRS, stairsRecipeInput));
        //Fence
        ArrayList<BlockTypes> fenceRecipeInput = new ArrayList<>();
        fenceRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("fence",BlockTypes.FENCE, RenderType.cutout(), ItemGroup.TAB_DECORATIONS, ATWRecipeTypes.FENCE, fenceRecipeInput));
        //Fence Gate
        ArrayList<BlockTypes> fenceGateRecipeInput = new ArrayList<>();
        fenceGateRecipeInput.add(BlockTypes.PLANKS);
        BLOCK_TYPES.add(new BlockType("fence_gate",BlockTypes.FENCE_GATE, RenderType.cutout(), ItemGroup.TAB_DECORATIONS, ATWRecipeTypes.FENCE_GATE, fenceGateRecipeInput));
    }

    @Override
    public void registerMaterials() {
        MATERIALS.add(new ATWWood(getModId(),"oak", MaterialColor.WOOD, ItemGroup.TAB_BUILDING_BLOCKS,
                new ArrayList<>(Arrays.asList(BlockTypes.PLANKS))));
    }
}
