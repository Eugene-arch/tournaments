package com.eugenearch.tournaments.utils.init;

import com.eugenearch.tournaments.blocks.SpleefBlock;
import com.eugenearch.tournaments.blocks.TNTRunBlock;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public class InitBlocks {
    public static final List<Block> BLOCKS = new ArrayList<>();

    public static Block SPLEEF_SNOW = new SpleefBlock("spleef_block");
    public static Block TNTRUN_SNOW = new TNTRunBlock("tntrun_block");
}
