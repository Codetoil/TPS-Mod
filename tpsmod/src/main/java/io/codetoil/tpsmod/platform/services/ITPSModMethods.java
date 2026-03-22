package io.codetoil.tpsmod.platform.services;

import io.codetoil.tpsmod.Dimension;
import io.codetoil.tpsmod.DimensionArgumentType;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ITPSModMethods {
    List<Dimension> getDimsAvailable();
    long getTotalWorldTime(Dimension dimension);
    DimensionArgumentType dimensionArgumentType();
    @Nullable Dimension getCurrentDimension();
}
