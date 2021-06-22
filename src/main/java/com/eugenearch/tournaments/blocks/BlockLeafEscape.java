package com.eugenearch.tournaments.blocks;

import com.eugenearch.tournaments.Main;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;
import com.eugenearch.tournaments.utils.registry.BlockRegistry;
import com.eugenearch.tournaments.utils.registry.ItemRegistry;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
import java.util.Random;

public class BlockLeafEscape extends BlockNewLeaf implements IHasModel {

    protected IBlockState thisBlockState;

    public BlockLeafEscape(String name) {
        this.thisBlockState = this.getDefaultState().withProperty(DECAYABLE, Boolean.TRUE).withProperty(CHECK_DECAY, Boolean.TRUE);
        this.setDefaultState(this.thisBlockState);

        setUnlocalizedName(name);
        setRegistryName(name);

        setCreativeTab(Main.TOURNAMENTS_TAB);
        setBlockUnbreakable();

        BlockRegistry.BLOCKS.add(this);
        ItemRegistry.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state) {
        super.onBlockAdded(world, pos, state);
        this.beginLeavesDecay(state, world, pos);
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("tile.leafescape_block.tooltip"));
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this, 1);
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
    }

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer entityPlayer = ((EntityPlayer) entityIn);
            entityPlayer.getFoodStats().addStats(1, 0.1F);
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    // TODO: Simplified parent methods
    @Override
    public int getMetaFromState(IBlockState state) {
        return 12;
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return BlockPlanks.EnumType.ACACIA;
    }

    @Override
    protected ItemStack getSilkTouchDrop(IBlockState state) {
        return null;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
        items.add(new ItemStack(this, 1, 0));
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.thisBlockState;
    }

    @Override
    public NonNullList<ItemStack> onSheared(ItemStack item, net.minecraft.world.IBlockAccess world, BlockPos pos, int fortune) {
        return NonNullList.withSize(1, new ItemStack(Blocks.AIR));
    }

    // TODO: Erased parent methods
    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {

    }

    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {

    }

}
