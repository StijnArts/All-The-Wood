package Net.Drai.AllTheWood.modules;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.misc.*;
import net.minecraftforge.fml.*;
import org.apache.logging.log4j.*;

import java.util.*;

public abstract class SimpleModule {

    private String modId;
    private ArrayList<ATWMaterial> MATERIALS = new ArrayList<>();



    private ArrayList<BlockType> BLOCK_TYPES = new ArrayList<>();


    public ArrayList<BlockTypes> MISSING_BLOCK_TYPES;
    public void registerModule(){
        if(ModList.get().isLoaded(modId) || modId == "minecraft"){
            registerModBlockTypes();

            AllTheWood.MODULES.add(this);
        }
    }
    public abstract void registerModBlockTypes();
    public abstract void registerMissingBlockTypes();
    public abstract void registerMaterials();
    public abstract String missingBlockTypesToString();
    public ArrayList<ATWMaterial> getMATERIALS() {
        return MATERIALS;
    }
    public ArrayList<BlockType> getBLOCK_TYPES() {
        return BLOCK_TYPES;
    }
    public String getModId() {
        return modId;
    }
}
