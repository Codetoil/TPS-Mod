package io.codetoil.tpsmod.core.platform;

import io.codetoil.tpsmod.core.TPSMod;
import io.codetoil.tpsmod.core.platform.services.ITPSModMethods;

import java.util.Iterator;
import java.util.ServiceLoader;

// Service loaders are a built-in Java feature that allow us to locate implementations of an interface that vary from one
// environment to another. In the context of MultiLoader we use this feature to access a mock API in the common code that
// is swapped out for the platform specific implementation at runtime.
public class Services
{

    // In this example we provide a platform helper which provides information about what platform the mod is running on.
    // For example this can be used to check if the code is running on Forge vs Fabric, or to ask the modloader if another
    // mod is loaded.
    public static final ITPSModMethods TPSMOD_METHODS = load(ITPSModMethods.class);

    // This code is used to load a service for the current environment. Your implementation of the service must be defined
    // manually by including a text file in META-INF/services named with the fully qualified class name of the service.
    // Inside the file you should write the fully qualified class name of the implementation to load for the platform. For
    // example our file on Forge points to ForgePlatformHelper while Fabric points to FabricPlatformHelper.
    public static <T> T load(Class<T> clazz) {

        final Iterator<T> serviceIterator = ServiceLoader.load(clazz).iterator();
        if (!serviceIterator.hasNext()) {
            throw new NullPointerException("Failed to load service for " + clazz.getName());
        }
        final T loadedService = serviceIterator.next();
        TPSMod.LOGGER.fine("Loaded " + loadedService + " for service " + clazz);
        return loadedService;
    }
}
