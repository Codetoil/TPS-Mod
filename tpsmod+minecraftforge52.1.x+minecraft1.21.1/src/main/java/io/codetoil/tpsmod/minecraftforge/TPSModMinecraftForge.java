package io.codetoil.tpsmod.minecraftforge;

import io.codetoil.tpsmod.TPSMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.event.server.ServerStoppingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import static io.codetoil.tpsmod.TPSMod.MODID;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(MODID)
public class TPSModMinecraftForge
{
    public TPSModMinecraftForge(FMLJavaModLoadingContext context)
    {
        context.getModEventBus().register(this);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onSetup(FMLCommonSetupEvent event) {
        TPSMod.init();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        TPSMod.serverStarting();
    }

    @SubscribeEvent
    public void onServerStopping(ServerStoppingEvent event)
    {
        TPSMod.serverStopping();
    }
}
