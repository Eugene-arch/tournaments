package com.eugenearch.tournaments.blocks;

import com.eugenearch.tournaments.Main;
import com.eugenearch.tournaments.blocks.tileentity.TNTRunBlockTE;
import com.eugenearch.tournaments.utils.registry.BlockRegistry;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;

import com.eugenearch.tournaments.utils.registry.ItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBreakable;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class TNTRunBlock extends BlockBreakable implements IHasModel, ITileEntityProvider {
    protected static final AxisAlignedBB COLLISION_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.9375D, 1.0D);

    public TNTRunBlock(String name) {
        super(Material.ROCK, false);

        setRegistryName(name);
        setUnlocalizedName(name);

        this.blockSoundType = SoundType.STONE;

        this.blockHardness = 20.0F;
        this.setHarvestLevel("tntrun_tool", 0);
        setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        BlockRegistry.BLOCKS.add(this);
        ItemRegistry.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("tile.tntrun_block.tooltip"));
        tooltip.add(I18n.format("tile.tntrun_block.tooltip2"));
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

    @Override
    public void onEntityWalk(World worldIn, BlockPos pos, Entity entityIn) {
        entityIn.motionX *= 1.1D;
        entityIn.motionZ *= 1.1D;
        if (entityIn instanceof EntityPlayer) {
            EntityPlayer entityPlayer = ((EntityPlayer) entityIn);
            entityPlayer.getFoodStats().addStats(1, 0.1F);
        }
        super.onEntityWalk(worldIn, pos, entityIn);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state)
    {
        return false;
    }

    @Override
    public boolean isTranslucent(IBlockState state) {
        return true;
    }

    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
    {
        return COLLISION_AABB;
    }

    @Override
    public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
        if (entity instanceof EntityPlayer) {
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TNTRunBlockTE) {
                ((TNTRunBlockTE) tileEntity).trigger();
            }
        }
        super.onEntityCollidedWithBlock(world, pos, state, entity);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TNTRunBlockTE();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TNTRunBlockTE();
    }
}
