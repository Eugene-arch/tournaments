package com.eugenearch.tournaments.blocks;

import com.eugenearch.tournaments.Main;
import com.eugenearch.tournaments.Reference;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;
import com.eugenearch.tournaments.utils.registry.BlockRegistry;
import com.eugenearch.tournaments.utils.registry.ItemRegistry;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

public class BlockSpleef extends BlockBreakable implements IHasModel {
    public BlockSpleef(String name) {
        super(Material.CRAFTED_SNOW, false);

        ResourceLocation RS = new ResourceLocation(Reference.MOD_ID, name);

        setRegistryName(RS);
        setUnlocalizedName(name);

        this.blockSoundType = SoundType.SNOW;

        this.blockHardness = 50.0F;
        this.setHarvestLevel("spleef_tool", 0);
        setCreativeTab(Main.TOURNAMENTS_TAB);

        BlockRegistry.BLOCKS.add(this);
        ItemRegistry.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isTranslucent(IBlockState state) {
        return true;
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("tile.spleef_block.tooltip"));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Items.AIR;
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer entityPlayer = ((EntityPlayer) entityIn);
            entityPlayer.getFoodStats().addStats(1, 0.1F);
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }
}
