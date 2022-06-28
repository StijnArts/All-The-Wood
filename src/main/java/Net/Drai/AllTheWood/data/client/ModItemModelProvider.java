package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import net.minecraft.data.*;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.*;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper){
        super(generator, AllTheWood.MOD_ID,existingFileHelper);
    }

    @Override
    protected void registerModels() {
        withExistingParent("test_block", modLoc("block/test_block"));
        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));

        //builder(itemGenerated, "test_block");
    }

    private void builder(ModelFile itemGenerated, String name) {
        //getBuilder(name).parent(itemGenerated).texture("layer:0", "item/"+name);
    }
}
