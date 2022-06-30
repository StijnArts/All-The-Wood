package Net.Drai.AllTheWood.data;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.data.client.*;
import net.minecraft.data.*;
import net.minecraftforge.common.data.*;
import net.minecraftforge.eventbus.api.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.event.lifecycle.*;

@Mod.EventBusSubscriber(modid = AllTheWood.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {
    private DataGenerators() {};
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event){
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        if (event.includeClient()) {
            gen.addProvider(new ATWBlockStateProvider(gen, existingFileHelper));
            gen.addProvider(new ATWItemModelProvider(gen, existingFileHelper));
            gen.addProvider(new ATWRecipeProvider(gen));
            gen.addProvider(new ATWLootTableProvider(gen));
            gen.addProvider(new ATWLanguageProvider(gen));
        }
    }
}
