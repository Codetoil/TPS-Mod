package io.codetoil.tpsmod.service;

import io.codetoil.tpsmod.Dimension;

import java.util.List;

public interface ITPSModMethods {
    List<Dimension> getDimsAvailable();
    double getTimeInSeconds();
}
