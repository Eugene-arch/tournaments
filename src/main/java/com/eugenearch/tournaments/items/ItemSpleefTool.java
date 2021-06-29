package com.eugenearch.tournaments.items;

import com.eugenearch.tournaments.Main;
import com.eugenearch.tournaments.Reference;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;
import com.eugenearch.tournaments.utils.registry.BlockRegistry;
import com.eugenearch.tournaments.utils.registry.ItemRegistry;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.List;
import java.util.Set;

public class ItemSpleefTool extends ItemTool implements IHasModel {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(BlockRegistry.SPLEEF_BLOCK);

    public ItemSpleefTool(String name) {
        super(-1.0F, 1.0F, Main.EVENT_MATERIAL, EFFECTIVE_ON);

        ResourceLocation RS = new ResourceLocation(Reference.MOD_ID, name);

        setRegistryName(RS);
        setUnlocalizedName(name);
        setCreativeTab(Main.TOURNAMENTS_TAB);

        ItemRegistry.ITEMS.add(this);
    }

    public boolean canHarvestBlock(IBlockState blockIn) {
        return blockIn.getBlock() == BlockRegistry.SPLEEF_BLOCK;
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("item.spleef_shovel.tooltip"));
    }

    @Override
    public boolean hasEffect(ItemStack itemstack) {
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
