package net.mark.noteblockdecrement;

import net.fabricmc.api.ModInitializer;
import net.mark.noteblockdecrement.item.ModItem;

public class NoteblockDecrement implements ModInitializer {
    public static final String MOD_ID = "noteblock_decrement";

    @Override
    public void onInitialize() {
        ModItem.registerModItems();
    }
}