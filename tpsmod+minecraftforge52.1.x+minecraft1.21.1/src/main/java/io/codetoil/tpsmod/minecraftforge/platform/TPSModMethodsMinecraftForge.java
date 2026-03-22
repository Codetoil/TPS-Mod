package io.codetoil.tpsmod.minecraftforge.platform;

import com.google.common.collect.Streams;
import io.codetoil.tpsmod.core.Dimension;
import io.codetoil.tpsmod.core.DimensionArgumentType;
import io.codetoil.tpsmod.minecraftforge.DimensionArgumentTypeMinecraftForge;
import io.codetoil.tpsmod.core.platform.services.ITPSModMethods;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class TPSModMethodsMinecraftForge implements ITPSModMethods {
    @Override
    public List<Dimension> getDimsAvailable() {
        if (FMLEnvironment.dist == Dist.CLIENT) {
            net.minecraft.client.Minecraft minecraft = net.minecraft.client.Minecraft.getInstance();
            if (!minecraft.hasSingleplayerServer()) {
                Dimension dimension = getCurrentDimension();
                if (dimension == null) return List.of();
                return List.of(dimension);
            }
        }
        return Streams.stream(ServerLifecycleHooks.getCurrentServer().getAllLevels())
                .map(ServerLevel::dimension)
                .map(ResourceKey::location)
                .map(location -> Dimension.of(location.getNamespace(), location.getPath()))
                .toList();
    }

    @Override
    public long getTotalWorldTime(Dimension dimension) {
        if (!getDimsAvailable().contains(dimension)) {
            return 0;
        }
        if (FMLEnvironment.dist == Dist.CLIENT) {
            net.minecraft.client.Minecraft minecraft = net.minecraft.client.Minecraft.getInstance();
            if (!minecraft.hasSingleplayerServer()) {
                if (dimension != getCurrentDimension()) {
                    return 0;
                } else if (minecraft.level != null) {
                    return minecraft.level.getGameTime();
                }
            }
        }
        Optional<Long> totalWorldTime = Streams.stream(ServerLifecycleHooks.getCurrentServer().getAllLevels())
                .filter((level) ->
                        level.dimension().location().getNamespace().equals(dimension.NAMESPACE) &&
                        level.dimension().location().getPath().equals(dimension.PATH))
                .map(ServerLevel::getGameTime)
                .findAny();
        if (totalWorldTime.isPresent()) {
            return totalWorldTime.get();
        } else {
            return 0;
        }
    }

    @Override
    public DimensionArgumentType dimensionArgumentType() {
        return DimensionArgumentTypeMinecraftForge.dimensionArgumentType();
    }

    @Override
    public @Nullable Dimension getCurrentDimension() {
        if (!FMLEnvironment.dist.isClient()) {
            return null;
        }
        net.minecraft.client.Minecraft minecraft = net.minecraft.client.Minecraft.getInstance();
        Level level = minecraft.level;
        assert level != null;
        ResourceLocation dimension = level.dimension().location();
        return Dimension.of(dimension.getNamespace(), dimension.getPath());
    }
}
