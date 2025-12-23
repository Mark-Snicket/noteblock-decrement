package net.mark.noteblockdecrement.item;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.mark.noteblockdecrement.NoteblockDecrement;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;

import java.util.function.Function;

public class ModItem {

    public static final Item NOTEBLOCK_DECREMENTER = registerItem(setting -> new NoteblockDecrementItem(setting.maxCount(1).rarity(Rarity.EPIC)));




    private static Item registerItem(Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(NoteblockDecrement.MOD_ID, "noteblock_decrementer"),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(NoteblockDecrement.MOD_ID, "noteblock_decrementer")))));
    }




    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.add(ModItem.NOTEBLOCK_DECREMENTER);
        });
    }
}