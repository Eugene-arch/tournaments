package com.eugenearch.tournaments.blocks;

import com.eugenearch.tournaments.Main;
import com.eugenearch.tournaments.Reference;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;
import com.eugenearch.tournaments.utils.registry.BlockRegistry;
import com.eugenearch.tournaments.utils.registry.ItemRegistry;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.List;

public class BlockWetSand extends BlockBreakable implements IHasModel {
    public BlockWetSand(String name) {
        super(Material.SAND, false);

        ResourceLocation RS = new ResourceLocation(Reference.MOD_ID, name);

        setRegistryName(RS);
        setUnlocalizedName(name);

        setCreativeTab(Main.TOURNAMENTS_TAB);

        BlockRegistry.BLOCKS.add(this);
        ItemRegistry.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        IBlockState plant = plantable.getPlant(world, pos.offset(direction));
        return plant.getBlock().equals(Blocks.REEDS);
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("tile.wet_sand.tooltip"));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }
}
