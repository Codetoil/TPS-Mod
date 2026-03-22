package io.codetoil.tpsmod.core.platform.services;

import io.codetoil.tpsmod.core.Dimension;
import io.codetoil.tpsmod.core.DimensionArgumentType;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ITPSModMethods {
    List<Dimension> getDimsAvailable();
    long getTotalWorldTime(Dimension dimension);
    DimensionArgumentType dimensionArgumentType();
    @Nullable Dimension getCurrentDimension();
}
