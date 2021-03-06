package Net.Drai.AllTheWood.data.client;

import Net.Drai.AllTheWood.*;
import Net.Drai.AllTheWood.modules.*;
import com.google.common.collect.*;
import com.mojang.datafixers.util.*;
import net.minecraft.block.*;
import net.minecraft.data.*;
import net.minecraft.data.loot.*;
import net.minecraft.loot.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.*;
import net.minecraftforge.registries.*;
import net.silentchaos512.gear.data.loot.*;
import net.silentchaos512.gear.init.*;

import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class ATWLootTableProvider extends LootTableProvider {
    public ATWLootTableProvider(DataGenerator p_i50789_1_) {
        super(p_i50789_1_);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootParameterSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((p_218436_2_, p_218436_3_) -> LootTableManager.validate(validationtracker, p_218436_2_, p_218436_3_));
    }

    public static class ModBlockLootTables extends BlockLootTables {
        @Override
        protected void addTables() {
            for (SimpleModule module : AllTheWood.MODULES) {
                for (RegistryObject<Block> block : module.BLOCKS_REGISTRY.getEntries()) {
                    dropSelf(block.get());
                }
            }
        }
        @Override
        protected Iterable<Block> getKnownBlocks() {
            ArrayList<RegistryObject<Block>> registryObjects = new ArrayList<>();
            for (SimpleModule module : AllTheWood.MODULES) {
                for (RegistryObject<Block> block : module.BLOCKS_REGISTRY.getEntries()) {
                    registryObjects.add(block);
                }
            }
            return registryObjects.stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }
    }
}
