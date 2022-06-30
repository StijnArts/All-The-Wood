package Net.Drai.AllTheWood.block.blocks;

import Net.Drai.AllTheWood.tileentitites.*;
import net.minecraft.block.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;

import javax.annotation.*;

public class ATWStandingSignBlock extends StandingSignBlock {
    public ATWStandingSignBlock(Properties properties) {
        super(properties, WoodType.OAK);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ATWSignTileEntity();
    }
}
