package Net.Drai.AllTheWood.data.client;

import net.minecraft.data.*;
import net.minecraftforge.common.data.*;

public class ModItemTagsProvider extends ForgeItemTagsProvider {
    public ModItemTagsProvider(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
        super(gen, blockTagProvider, existingFileHelper);
    }
}
