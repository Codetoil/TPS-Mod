/*
 * Copyright Codetoil (c) 2019-2026
 */

package io.codetoil.tpsmod;


import io.codetoil.tpsmod.platform.Services;
import org.greenrobot.eventbus.Subscribe;

public class DimensionTPSCalculator
{
	private static double maxTimeWait = 5.0;
	public final Dimension dimension;
	private long previousTotalWorldTime;
	private long previousMeasureTime;
	private double tps;

	public DimensionTPSCalculator(Dimension dimension)
	{
		this.dimension = dimension;
		TPSMod.EVENT_BUS.register(this);
	}

	public static double getMaxTimeWait()
	{
		return maxTimeWait;
	}

	static void setMaxTimeWait(double pMaxTimeWait)
	{
		if (pMaxTimeWait >= 0.0)
		{
			maxTimeWait = pMaxTimeWait;
		}
	}

	public long getPreviousMeasureTime() {
		return previousMeasureTime;
	}

	public long getPreviousTotalWorldTime() {
		return previousTotalWorldTime;
	}

	public double getTPS() {
		return tps;
	}

	@Subscribe
	public void onCalculateTPSEvent(CalculateTPSEvent event)
	{
		if (event.dimension.equals(this.dimension))
		{
			TPSMod.LOGGER.finest("Update TPS for dimension " + dimension);
			long totalWorldTime = Services.TPSMOD_METHODS.getTotalWorldTime(this.dimension);
			long measuredTime = System.currentTimeMillis();
			if (measuredTime - previousMeasureTime != 0)
			{
				tps = 1000.0 * ((double) (totalWorldTime - previousTotalWorldTime)) /
						((double) (measuredTime - previousMeasureTime));
				previousMeasureTime = measuredTime;
				previousTotalWorldTime = totalWorldTime;
			}
			TPSMod.LOGGER.finest(String.valueOf(totalWorldTime));
			TPSMod.LOGGER.finest(String.valueOf(measuredTime));
			TPSMod.LOGGER.finest(String.valueOf(tps));
		}
		TPSMod.EVENT_BUS.post(new CalculateTPSEvent(dimension));
	}


}
