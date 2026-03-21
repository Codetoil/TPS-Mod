/*
 * Copyright Codetoil (c) 2019-2026
 */

package io.codetoil.tpsmod;

import com.google.common.collect.Lists;
import io.codetoil.tpsmod.service.ITPSModMethods;

import java.util.List;
import java.util.logging.Logger;

public class TPSMod
{
	public static final String MODID = "tpsmod";
	public static final String VERSION = "3.0.0-SNAPSHOT";
	public static final Logger LOGGER = Logger.getLogger("TPSMod");
	private static final List<MeasureTPSdrop> independentDimensionTPSMeasures = Lists.newArrayList();
	public static double initialLoadTime;
	public static ITPSModMethods tpsmodMethods;

	/*List<Command> internalCommandList = new ArrayList<>();
	try
	{
		BiMap<String, ArgumentWrapper<?>> args = HashBiMap.create();
		switch (LaunchCommon.getSide())
		{
			case CLIENT:
			case BOTH:
				args.put("dimension", new ArgumentWrapper<>("dimension", new ArgumentParserInteger(), "Dimension to measure the tps of", false));
				break;
			case SERVER:
				args.put("dimension", new ArgumentWrapper<>("dimension", new ArgumentParserInteger(), "Dimension to measure the tps of", true));
				break;
		}
		internalCommandList.add(new Command("/tps", CommandHandler::executeTPS, args, Command.Side.BOTH));
		internalCommandList.add(new Command("/tpstoall", CommandHandler::executeTPSTOALL, args, Command.Side.BOTH));
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}*/

	public static void init()
	{
		LOGGER.info("TPSMod v" + VERSION + " initializing");
		List<Dimension> dimensionsList = tpsmodMethods.getDimsAvailable();
		LOGGER.info("Dimensions: " + dimensionsList + " (" + dimensionsList.size() + ")");
		for (Dimension dimension : dimensionsList) {
			TPSMod.independentDimensionTPSMeasures.add(new MeasureTPSdrop(dimension));
		}
	}
}
