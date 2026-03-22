/*
 * Copyright Codetoil (c) 2019-2026
 */

package io.codetoil.tpsmod.core.commands;

import io.codetoil.tpsmod.core.Dimension;
import io.codetoil.tpsmod.core.DimensionTPSCalculator;
import io.codetoil.tpsmod.core.TPSMod;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.logging.Level;

public class CommandHandler
{
	public static void executeTPS(ICommandSource source, Dimension dimension)
	{
		TPSMod.LOGGER.finest(dimension.toString());
		if (System.currentTimeMillis() - TPSMod.initialLoadTime > 5000)
		{
			double TPS = getTPS(dimension);
			String TPS_STR = formatTPS(TPS);
			source.notifyUser("[TPS Mod v" + TPSMod.VERSION + "] " + TPS_STR + " tps in dimension " + dimension, Level.INFO);
		}
		else
		{
			source.notifyUser("The TPS Mod v" + TPSMod.VERSION + " is still loading. Please wait...", Level.SEVERE);
		}
	}

	public static void executeTPSTOALL(ICommandSource source, Dimension dimension)
	{
		if (System.currentTimeMillis() - TPSMod.initialLoadTime > 5000)
		{
			double TPS = getTPS(dimension);
			String TPS_STR = formatTPS(TPS);
			source.sendAsChatMessage("[TPS Mod v" + TPSMod.VERSION + "] " + TPS_STR + " tps in dimension " + dimension);
		}
		else
		{
			source.notifyUser("The TPS Mod v" + TPSMod.VERSION + " is still loading. Please wait...", Level.SEVERE);
		}
	}

	private static double getTPS(Dimension dimension)
	{
		double TPS = Double.NaN;
		for (DimensionTPSCalculator TPSdrop : TPSMod.independentDimensionTPSMeasures)
		{
			if (TPSdrop.dimension == dimension)
			{
				TPS = TPSdrop.getTPS();
			}
		}
		return TPS;
	}

	private static String formatTPS(double TPS)
	{
		DecimalFormat df = new DecimalFormat("#.##");
		df.setRoundingMode(RoundingMode.CEILING);
		return df.format(TPS);
	}
}
