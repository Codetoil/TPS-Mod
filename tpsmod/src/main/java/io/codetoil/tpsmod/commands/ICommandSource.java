package io.codetoil.tpsmod.commands;

import java.util.logging.Level;

public interface ICommandSource {
    void notifyUser(String msg, Level level);
    void sendAsChatMessage(String msg);
}
