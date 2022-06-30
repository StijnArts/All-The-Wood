package Net.Drai.AllTheWood.material;

import Net.Drai.AllTheWood.block.enums.*;
import net.minecraft.block.*;
import net.minecraft.block.material.*;
import net.minecraft.item.*;
import net.minecraftforge.common.*;

import java.util.*;

public class ATWMaterial {

    private String name;
    private MaterialColor materialColor;
    public ArrayList<BlockTypes> MISSING_BLOCK_TYPES;
    private Material material;
    private int harvestLevel;
    private ToolType toolType;
    private float strengthLower;
    private float strengthUpper;
    private SoundType soundType;
    private Block baseBlock;
    private String modId;
    private ItemGroup itemGroup;

    public ATWMaterial(String modId, String name, MaterialColor materialColor, Material material, int harvestLevel, ToolType toolType,
                       float strengthLower, float strengthUpper, SoundType soundType, ItemGroup defaultItemgroup,
                       ArrayList<BlockTypes> missing_block_types){
        this.modId = modId;
        this.name = name;
        this.material = material;
        this.harvestLevel = harvestLevel;
        this.toolType = toolType;
        this.strengthLower = strengthLower;
        this.strengthUpper = strengthUpper;
        this.soundType = soundType;
        this.materialColor = materialColor;
        this.MISSING_BLOCK_TYPES = missing_block_types;
        this.itemGroup = defaultItemgroup;
        this.baseBlock = new Block(AbstractBlock.Properties.of(Material.WOOD, materialColor).sound(soundType).harvestTool(toolType).harvestLevel(harvestLevel).strength(strengthLower, strengthUpper));
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

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public Material getMaterial() {
        return material;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public float getStrengthLower() {
        return strengthLower;
    }

    public float getStrengthUpper() {
        return strengthUpper;
    }

    public String getModId() {
        return modId;
    }

    public SoundType getSoundType() {
        return soundType;
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }
}
