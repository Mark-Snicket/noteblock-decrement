package net.mark.noteblockdecrement.item;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.mark.noteblockdecrement.NoteblockDecrement;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.function.Function;

public class ModItem {

    public static final Item NOTEBLOCK_DECREMENTER = registerItem("noteblock_decrementer", properties -> new NoteblockDecrementItem(properties.stacksTo(1).rarity(Rarity.EPIC)));




    private static Item registerItem(String name, Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(NoteblockDecrement.MOD_ID, name),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(NoteblockDecrement.MOD_ID, name)))));
    }




    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.OP_BLOCKS).register(entries -> {
            entries.accept(ModItem.NOTEBLOCK_DECREMENTER);
        });
    }
}