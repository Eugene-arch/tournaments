package com.eugenearch.tournaments.utils.registry;

import com.eugenearch.tournaments.blocks.HealingRoad;
import com.eugenearch.tournaments.blocks.LeafEscapeBlock;
import com.eugenearch.tournaments.blocks.SpleefBlock;
import com.eugenearch.tournaments.blocks.TNTRunBlock;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static Block SPLEEF_BLOCK = new SpleefBlock("spleef_block");
    public static Block TNTRUN_BLOCK = new TNTRunBlock("tntrun_block");
    public static Block LEAFESCAPE_BLOCK = new LeafEscapeBlock("leafescape_block");
    public static Block HEALER_BLOCK = new HealingRoad("healer_block");
}
