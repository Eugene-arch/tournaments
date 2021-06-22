package com.eugenearch.tournaments.utils.registry;

import com.eugenearch.tournaments.items.ItemNightVisionStick;
import com.eugenearch.tournaments.items.ItemSlimeChunkFounder;
import com.eugenearch.tournaments.items.ItemSpleefTool;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemRegistry {
    public static final List<Item> ITEMS = new ArrayList<>();

    public static Item SPLEEF_SHOVEL = new ItemSpleefTool("spleef_shovel");
    public static Item NV_STICK = new ItemNightVisionStick("nightvision_stick");
    public static Item SLIMIZER = new ItemSlimeChunkFounder("slimizer");
}
