package Net.Drai.AllTheWood.tileentitites;

import Net.Drai.AllTheWood.modules.*;
import com.mojang.brigadier.exceptions.*;
import com.mojang.serialization.*;
import net.minecraft.block.*;
import net.minecraft.command.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.nbt.*;
import net.minecraft.network.play.server.*;
import net.minecraft.tileentity.*;
import net.minecraft.util.*;
import net.minecraft.util.math.vector.*;
import net.minecraft.util.text.*;
import net.minecraft.util.text.event.*;
import net.minecraft.world.server.*;
import net.minecraftforge.api.distmarker.*;

import javax.annotation.*;
import java.util.function.*;
import net.minecraft.tileentity.SignTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.silentchaos512.gear.init.*;

public class ATWSignTileEntity extends SignTileEntity {
    public ATWSignTileEntity(){
        super();
    }

    @Override
    public TileEntityType<?> getType() {
        return TileEntities.SIGN_TILE_ENTITIES.get();
    }
}
