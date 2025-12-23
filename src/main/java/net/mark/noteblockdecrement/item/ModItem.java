package net.mark.noteblockdecrement.item;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.mark.noteblockdecrement.NoteblockDecrement;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import java.util.function.Function;

public class ModItem {

    public static final Item NOTEBLOCK_DECREMENTER = registerItem(properties -> new NoteblockDecrementItem(properties.stacksTo(1).rarity(Rarity.EPIC)));




    private static Item registerItem(Function<Item.Properties, Item> function) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(NoteblockDecrement.MOD_ID, "noteblock_decrementer"),
                function.apply(new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(NoteblockDecrement.MOD_ID, "noteblock_decrementer")))));
    }




    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.OP_BLOCKS).register(entries -> {
            entries.accept(ModItem.NOTEBLOCK_DECREMENTER);
        });
    }
}