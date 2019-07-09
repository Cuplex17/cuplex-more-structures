package net.cuplex.morestructures.world;

import net.cuplex.morestructures.MoreStructures;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.datafixers.fixes.ChunkPalettedStorageFix;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.structure.*;
import net.minecraft.structure.processor.BlockIgnoreStructureProcessor;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableIntBoundingBox;
import net.minecraft.world.Heightmap;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.loot.LootTables;

import java.util.List;
import java.util.Random;

public class BeachHutGenerator
{
    public static final Identifier id = new Identifier("morestructures", "beach_hut");
    //public static final Identifier lootTableID = new Identifier("morestructures", "beach_hut_loot");

    public static void addParts(StructureManager structureManager, BlockPos blockPos, BlockRotation rotation, List<StructurePiece> list_1, Random random, DefaultFeatureConfig defaultFeatureConfig)
    {
        list_1.add(new BeachHutGenerator.Piece(structureManager, id, blockPos, rotation));
    }

    public static class Piece extends SimpleStructurePiece {
        private final Identifier identifier;

        public Piece(StructureManager structureManager_1, CompoundTag compoundTag_1)
        {
            super(MoreStructures.BEACH_HUT_PIECE_TYPE, compoundTag_1);

            this.identifier = new Identifier(compoundTag_1.getString("Template"));

            this.setStructureData(structureManager_1);
        }

        public Piece(StructureManager structureManager, Identifier identifier, BlockPos pos, BlockRotation rotation)
        {
            super(MoreStructures.BEACH_HUT_PIECE_TYPE, 0);

            this.identifier = identifier;
            this.pos = pos;

            this.setStructureData(structureManager);
        }

        public void setStructureData(StructureManager structureManager)
        {
            Structure structure_1 = structureManager.getStructureOrBlank(this.identifier);
            StructurePlacementData structurePlacementData_1 = (new StructurePlacementData()).setMirrored(BlockMirror.NONE).setPosition(pos).addProcessor(BlockIgnoreStructureProcessor.IGNORE_STRUCTURE_BLOCKS);
            this.setStructureData(structure_1, this.pos, structurePlacementData_1);
        }

        @Override
        protected void handleMetadata(String string_1, BlockPos blockPos, IWorld iWorld, Random random, MutableIntBoundingBox mutableIntBoundingBox)
        {
            if("loot_chest".equals(string_1))
            {
                LootableContainerBlockEntity.setLootTable(iWorld, random, blockPos.down(), LootTables.FISHING_GAMEPLAY);
            }
            if("work_station".equals(string_1))
            {
                Random rand_1 = new Random();
                int int_1 = rand_1.nextInt(10);
                switch(int_1)
                {
                    case 1:
                        iWorld.setBlockState(blockPos.down(), Blocks.LOOM.getDefaultState(), 1);
                        break;
                    case 2:
                        iWorld.setBlockState(blockPos.down(), Blocks.BARREL.getDefaultState(), 1);
                        break;
                    case 3:
                        iWorld.setBlockState(blockPos.down(), Blocks.SMOKER.getDefaultState(), 1);
                        break;
                    case 4:
                        iWorld.setBlockState(blockPos.down(), Blocks.BLAST_FURNACE.getDefaultState(), 1);
                        break;
                    case 5:
                        iWorld.setBlockState(blockPos.down(), Blocks.CARTOGRAPHY_TABLE.getDefaultState(), 1);
                        break;
                    case 6:
                        iWorld.setBlockState(blockPos.down(), Blocks.FLETCHING_TABLE.getDefaultState(), 1);
                        break;
                    case 7:
                        iWorld.setBlockState(blockPos.down(), Blocks.GRINDSTONE.getDefaultState(), 1);
                        break;
                    case 8:
                        iWorld.setBlockState(blockPos.down(), Blocks.SMITHING_TABLE.getDefaultState(), 1);
                        break;
                    case 9:
                        iWorld.setBlockState(blockPos.down(), Blocks.STONECUTTER.getDefaultState(), 1);
                        break;
                    default:
                        iWorld.setBlockState(blockPos.down(), Blocks.BARREL.getDefaultState(), 1);
                }
            }

            World world = iWorld.getWorld();
            VillagerEntity villagerEntity= new VillagerEntity(EntityType.VILLAGER, world);
            villagerEntity.setPersistent();
            villagerEntity.setPositionAndAngles(blockPos, 0.0F, 0.0F);
            if("villager".equals(string_1))
            {
                iWorld.spawnEntity(villagerEntity);
            }

            iWorld.setBlockState(blockPos, Blocks.AIR.getDefaultState(), 1);
        }

        @Override
        public boolean generate(IWorld iWorld_1, Random random_1, MutableIntBoundingBox mutableIntBoundingBox_1, ChunkPos chunkPos_1) {
            int yHeight = iWorld_1.getTop(Heightmap.Type.WORLD_SURFACE_WG, this.pos.getX(), this.pos.getZ());
            this.pos = this.pos.add(0, yHeight - 6, 0);
            return super.generate(iWorld_1, random_1, mutableIntBoundingBox_1, chunkPos_1);
        }
    }
}
