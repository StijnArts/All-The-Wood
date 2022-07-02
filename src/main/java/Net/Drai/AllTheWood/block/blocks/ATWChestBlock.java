package Net.Drai.AllTheWood.block.blocks;

import Net.Drai.AllTheWood.api.*;
import Net.Drai.AllTheWood.modules.*;
import Net.Drai.AllTheWood.tileentitites.*;
import com.google.common.base.Supplier;
import com.mojang.blaze3d.matrix.*;
import net.minecraft.block.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.tileentity.*;
import net.minecraft.item.*;
import net.minecraft.loot.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraftforge.api.distmarker.*;
import net.minecraftforge.fml.*;

import javax.annotation.*;
import java.util.*;
import java.util.function.*;

public class ATWChestBlock extends ChestBlock {
    public ATWChestBlock(AbstractBlock.Properties properties) {
        super(properties.noOcclusion(), () -> {
            return TileEntities.CHEST_TILE_ENTITIES.get();
        });
    }

    public TileEntity newBlockEntity(IBlockReader world) {
        return TileEntities.CHEST_TILE_ENTITIES.get().create();
    }

    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drop = super.getDrops(state, builder);
        drop.add(new ItemStack(this.asItem()));
        return drop;
    }
}

