/*
 * This file is part of RandomEvents.
 *
 * Copyright (c) 2014 <http://dev.bukkit.org/server-mods/randomevents//>
 *
 * RandomEvents is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * RandomEvents is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with RandomEvents.  If not, see <http://www.gnu.org/licenses/>.
 */
package me.ampayne2.randomevents;

import me.ampayne2.randomevents.commands.CommandController;
import me.ampayne2.randomevents.messaging.Messenger;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * The main class of the RandomEvents plugin.
 */
public class RandomEvents extends JavaPlugin {
    private Messenger messenger = null;
    private EventManager eventManager = null;
    private CommandController commandController = null;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        messenger = new Messenger(this);
        eventManager = new EventManager(this);
        if (getConfig().getBoolean("StartOnStartup", true)) {
            eventManager.startEvents();
        }

        commandController = new CommandController(this);
        getCommand("randomevents").setExecutor(commandController);
    }

    @Override
    public void onDisable() {
        getCommand("randomevents").setExecutor(null);
        commandController = null;
        eventManager.destroy();
        eventManager = null;
        messenger.destroy();
        messenger = null;
    }

    /**
     * Gets the {@link me.ampayne2.randomevents.messaging.Messenger}.
     *
     * @return The {@link me.ampayne2.randomevents.messaging.Messenger}.
     */
    public Messenger getMessenger() {
        return messenger;
    }

    /**
     * Gets the {@link me.ampayne2.randomevents.EventManager}.
     *
     * @return The {@link me.ampayne2.randomevents.EventManager}.
     */
    public EventManager getEventManager() {
        return eventManager;
    }

    /**
     * Gets the {@link me.ampayne2.randomevents.commands.CommandController}.
     *
     * @return The {@link me.ampayne2.randomevents.commands.CommandController}.
     */
    public CommandController getCommandController() {
        return commandController;
    }
}
