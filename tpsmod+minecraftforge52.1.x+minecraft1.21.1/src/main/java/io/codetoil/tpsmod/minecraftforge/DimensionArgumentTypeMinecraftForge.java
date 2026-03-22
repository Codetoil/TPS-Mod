package io.codetoil.tpsmod.minecraftforge;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.codetoil.tpsmod.core.Dimension;
import io.codetoil.tpsmod.core.DimensionArgumentType;
import net.minecraft.resources.ResourceLocation;

public class DimensionArgumentTypeMinecraftForge extends DimensionArgumentType {
    protected DimensionArgumentTypeMinecraftForge() {
        super();
    }

    public static DimensionArgumentType dimensionArgumentType() {
        return new DimensionArgumentTypeMinecraftForge();
    }

    @Override
    public Dimension parse(StringReader reader) throws CommandSyntaxException {
        ResourceLocation location = ResourceLocation.read(reader);
        return Dimension.of(location.getNamespace(), location.getPath());
    }
}
