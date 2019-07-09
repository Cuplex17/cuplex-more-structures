package net.cuplex.morestructures.world;

import net.cuplex.morestructures.MoreStructures;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.AbstractTempleFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;

import java.util.Random;

public class BeachHutFeature extends AbstractTempleFeature<DefaultFeatureConfig>
{
    public BeachHutFeature()
    {
        super(DefaultFeatureConfig::deserialize);
    }

    @Override
    protected int getSeedModifier()
    {
        return 0;
    }

    @Override
    public StructureFeature.StructureStartFactory getStructureStartFactory()
    {
        return BeachHutFeature.BeachHutStart::new;
    }

    @Override
    public String getName()
    {
        return "beach_hut";
    }

    public int getRadius()
    {
        return 8;
    }

    @Override
    public boolean shouldStartAt(ChunkGenerator<?> chunkGenerator_1, Random random_1, int int_1, int int_2)
    {
        return true;
    }

    public static class BeachHutStart extends StructureStart
    {
        public BeachHutStart(StructureFeature<?> structureFeature_1, int int_1, int int_2, Biome biome_1, MutableIntBoundingBox mutableIntBoundingBox_1, int int_3, long long_1)
        {
            super(structureFeature_1, int_1, int_2, biome_1, mutableIntBoundingBox_1, int_3, long_1);
        }

        @Override
        public void initialize(ChunkGenerator<?> chunkGenerator, StructureManager structureManager, int chunkX, int chunkZ, Biome biome)
        {
            DefaultFeatureConfig defaultFeatureConfig = chunkGenerator.getStructureConfig(biome, MoreStructures.BEACH_HUT_FEATURE);

            int x = chunkX * 16;
            int z = chunkZ * 16;

            BlockPos startingPos = new BlockPos(x, 0, z);
            BlockRotation rotation = BlockRotation.values()[this.random.nextInt(BlockRotation.values().length)];
            BeachHutGenerator.addParts(structureManager, startingPos, rotation, this.children, this.random, defaultFeatureConfig);
            this.setBoundingBoxFromChildren();;
        }
    }
}
