package com.github.alexthe666.iceandfire.structures.processor;

import com.github.alexthe666.iceandfire.core.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.template.ITemplateProcessor;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;

import javax.annotation.Nullable;
import java.util.Random;

public class DreadRuinProcessor implements ITemplateProcessor {

    private float integrity = 1.0F;
    public DreadRuinProcessor(BlockPos position, PlacementSettings settings, Biome biome) {
    }

    @Nullable
    @Override
    public Template.BlockInfo processBlock(World worldIn, BlockPos pos, Template.BlockInfo blockInfoIn) {
        if (worldIn.rand.nextFloat() <= integrity) {
            if (worldIn.getBlockState(pos).getBlock() == Blocks.VINE) {
                return null;
            }
            if (blockInfoIn.blockState.getBlock() == ModBlocks.dread_stone_bricks) {
                IBlockState state = getRandomCrackedBlock(null, worldIn.rand);
                return new Template.BlockInfo(pos, state, null);
            }
            return blockInfoIn;
        }
        return blockInfoIn;

    }

    public static IBlockState getRandomCrackedBlock(@Nullable IBlockState prev, Random random) {
        float rand = random.nextFloat();
        if (rand < 0.3) {
            return ModBlocks.dread_stone_bricks.getDefaultState();
        } else if (rand < 0.6) {
            return ModBlocks.dread_stone_bricks_cracked.getDefaultState();
        } else {
            return ModBlocks.dread_stone_bricks_mossy.getDefaultState();
        }
    }
}
