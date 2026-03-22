package io.codetoil.tpsmod.core;

public class CalculateTPSEvent {
    public final Dimension dimension;

    public CalculateTPSEvent(Dimension dimension)
    {
        this.dimension = dimension;
    }
}