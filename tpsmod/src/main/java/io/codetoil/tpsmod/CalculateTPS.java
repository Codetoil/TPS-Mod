/*
 * Copyright Codetoil (c) 2019-2026
 */

package io.codetoil.tpsmod;


public class CalculateTPS
{
	private final Dimension dimension;
	private long previousTotalWorldTime;
	private double previousMeasureTime;
	private double tps;

	public CalculateTPS(Dimension dimension)
	{
		this.dimension = dimension;
	}

	public void ReceivedEvent(LitEvent event)
	{
		if (event.getType() == TPSMod.updateTPS)
		{
			if (event.getData()[2].equals(this.dimension))
			{
				updateTPS();
			}
		}
	}

	private void updateTPS()
	{
		FrontEnd.debug("Update TPS for dimension " + dimension);
		long totalWorldTime = FrontEnd.GET_FIELDS().getTotalWorldTime(this.dimension);
		double measuredTime = LaunchCommon.getTimeInSeconds();
		if (measuredTime - previousMeasureTime != 0)
		{
			tps = (totalWorldTime - previousTotalWorldTime) / (measuredTime - previousMeasureTime);
			previousMeasureTime = measuredTime;
			previousTotalWorldTime = totalWorldTime;
		}
		FrontEnd.debug(totalWorldTime);
		FrontEnd.debug(measuredTime);
		FrontEnd.debug(tps);
	}

	@Override
	public LitEventHandler.EventListener getListener()
	{
		return this;
	}

	public double getTPS()
	{
		return tps;
	}
}
