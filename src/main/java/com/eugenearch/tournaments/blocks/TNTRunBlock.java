package com.eugenearch.tournaments.blocks;

import com.eugenearch.tournaments.Main;
import com.eugenearch.tournaments.utils.init.InitBlocks;
import com.eugenearch.tournaments.utils.init.InitItems;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class TNTRunBlock extends Block implements IHasModel {
    public TNTRunBlock(String name) {
        super(Material.ROCK);

        setRegistryName(name);
        setUnlocalizedName(name);

        this.blockSoundType = SoundType.STONE;
        this.lightValue = 15;

        this.blockHardness = 20.0F;
        this.setHarvestLevel("tntrun_tool", 0);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);

        InitBlocks.BLOCKS.add(this);
        InitItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(I18n.format("tile.tntrun_block.tooltip"));
        tooltip.add(I18n.format("tile.tntrun_block.tooltip2"));
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return null;
    }
}
