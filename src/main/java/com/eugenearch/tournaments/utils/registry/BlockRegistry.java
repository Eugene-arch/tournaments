package com.eugenearch.tournaments.utils.registry;

import com.eugenearch.tournaments.blocks.*;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static Block SPLEEF_BLOCK = new SpleefBlock("spleef_block");
    public static Block TNTRUN_BLOCK = new TNTRunBlock("tntrun_block");
    public static Block LEAFESCAPE_BLOCK = new LeafEscapeBlock("leafescape_block");
    public static Block HEALER_BLOCK = new HealingRoad("healer_block");
    public static Block WET_SAND = new WetSand("wet_sand");
}
