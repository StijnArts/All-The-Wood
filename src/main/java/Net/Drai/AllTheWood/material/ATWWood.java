package Net.Drai.AllTheWood.material;

import Net.Drai.AllTheWood.block.enums.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.item.*;
import net.minecraftforge.common.*;

import java.util.*;

public class ATWWood extends ATWMaterial{
    public ATWWood(String modId, String name, MaterialColor materialColor, WoodType woodType, ItemGroup defaultItemgroup, ArrayList<BlockTypes> missing_block_types) {
        super(modId,name, materialColor, Material.WOOD, 0, ToolType.AXE, 3.0F,4.0F, woodType, SoundType.WOOD, defaultItemgroup, missing_block_types);
    }
}
