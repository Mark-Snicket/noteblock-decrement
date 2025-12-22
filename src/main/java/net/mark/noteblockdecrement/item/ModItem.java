package net.mark.noteblockdecrement.item;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.mark.noteblockdecrement.NoteblockDecrement;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ModItem {

    public static final Item NOTEBLOCK_DECREMENTER = registerItem(new NoteblockDecrementItem(new Item.Settings().maxCount(1).rarity(Rarity.EPIC)));



    private static Item registerItem(Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(NoteblockDecrement.MOD_ID, "noteblock_decrementer"), item);
    }




    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.add(ModItem.NOTEBLOCK_DECREMENTER);
        });
    }
}
