package Net.Drai.AllTheWood.block;

import Net.Drai.AllTheWood.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;

public class BlockType {
    private static String name;
    private BlockTypes blockType;

    private RenderType renderType;

    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    private ItemGroup itemGroup;
    public BlockType(String name, BlockTypes blockType, RenderType rendertype, ItemGroup itemGroup)
    {
        this.name = name;
        this.blockType = blockType;
        this.renderType = rendertype;
        this.itemGroup = itemGroup;
        AllTheWood.BLOCK_TYPES.put(BlockTypes.PLANKS, this);
    }
    public BlockType(String name, BlockTypes blockType, RenderType rendertype)
    {
        this.name = name;
        this.blockType = blockType;
        this.renderType = rendertype;
        AllTheWood.BLOCK_TYPES.put(BlockTypes.PLANKS, this);
    }


    public RenderType getRenderType() {
        return renderType;
    }

    public BlockTypes getBlockType() {
        return blockType;
    }


    public static String getName() {
        return name;
    }
}
