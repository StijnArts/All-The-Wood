package Net.Drai.AllTheWood.block.blocks;

import Net.Drai.AllTheWood.modules.*;
import Net.Drai.AllTheWood.tileentitites.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.entity.monster.piglin.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.loot.*;
import net.minecraft.stats.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.server.*;

import javax.annotation.*;
import java.util.*;

public class ATWBarrel extends BarrelBlock {
    public ATWBarrel(Properties source) {
        super(source.noOcclusion());
    }

    public TileEntity newBlockEntity(IBlockReader world) {
        return TileEntities.BARREL_TILE_ENTITIES.get().create();
    }

    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        List<ItemStack> drop = super.getDrops(state, builder);
        drop.add(new ItemStack(this.asItem()));
        return drop;
    }

    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (world.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            TileEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ATWBarrelTileEntity) {
                player.openMenu((ATWBarrelTileEntity)blockEntity);
                player.awardStat(Stats.OPEN_BARREL);
                PiglinTasks.angerNearbyPiglins(player, true);
            }

            return ActionResultType.CONSUME;
        }
    }

    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        TileEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof ATWBarrelTileEntity) {
            ((ATWBarrelTileEntity)blockEntity).tick();
        }

    }

    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public void setPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (itemStack.hasCustomHoverName()) {
            TileEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ATWBarrelTileEntity) {
                ((ATWBarrelTileEntity)blockEntity).setCustomName(itemStack.getHoverName());
            }
        }

    }
}
