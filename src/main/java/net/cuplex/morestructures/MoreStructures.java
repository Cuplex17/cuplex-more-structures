package net.cuplex.morestructures;

import net.cuplex.morestructures.world.BeachHutFeature;
import net.cuplex.morestructures.world.BeachHutGenerator;
import net.cuplex.morestructures.world.WanderingTraderShipFeature;
import net.cuplex.morestructures.world.WanderingTraderShipGenerator;
import net.fabricmc.api.ModInitializer;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.ChanceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.loot.LootTables;

public class MoreStructures implements ModInitializer
{
	//New Structures
	public static final StructurePieceType WANDERING_TRADER_SHIP_PIECE_TYPE = Registry.register(Registry.STRUCTURE_PIECE, "wandering_trader_ship_piece", WanderingTraderShipGenerator.Piece::new);
	public static final StructureFeature<DefaultFeatureConfig> WANDERING_TRADER_SHIP_FEATURE = Registry.register(Registry.FEATURE, "wandering_trader_ship_feature", new WanderingTraderShipFeature());
	public static final StructureFeature<?> WANDERING_TRADER_SHIP = Registry.register(Registry.STRUCTURE_FEATURE, "wandering_trader_ship", WANDERING_TRADER_SHIP_FEATURE);

	public static final StructurePieceType BEACH_HUT_PIECE_TYPE = Registry.register(Registry.STRUCTURE_PIECE, "beach_hut_piece", BeachHutGenerator.Piece::new);
	public static final StructureFeature<DefaultFeatureConfig> BEACH_HUT_FEATURE = Registry.register(Registry.FEATURE, "beach_hut_feature", new BeachHutFeature());
	public static final StructureFeature<?> BEACH_HUT_SHIP = Registry.register(Registry.STRUCTURE_FEATURE, "beach_hut", BEACH_HUT_FEATURE);

	@Override
	public void onInitialize()
	{
		System.out.println("More Structures Initialised.");

		//Register structures
		Feature.STRUCTURES.put("Wandering Trader Ship", WANDERING_TRADER_SHIP_FEATURE);
		Feature.STRUCTURES.put("Beach Hut", BEACH_HUT_FEATURE);

		for(Biome biome : Registry.BIOME)
		{
			if(biome.getCategory() == Biome.Category.OCEAN)
			{
				biome.addStructureFeature(WANDERING_TRADER_SHIP_FEATURE, new DefaultFeatureConfig());
				biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(WANDERING_TRADER_SHIP_FEATURE, new DefaultFeatureConfig(), Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(160)));
			}

			if(biome.getCategory() == Biome.Category.BEACH)
			{
				biome.addStructureFeature(BEACH_HUT_FEATURE, new DefaultFeatureConfig());
				biome.addFeature(GenerationStep.Feature.SURFACE_STRUCTURES, Biome.configureFeature(BEACH_HUT_FEATURE, new DefaultFeatureConfig(), Decorator.CHANCE_PASSTHROUGH, new ChanceDecoratorConfig(40)));
			}
		}
	}
}
