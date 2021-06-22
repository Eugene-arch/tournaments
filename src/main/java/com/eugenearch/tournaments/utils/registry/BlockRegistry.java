package com.eugenearch.tournaments.utils.registry;

import com.eugenearch.tournaments.blocks.*;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static Block SPLEEF_BLOCK = new BlockSpleef("spleef_block");
    public static Block TNTRUN_BLOCK = new BlockTNTRun("tntrun_block");
    public static Block LEAFESCAPE_BLOCK = new BlockLeafEscape("leafescape_block");
    public static Block HEALER_BLOCK = new BlockHealingRoad("healer_block");
    public static Block WET_SAND = new BlockWetSand("wet_sand");
}
