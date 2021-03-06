package Net.Drai.AllTheWood.modules;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.tileentitites.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.registries.*;
import vazkii.quark.content.building.tile.*;

public class TileEntities {
    public static RegistryObject<TileEntityType<ATWSignTileEntity>> SIGN_TILE_ENTITIES;
    public static RegistryObject<TileEntityType<ATWBarrelTileEntity>> BARREL_TILE_ENTITIES;
    public static RegistryObject<TileEntityType<ATWChestTileEntity>> CHEST_TILE_ENTITIES;
}