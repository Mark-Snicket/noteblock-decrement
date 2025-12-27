package net.mark.noteblockdecrement.item;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NoteBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

import static net.minecraft.world.level.block.NoteBlock.INSTRUMENT;
import static net.minecraft.world.level.block.NoteBlock.NOTE;


public class NoteblockDecrementItem extends Item {

    public NoteblockDecrementItem(Properties properties) {
        super(properties);
    }


    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();
        Player player = context.getPlayer();
        BlockPos blockPos = context.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);

        if (clickedBlock instanceof NoteBlock noteBlock) {

            if (level.isClientSide()) {
                return InteractionResult.SUCCESS;
            } else {
                for (int i = 0; i < 24; i++) {
                blockState = (BlockState) blockState.cycle(NOTE);
            }
                level.setBlockAndUpdate(blockPos, blockState);
                this.playNote(player, blockState, level, blockPos, noteBlock);
                player.awardStat(Stats.TUNE_NOTEBLOCK);
            }
        }
        return InteractionResult.PASS;

    }

    private void playNote(@Nullable Entity entity, BlockState blockState, Level level, BlockPos blockPos, NoteBlock noteBlock) {
        if ((blockState.getValue(INSTRUMENT)).worksAboveNoteBlock() || level.getBlockState(blockPos.above()).isAir()) {
            level.blockEvent(blockPos, noteBlock, 0, 0);
            level.gameEvent(entity, GameEvent.NOTE_BLOCK_PLAY, blockPos);
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, TooltipDisplay tooltipDisplay, Consumer<Component> consumer, TooltipFlag tooltipFlag) {

        consumer.accept(Component.translatable("tooltip.noteblock_decrementer.NoteblockDecrementerItem.tooltip"));
        super.appendHoverText(itemStack, tooltipContext,tooltipDisplay, consumer, tooltipFlag);
    }
}
