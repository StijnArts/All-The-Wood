package Net.Drai.AllTheWood.misc;

import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraftforge.common.*;

import java.util.*;

public class ATWMaterial {
    private String name;
    private MaterialColor materialColor;
    public ArrayList<BlockTypes> MISSING_BLOCK_TYPES;
    private Block baseBlock;
    public ATWMaterial(String name, MaterialColor materialColor, ArrayList<BlockTypes> missing_block_types){
        this.name = name;
        this.materialColor = materialColor;
        this.MISSING_BLOCK_TYPES = missing_block_types;
        this.baseBlock = new Block(AbstractBlock.Properties.of(Material.WOOD, materialColor).sound(SoundType.WOOD).harvestTool(ToolType.AXE).harvestLevel(0).strength(3.0F, 4.0F));
    }
    public String getName(){
        return name;
    }
    public MaterialColor getMaterialColor(){
        return materialColor;
    }
    public BlockState getBlockState(){
        return baseBlock.getBlock().defaultBlockState();
    }
}
