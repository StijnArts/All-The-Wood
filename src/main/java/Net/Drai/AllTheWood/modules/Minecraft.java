package Net.Drai.AllTheWood.modules;

import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.material.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraftforge.common.*;

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
        new BlockType("test",BlockTypes.PLANKS, RenderType.solid(), ItemGroup.TAB_BUILDING_BLOCKS);
    }

    @Override
    public void registerMaterials() {
        MATERIALS.add(new ATWWood("oak", MaterialColor.WOOD, ItemGroup.TAB_BUILDING_BLOCKS,
                new ArrayList<BlockTypes>(Arrays.asList(BlockTypes.PLANKS))));

    }
}
