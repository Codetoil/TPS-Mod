/*
 * Copyright Codetoil (c) 2019-2026
 */

package io.codetoil.tpsmod.core;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import io.codetoil.tpsmod.core.commands.CommandHandler;
import io.codetoil.tpsmod.core.commands.ICommandSource;
import io.codetoil.tpsmod.core.platform.Services;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TPSMod
{
	public static final String MODID = "tpsmod";
	public static final String VERSION = "3.0.0-SNAPSHOT";
	public static final Logger LOGGER = Logger.getLogger("TPSMod");
	public static final EventBus EVENT_BUS = EventBus.builder()
			.logger(new org.greenrobot.eventbus.Logger.JavaLogger("TPSMod"))
			.build();
	public static final List<DimensionTPSCalculator> independentDimensionTPSMeasures = new ArrayList<>();
	public static long initialLoadTime;
	private static final long timeInit = System.currentTimeMillis();
	public static CommandDispatcher<ICommandSource> dispatcher = new CommandDispatcher<>();

	public static void init()
	{
		dispatcher.register(LiteralArgumentBuilder.<ICommandSource>literal("/tps")
				.then(RequiredArgumentBuilder.<ICommandSource, Dimension>
								argument("dimension", Services.TPSMOD_METHODS.dimensionArgumentType())
						.executes(c -> {
							CommandHandler.executeTPS(c.getSource(),
									DimensionArgumentType.getDimension(c, "dimension"));
							return 0;
						}))
				.executes(c -> {
					Dimension dimension = Services.TPSMOD_METHODS.getCurrentDimension();
					if (dimension == null)
					{
						c.getSource().notifyUser("//tps must either be run as a player/entity or one must supply a dimension.", Level.SEVERE);
						return 1;
					}
					CommandHandler.executeTPS(c.getSource(), dimension);
					return 0;
				}));
		dispatcher.register(LiteralArgumentBuilder.<ICommandSource>literal("/tpstoall")
				.then(RequiredArgumentBuilder.<ICommandSource, Dimension>
								argument("dimension", Services.TPSMOD_METHODS.dimensionArgumentType())
						.executes(c -> {
							CommandHandler.executeTPSTOALL(c.getSource(),
									DimensionArgumentType.getDimension(c, "dimension"));
							return 1;
						}))
				.executes(c -> {
					Dimension dimension = Services.TPSMOD_METHODS.getCurrentDimension();
					if (dimension == null)
					{
						c.getSource().notifyUser("//tps must either be run as a player/entity or one must supply a dimension.", Level.SEVERE);
						return 0;
					}
					CommandHandler.executeTPSTOALL(c.getSource(), dimension);
					return 1;
				}));
	}

	public static void serverStarting()
	{
		List<Dimension> dimensionsList = Services.TPSMOD_METHODS.getDimsAvailable();
		LOGGER.info("Dimensions: " + dimensionsList + " (" + dimensionsList.size() + ")");
		for (Dimension dimension : dimensionsList) {
			independentDimensionTPSMeasures.add(new DimensionTPSCalculator(dimension));
		}
		initialLoadTime = System.currentTimeMillis();
	}

	public static void serverStopping()
	{
		independentDimensionTPSMeasures.clear();
		Dimension.cleanCache();
	}
}
