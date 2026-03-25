package net.mark.noteblockdecrement.item;


import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.mark.noteblockdecrement.NoteblockDecrement;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItem {

    public static final Item NOTEBLOCK_DECREMENTER = registerItem(new NoteblockDecrementItem(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));



    private static Item registerItem(Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(NoteblockDecrement.MOD_ID, "noteblock_decrementer"), item);
    }




    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.OP_BLOCKS).register(entries -> {
            entries.accept(ModItem.NOTEBLOCK_DECREMENTER);
        });
    }
}
