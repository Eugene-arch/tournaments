package com.eugenearch.tournaments.utils.handlers;

import com.eugenearch.tournaments.utils.init.InitBlocks;
import com.eugenearch.tournaments.utils.init.InitItems;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraft.util.math.BlockPos;


@EventBusSubscriber
public class RegisterHandler {
    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        event.getRegistry().registerAll(InitItems.ITEMS.toArray(new Item[0]));
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(InitBlocks.BLOCKS.toArray(new Block[0]));
    }

    @SubscribeEvent
    public static void onModelRegister(ModelRegistryEvent event) {
        for(Item item:InitItems.ITEMS) {
            if (item instanceof IHasModel) {
                ((IHasModel)item).registerModels();
            }
        }

        for(Block block:InitBlocks.BLOCKS) {
            if (block instanceof IHasModel) {
                ((IHasModel)block).registerModels();
            }
        }
    }

    @SubscribeEvent
    public static void playerTick(TickEvent.PlayerTickEvent event) {
        double x, y, z;
        x = event.player.posX;
        y = event.player.posY - event.player.getYOffset() - 1;
        z = event.player.posZ;

        IBlockState blockState = event.player.world.getBlockState(new BlockPos(x, y, z));
        Block block = blockState.getBlock();

        if (block != null) {
            if (block.getHarvestTool(blockState) == "tntrun_tool") {
                // give player "Speed 10" effect for 60 ticks
                event.player.addPotionEffect(new PotionEffect(Potion.getPotionById(1), 60, 10));
                // if player is on tntrun_block then destroy this block
                if (event.player.posY % 1 == 0) {
                    event.player.world.destroyBlock(new BlockPos(x, y, z), false);
                }
            }
        }
    }
}
