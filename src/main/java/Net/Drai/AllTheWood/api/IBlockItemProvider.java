package Net.Drai.AllTheWood.api;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item.Properties;

public interface IBlockItemProvider {
    BlockItem provideItemBlock(Block var1, Properties var2);
}

