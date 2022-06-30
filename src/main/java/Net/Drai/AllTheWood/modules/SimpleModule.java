package Net.Drai.AllTheWood.modules;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.tileentity.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.registries.*;

import java.util.*;

public abstract class SimpleModule {

    public static DeferredRegister<TileEntityType<?>> TILE_ENTITY_REGISTRY;
    public DeferredRegister<Block> BLOCKS_REGISTRY;
    public DeferredRegister<Item> ITEM_REGISTRY;
    private String modId;
    private ArrayList<ATWMaterial> MATERIALS = new ArrayList<>();
    private ArrayList<BlockType> BLOCK_TYPES = new ArrayList<>();
    public ArrayList<BlockTypes> TILE_ENTITIES = new ArrayList<>();

    public SimpleModule(String modId){
        this.MATERIALS = MATERIALS;
        this.modId = modId;
        registerModule();
    }

    public void registerModule(){
        if(ModList.get().isLoaded(modId) || modId == "minecraft"){
            registerModBlockTypes();
            AllTheWood.BLOCKS_REGISTRY.add(BLOCKS_REGISTRY);
            AllTheWood.ITEM_REGISTRY.add(ITEM_REGISTRY);
            AllTheWood.MODULES.add(this);
        }
    }
    public abstract void registerModBlockTypes();
    public abstract void registerMaterials();
    public ArrayList<ATWMaterial> getMATERIALS() {
        return MATERIALS;
    }
    public ArrayList<BlockType> getBLOCK_TYPES() {
        return BLOCK_TYPES;
    }
    public String getModId() {
        return modId;
    }
    public void setMATERIALS(ArrayList<ATWMaterial> MATERIALS) {
        this.MATERIALS = MATERIALS;
    }
    public void setBlockRegister(DeferredRegister<Block> BLOCKS_REGISTRY){this.BLOCKS_REGISTRY=BLOCKS_REGISTRY;}
    public void setItemRegister(DeferredRegister<Item> ITEM_REGISTRY){this.ITEM_REGISTRY=ITEM_REGISTRY;}
    public static void setTileEntityRegistry(DeferredRegister<TileEntityType<?>> tileEntityRegistry) {
        TILE_ENTITY_REGISTRY = tileEntityRegistry;
    }
    public void setBLOCK_TYPES(ArrayList<BlockType> BLOCK_TYPES) {
        this.BLOCK_TYPES = BLOCK_TYPES;
    }
    public void setTILE_ENTITIES(ArrayList<BlockTypes> TILE_ENTITIES) {
        this.TILE_ENTITIES = TILE_ENTITIES;
    }
}
