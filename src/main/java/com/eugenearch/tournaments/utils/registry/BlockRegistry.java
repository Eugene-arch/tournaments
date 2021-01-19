package com.eugenearch.tournaments.utils.registry;

import com.eugenearch.tournaments.blocks.SpleefBlock;
import com.eugenearch.tournaments.blocks.TNTRunBlock;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class BlockRegistry {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static Block SPLEEF_SNOW = new SpleefBlock("spleef_block");
    public static Block TNTRUN_SNOW = new TNTRunBlock("tntrun_block");
}
