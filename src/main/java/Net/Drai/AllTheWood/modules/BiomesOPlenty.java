package Net.Drai.AllTheWood.modules;

import Net.Drai.AllTheWood.block.*;
import Net.Drai.AllTheWood.block.enums.*;
import Net.Drai.AllTheWood.material.*;

import java.util.*;

public class BiomesOPlenty extends SimpleModule{
    public static ArrayList<ATWMaterial> MATERIALS = new ArrayList<>();
    public ArrayList<BlockType> BLOCK_TYPES = new ArrayList<>();
    public ArrayList<BlockTypes> MISSING_BLOCK_TYPES = new ArrayList<>();
    public BiomesOPlenty(String modId){
        super(modId);
        registerMissingBlockTypes();
        registerModBlockTypes();
        setBLOCK_TYPES(BLOCK_TYPES);
        registerMaterials();
        setMATERIALS(MATERIALS);
    }

    @Override
    public void registerModBlockTypes() {

    }

    public void registerMissingBlockTypes() {
        System.out.println("registerMissingBlockTypes method called on"+getModId());
        /*MISSING_BLOCK_TYPES.add(BlockTypes.BAR_STOOL);
        MISSING_BLOCK_TYPES.add(BlockTypes.CHAIR);
        MISSING_BLOCK_TYPES.add(BlockTypes.TABURET);
        MISSING_BLOCK_TYPES.add(BlockTypes.BOOKCASE);
        MISSING_BLOCK_TYPES.add(BlockTypes.CRATE_CHARM);*/
    }

    public String missingBlockTypesToString(){
        String returnValue = "Missing Blocktypes for "+getModId()+":";
        for (BlockTypes blocktype: MISSING_BLOCK_TYPES
        ) {
            returnValue = returnValue + blocktype.name();
        }
        return(returnValue);
    }

    @Override
    public void registerMaterials() {

    }


}
