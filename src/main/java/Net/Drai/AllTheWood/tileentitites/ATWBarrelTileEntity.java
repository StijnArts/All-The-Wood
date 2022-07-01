package Net.Drai.AllTheWood.tileentitites;

import Net.Drai.AllTheWood.block.blocks.*;
import Net.Drai.AllTheWood.modules.*;
import net.minecraft.block.*;
import net.minecraft.entity.player.*;
import net.minecraft.inventory.*;
import net.minecraft.inventory.container.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.*;
import net.minecraft.util.text.*;

public class ATWBarrelTileEntity extends LockableLootTileEntity {
    private NonNullList<ItemStack> inventory;
    private int viewerCount;
    protected ATWBarrelTileEntity(TileEntityType<?> type) {
        super(type);
        this.inventory = NonNullList.withSize(27, ItemStack.EMPTY);
    }

    public ATWBarrelTileEntity() {
        this(TileEntities.BARREL_TILE_ENTITIES.get());
    }

    public CompoundNBT save(CompoundNBT tag) {
        super.save(tag);
        if (!this.trySaveLootTable(tag)) {
            ItemStackHelper.saveAllItems(tag, this.inventory);
        }

        return tag;
    }

    public void load(BlockState state, CompoundNBT tag) {
        super.load(state, tag);
        this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        if (!this.tryLoadLootTable(tag)) {
            ItemStackHelper.loadAllItems(tag, this.inventory);
        }

    }

    public int getContainerSize() {
        return 27;
    }

    protected NonNullList<ItemStack> getItems() {
        return this.inventory;
    }

    protected void setItems(NonNullList<ItemStack> list) {
        this.inventory = list;
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container.barrel");
    }

    protected Container createMenu(int syncId, PlayerInventory playerInventory) {
        return ChestContainer.threeRows(syncId, playerInventory, this);
    }

    public void startOpen(PlayerEntity player) {
        if (!player.isSpectator()) {
            if (this.viewerCount < 0) {
                this.viewerCount = 0;
            }

            ++this.viewerCount;
            BlockState blockState = this.getBlockState();
            boolean bl = (Boolean)blockState.getValue(BarrelBlock.OPEN);
            if (!bl) {
                this.playSound(blockState, SoundEvents.BARREL_OPEN);
                this.setOpen(blockState, true);
            }

            this.scheduleTick();
        }

    }

    private void scheduleTick() {
        this.level.getBlockTicks().scheduleTick(this.getBlockPos(), this.getBlockState().getBlock(), 5);
    }

    public void tick() {
        int i = this.worldPosition.getX();
        int j = this.worldPosition.getY();
        int k = this.worldPosition.getZ();
        this.viewerCount = ChestTileEntity.getOpenCount(this.level, this, i, j, k);
        if (this.viewerCount > 0) {
            this.scheduleTick();
        } else {
            BlockState blockState = this.getBlockState();
            if (!(blockState.getBlock() instanceof ATWBarrel)) {
                this.setRemoved();
                return;
            }

            boolean bl = (Boolean)blockState.getValue(BarrelBlock.OPEN);
            if (bl) {
                this.playSound(blockState, SoundEvents.BARREL_CLOSE);
                this.setOpen(blockState, false);
            }
        }

    }

    public void stopOpen(PlayerEntity player) {
        if (!player.isSpectator()) {
            --this.viewerCount;
        }
    }

    private void setOpen(BlockState state, boolean open) {
        this.level.setBlock(this.getBlockPos(), (BlockState)state.setValue(BarrelBlock.OPEN, open), 3);
    }

    private void playSound(BlockState blockState, SoundEvent soundEvent) {
        Vector3i vec3i = ((Direction)blockState.getValue(BarrelBlock.FACING)).getNormal();
        double d = (double)this.worldPosition.getX() + 0.5D + (double)vec3i.getX() / 2.0D;
        double e = (double)this.worldPosition.getY() + 0.5D + (double)vec3i.getY() / 2.0D;
        double f = (double)this.worldPosition.getZ() + 0.5D + (double)vec3i.getZ() / 2.0D;
        this.level.playSound((PlayerEntity)null, d, e, f, soundEvent, SoundCategory.BLOCKS, 0.5F, this.level.random.nextFloat() * 0.1F + 0.9F);
    }
}
