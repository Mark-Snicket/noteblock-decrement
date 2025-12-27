package net.mark.noteblockdecrement.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.NoteBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.minecraft.block.NoteBlock.INSTRUMENT;
import static net.minecraft.block.NoteBlock.NOTE;


public class NoteblockDecrementItem extends Item {

    public NoteblockDecrementItem(Settings settings) {
        super(settings);
    }


    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        Block clickedBlock = world.getBlockState(context.getBlockPos()).getBlock();
        PlayerEntity player = context.getPlayer();
        BlockPos blockPos = context.getBlockPos();
        BlockState blockState = world.getBlockState(blockPos);

        if (clickedBlock instanceof NoteBlock noteBlock) {

            if (world.isClient) {
                return ActionResult.SUCCESS;
            } else {
                for (int i = 0; i < 24; i++) {
                    blockState = (BlockState) blockState.cycle(NOTE);
                }
                world.setBlockState(blockPos, blockState, 3);
                this.playNote(player, blockState, world, blockPos, noteBlock);
                player.incrementStat(Stats.TUNE_NOTEBLOCK);
            }
        }

        return ActionResult.PASS;

    }

    private void playNote(@Nullable Entity entity, BlockState blockState, World world, BlockPos pos, NoteBlock noteBlock) {
        if (blockState.get(INSTRUMENT).isNotBaseBlock() || world.getBlockState(pos.up()).isAir()) {
            world.addSyncedBlockEvent(pos, noteBlock, 0, 0);
            world.emitGameEvent(entity, GameEvent.NOTE_BLOCK_PLAY, pos);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("tooltip.noteblock_decrementer.NoteblockDecrementerItem.tooltip"));
        super.appendTooltip(stack, context, tooltip, type);
    }
}
