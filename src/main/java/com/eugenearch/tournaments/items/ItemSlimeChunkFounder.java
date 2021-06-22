package com.eugenearch.tournaments.items;

import com.eugenearch.tournaments.Main;
import com.eugenearch.tournaments.Reference;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;
import com.eugenearch.tournaments.utils.registry.ItemRegistry;
import net.minecraft.advancements.Advancement;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.command.CommandBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.sql.Ref;
import java.util.List;

public class ItemSlimeChunkFounder extends Item implements IHasModel {
    private String name;
    private int chunk_key;

    public ItemSlimeChunkFounder(String name) {
        super();

        this.name = name;
        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Main.TOURNAMENTS_TAB);

        ItemRegistry.ITEMS.add(this);
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("item." + this.name + ".tooltip"));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    private boolean getChunk_key(ItemStack stack) {
        if (!stack.hasTagCompound()) {
            setChunk_key(stack, false);
            return false;
        }
        NBTTagCompound tag = stack.getTagCompound();
        return tag.getBoolean("isInSlimeChunk");
    }

    private void setChunk_key(ItemStack stack, boolean isSlimeChunk) {
        NBTTagCompound tag;
        if (!stack.hasTagCompound()) {
            tag = new NBTTagCompound();
        } else {
            tag = stack.getTagCompound();
        }
        tag.setBoolean("isInSlimeChunk", isSlimeChunk);
        stack.setTagCompound(tag);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
        if (!world.isRemote && entity instanceof EntityPlayer && ((EntityPlayer)entity).getHeldItemOffhand() == stack) {
            if (entity != null) {
                Chunk chunk = world.getChunkFromChunkCoords(MathHelper.floor(entity.posX) >> 4, MathHelper.floor(entity.posZ) >> 4);
                boolean isSlimeChunkPrev = this.getChunk_key(stack);
                boolean isSlimeChunkNew = chunk.getRandomWithSeed(987234911L).nextInt(10) == 0;
//                System.out.println(isSlimeChunkPrev + " >> " + isSlimeChunkNew);

                if (isSlimeChunkPrev != isSlimeChunkNew) {
                    this.setChunk_key(stack, isSlimeChunkNew);

                    if (isSlimeChunkPrev == false) {
                        entity.sendMessage(new TextComponentTranslation("item." + name + ".messages.slimechunk_enter"));
                    } else {
                        entity.sendMessage(new TextComponentTranslation("item." + name + ".messages.slimechunk_leave"));
                    }
                }
            } else {
                this.setChunk_key(stack,false);
            }
        }
    }
}
