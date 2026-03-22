package io.codetoil.tpsmod.core;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public abstract class DimensionArgumentType implements ArgumentType<Dimension> {
    protected DimensionArgumentType() {}

    @Override
    public abstract Dimension parse(StringReader reader) throws CommandSyntaxException;

    public static Dimension getDimension(final CommandContext<?> context, final String name) {
        return context.getArgument(name, Dimension.class);
    }
}
