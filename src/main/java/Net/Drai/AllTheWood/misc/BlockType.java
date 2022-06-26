package Net.Drai.AllTheWood.misc;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraftforge.common.*;

public class BlockType {
    private static String name;
    private BlockTypes blockType;
    private Material material;
    private int harvestLevel;
    private ToolType toolType;
    private float strengthLower;
    private float strengthUpper;
    private SoundType soundType;
    private RenderType renderType;
    private ItemGroup itemGroup;
    private BlockType(String name, BlockTypes blockType, Material material, int harvestLevel, ToolType toolType, float strengthLower, float strengthUpper, SoundType soundType, RenderType rendertype, ItemGroup itemGroup)
    {
        this.name = name;
        this.blockType = blockType;
        this.material = material;
        this.harvestLevel = harvestLevel;
        this.toolType = toolType;
        this.strengthLower = strengthLower;
        this.strengthUpper = strengthUpper;
        this.soundType = soundType;
        this.renderType = rendertype;
        this.itemGroup = itemGroup;
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

    public SoundType getSoundType() {
        return soundType;
    }

    public RenderType getRenderType() {
        return renderType;
    }

    public int getHarvestLevel() {
        return harvestLevel;
    }

    public BlockTypes getBlockType() {
        return blockType;
    }

    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    public static String getName() {
        return name;
    }
}
