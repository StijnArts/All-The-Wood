package Net.Drai.AllTheWood.modules;

import Net.Drai.AllTheWood.misc.*;
import net.minecraft.block.material.*;
import org.apache.logging.log4j.*;

import java.util.*;

public class BiomesOPlenty extends SimpleModule{
    public static ArrayList<ATWMaterial> MATERIALS = new ArrayList<>();
    public ArrayList<BlockTypes> MISSING_BLOCK_TYPES = new ArrayList<>();
    private String modId = "biomesoplenty";

    public BiomesOPlenty(){
        registerMissingBlockTypes();

        registerModule();
    }
    @Override
    public void registerModBlockTypes() {

    }

    public void registerMissingBlockTypes() {
        System.out.println("registerMissingBlockTypes method called on"+modId);
        MISSING_BLOCK_TYPES.add(BlockTypes.BAR_STOOL);
        MISSING_BLOCK_TYPES.add(BlockTypes.CHAIR);
        MISSING_BLOCK_TYPES.add(BlockTypes.TABURET);
        MISSING_BLOCK_TYPES.add(BlockTypes.BOOKCASE);
        MISSING_BLOCK_TYPES.add(BlockTypes.CRATE_CHARM);
    }

    public String missingBlockTypesToString(){
        String returnValue = "Missing Blocktypes for "+modId+":";
        for (BlockTypes blocktype: MISSING_BLOCK_TYPES
        ) {
            returnValue = returnValue + blocktype.name();
        }
        return(returnValue);
    }

    @Override
    public void registerMaterials() {
        MATERIALS.add(new ATWMaterial("fir", MaterialColor.WOOD, MISSING_BLOCK_TYPES));
    }


}
