package Net.Drai.AllTheWood.modules;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.material.*;
import net.minecraftforge.fml.*;

import java.util.*;

public abstract class SimpleModule {

    private String modId;
    private ArrayList<ATWMaterial> MATERIALS = new ArrayList<>();



    private ArrayList<BlockType> BLOCK_TYPES = new ArrayList<>();
    public SimpleModule(String modId){
        this.MATERIALS = MATERIALS;
        this.modId = modId;
        registerModule();
    }

    public void registerModule(){
        if(ModList.get().isLoaded(modId) || modId == "minecraft"){
            registerModBlockTypes();

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

    public void setBLOCK_TYPES(ArrayList<BlockType> BLOCK_TYPES) {
        this.BLOCK_TYPES = BLOCK_TYPES;
    }
}
