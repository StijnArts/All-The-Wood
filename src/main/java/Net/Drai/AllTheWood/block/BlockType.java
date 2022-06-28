package Net.Drai.AllTheWood.block;

import Net.Drai.AllTheWood.*;
import net.minecraft.client.renderer.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

import java.util.*;

public class BlockType {
    private static String name;
    private BlockTypes blockType;
    private ATWRecipeTypes recipeType;
    private int quantityOut;
    private RenderType renderType;
    public ArrayList<BlockTypes> recipeInput;
    //public ITag.INamedTag<Block> blockTag;
    //public ITag.INamedTag<Item> itemTag;
    public ItemGroup getItemGroup() {
        return itemGroup;
    }

    private ItemGroup itemGroup;
    public BlockType(String name, BlockTypes blockType, RenderType rendertype, ItemGroup itemGroup, ATWRecipeTypes recipeType, ArrayList<BlockTypes> recipeInput, int quantityOut /*,ITag.INamedTag<Item> itemTag, ITag.INamedTag<Block> blockTag*/)
    {
        this.name = name;
        this.blockType = blockType;
        this.renderType = rendertype;
        this.itemGroup = itemGroup;
        this.recipeType = recipeType;
        this.quantityOut = quantityOut;
        this.recipeInput = recipeInput;
        //this.blockTag = blockTag;
        //this.itemTag = itemTag;
        AllTheWood.BLOCK_TYPES.put(blockType, this);
    }

    public BlockType(String name, BlockTypes blockType, RenderType rendertype, ItemGroup itemGroup, ATWRecipeTypes recipeType, ArrayList<BlockTypes> recipeInput /*, ITag.INamedTag<Item> itemTag, ITag.INamedTag<Block> blockTag*/)
    {
        this(name,blockType,rendertype,itemGroup,recipeType,recipeInput,1/*,itemTag,blockTag*/);
    }

    public BlockType(String name, BlockTypes blockType, RenderType rendertype, ATWRecipeTypes recipeType)
    {
        this.name = name;
        this.blockType = blockType;
        this.renderType = rendertype;
        this.recipeType = recipeType;
        AllTheWood.BLOCK_TYPES.put(BlockTypes.PLANKS, this);
    }


    public RenderType getRenderType() {
        return renderType;
    }

    public BlockTypes getBlockType() {
        return blockType;
    }

    public ATWRecipeTypes getRecipeType() {
        return recipeType;
    }

    public int getQuantityOut() {
        return quantityOut;
    }

    public static String getName() {
        return name;
    }
}
