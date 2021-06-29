package com.eugenearch.tournaments.items;

import com.eugenearch.tournaments.Main;
import com.eugenearch.tournaments.Reference;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;
import com.eugenearch.tournaments.utils.registry.ItemRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class ItemSlimeChunkFounder extends Item implements IHasModel {

    public static final String TAG_LAST_SLIME_CHUNK_STATUS = "isInSlimeChunk";

    public ItemSlimeChunkFounder(String name) {
        ResourceLocation RS = new ResourceLocation(Reference.MOD_ID, name);

        setRegistryName(RS);
        setUnlocalizedName(name);
        setCreativeTab(Main.TOURNAMENTS_TAB);

        ItemRegistry.ITEMS.add(this);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("item.slimizer.tooltip"));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    private boolean getLastChunkStatus(ItemStack stack) {
        return ensureTagCompound(stack).getBoolean(TAG_LAST_SLIME_CHUNK_STATUS);
    }

    private void setLastChunkStatus(ItemStack stack, boolean isSlimeChunk) {
        ensureTagCompound(stack).setBoolean(TAG_LAST_SLIME_CHUNK_STATUS, isSlimeChunk);
    }

    public static NBTTagCompound ensureTagCompound(ItemStack itemStack) {
        NBTTagCompound compound = itemStack.getTagCompound();
        if (compound == null) {
            compound = new NBTTagCompound();
            itemStack.setTagCompound(compound);
        }
        return compound;
    }

    @SubscribeEvent
    public void onEnteringChunk(EntityEvent.EnteringChunk event) {

        final Entity entity = event.getEntity();
        if (entity.getEntityWorld().isRemote) return;

        if (entity instanceof EntityPlayer) {

            ItemStack offhand = ((EntityPlayer) entity).getHeldItemOffhand();
            if (!offhand.isEmpty() && offhand.getItem() instanceof ItemSlimeChunkFounder) {

                Chunk chunk = entity.getEntityWorld().getChunkFromChunkCoords(event.getNewChunkX(), event.getNewChunkZ());
                boolean isSlimeChunkPrev = this.getLastChunkStatus(offhand);
                boolean isSlimeChunkNew = chunk.getRandomWithSeed(987234911L).nextInt(10) == 0;

                if (isSlimeChunkPrev != isSlimeChunkNew) {
                    this.setLastChunkStatus(offhand, isSlimeChunkNew);
                    if (!isSlimeChunkPrev) {
                        entity.sendMessage(new TextComponentTranslation("item.slimizer.messages.slimechunk_enter"));
                    } else {
                        entity.sendMessage(new TextComponentTranslation("item.slimizer.messages.slimechunk_leave"));
                    }
                }

            }

        }

    }

}
