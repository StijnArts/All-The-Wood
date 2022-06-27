package Net.Drai.AllTheWood.misc;

import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;

public class FuelItemBlock extends BlockItem {
    public FuelItemBlock(Block block, Properties prop) {
        super(block, prop);
    }

    public int getBurnTime(ItemStack itemStack) {
        return 300;
    }
}