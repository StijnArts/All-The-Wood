package Net.Drai.AllTheWood.tileentitites;

import Net.Drai.AllTheWood.modules.*;
import net.minecraft.tileentity.*;

public class ATWChestTileEntity extends ChestTileEntity {
    public ATWChestTileEntity() {
        super(TileEntities.CHEST_TILE_ENTITIES.get());
    }

    @Override
    public TileEntityType<?> getType() {
        return TileEntities.CHEST_TILE_ENTITIES.get();
    }
}

