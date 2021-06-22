package com.eugenearch.tournaments;

import com.eugenearch.tournaments.blocks.tileentity.TileEntityBlockTNTRun;
import com.eugenearch.tournaments.proxy.ProxyCommon;
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
        modid = Reference.MOD_ID,
        name = Reference.MOD_NAME,
        version = Reference.MOD_VERSION
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
            clientSide = Reference.PROXY_CLIENT,
            serverSide = Reference.PROXY_COMMON
    )
    public static ProxyCommon proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        GameRegistry.registerTileEntity(TileEntityBlockTNTRun.class, new ResourceLocation(Reference.MOD_ID + ":tntrun_blockte"));
    }

    @EventHandler
    public void postInit(FMLInitializationEvent event) {
        logger.info("\"Eugene Tournaments\" initialization completed.");
    }

}
