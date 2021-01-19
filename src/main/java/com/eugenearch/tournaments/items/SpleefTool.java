package com.eugenearch.tournaments.items;

import com.eugenearch.tournaments.Main;
import com.eugenearch.tournaments.utils.init.InitBlocks;
import com.eugenearch.tournaments.utils.init.InitItems;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

public class SpleefTool extends ItemTool implements IHasModel {
    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(InitBlocks.SPLEEF_SNOW);

    public SpleefTool(String name) {
        super(-1.0F, 1.0F, Main.EVENT_MATERIAL, EFFECTIVE_ON);

        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(CreativeTabs.TOOLS);

        InitItems.ITEMS.add(this);
    }

    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return blockIn.getBlock() == InitBlocks.SPLEEF_SNOW;
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        tooltip.add(I18n.format("item.spleef_shovel.tooltip"));
        super.addInformation(stack, player, tooltip, advanced);
    }

    @Override
    public boolean hasEffect (ItemStack itemstack) {
        return true;
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    @Override
    public Set<String> getToolClasses(ItemStack stack) {
        return ImmutableSet.of("spleef_tool");
    }
}
