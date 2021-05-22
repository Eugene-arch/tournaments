package com.eugenearch.tournaments;

import com.eugenearch.tournaments.blocks.tileentity.TNTRunBlockTE;
import com.eugenearch.tournaments.proxy.CommonProxy;
import com.eugenearch.tournaments.utils.registry.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = Reference.MODID,
        name = Reference.NAME,
        version = Reference.VERSION
)
public class Main {

    public static Logger logger;

    public static final Item.ToolMaterial EVENT_MATERIAL = EnumHelper.addToolMaterial("EVENT_MATERIAL", 1, -1, 20000F, 0F, 0);

    public static final CreativeTabs TOURNAMENTS_TAB = new CreativeTabs(10, "tournaments") {
        @SideOnly(Side.CLIENT)
        public ItemStack getTabIconItem() {
            return new ItemStack(ItemRegistry.SPLEEF_SHOVEL);
        }
    };

    @Instance
    public static Main instance;

    @SidedProxy(
            clientSide = Reference.CLIENT,
            serverSide = Reference.COMMON
    )
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Boolean
        logger = event.getModLog();
        GameRegistry.registerTileEntity(TNTRunBlockTE.class, new ResourceLocation(Reference.MODID + ":tntrun_blockte"));
    }

    @EventHandler
    public void postInit(FMLInitializationEvent event) {
        logger.info("\"Eugene Tournaments\" initialization completed.");
    }

}
