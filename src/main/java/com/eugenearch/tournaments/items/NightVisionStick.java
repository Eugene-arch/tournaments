package com.eugenearch.tournaments.items;

import com.eugenearch.tournaments.Main;
import com.eugenearch.tournaments.utils.interfaces.IHasModel;
import com.eugenearch.tournaments.utils.registry.ItemRegistry;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class NightVisionStick extends Item implements IHasModel {

    public NightVisionStick(String name) {
        super();

        setRegistryName(name);
        setUnlocalizedName(name);
        setCreativeTab(Main.TOURNAMENTS_TAB);

        ItemRegistry.ITEMS.add(this);
    }

    @Override
    public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
        super.addInformation(stack, player, tooltip, advanced);
        tooltip.add(I18n.format("item.nightvision_stick.tooltip"));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (playerIn.getActivePotionEffect(MobEffects.NIGHT_VISION) != null) {
            playerIn.removePotionEffect(MobEffects.NIGHT_VISION);
        } else {
            playerIn.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 1000000));
        }
        return new ActionResult<ItemStack>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }

    @Override
    public void registerModels() {
        Main.proxy.registerItemRenderer(this, 0, "inventory");
    }

    public boolean hasEffect(ItemStack par1ItemStack) {
        return true;
    }
}