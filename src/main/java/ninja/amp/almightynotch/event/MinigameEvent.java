/*
 * This file is part of AlmightyNotch.
 *
 * Copyright (c) 2014 <http://dev.bukkit.org/server-mods/almightynotch//>
 *
 * AlmightyNotch is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * AlmightyNotch is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with AlmightyNotch.  If not, see <http://www.gnu.org/licenses/>.
 */
package ninja.amp.almightynotch.event;

import ninja.amp.almightynotch.AlmightyNotchPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Server;

/**
 * An event that rewards the first player to complete the minigame.
 */
public abstract class MinigameEvent extends Event<Server> {
    private boolean isRunning = false;
    private static final EventHandler handler = new MinigameEventHandler();

    public MinigameEvent(String name) {
        super(name, handler);
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, Server server) {
        startMinigame(plugin);
        isRunning = true;
    }

    @Override
    public boolean canOccur(AlmightyNotchPlugin plugin, Server server) {
        return !isRunning;
    }

    /**
     * Checks if the minigame event is running.
     *
     * @return True if the minigame event is running, else false.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Starts the minigame.
     *
     * @param plugin The {@link ninja.amp.almightynotch.AlmightyNotchPlugin} instance.
     */
    public abstract void startMinigame(AlmightyNotchPlugin plugin);

    /**
     * Stops the minigame.
     *
     * @param plugin The {@link ninja.amp.almightynotch.AlmightyNotchPlugin} instance.
     */
    public abstract void stopMinigame(AlmightyNotchPlugin plugin);

    /**
     * Lets the minigame event be triggered again.
     */
    public void reset(AlmightyNotchPlugin plugin) {
        stopMinigame(plugin);
        isRunning = false;
    }

    /**
     * Handles attempting to trigger a {@link MinigameEvent}.
     */
    public static class MinigameEventHandler extends EventHandler<MinigameEvent> {
        @Override
        public boolean triggerEvent(AlmightyNotchPlugin plugin, MinigameEvent event) {
            if (event.canOccur(plugin, Bukkit.getServer())) {
                event.trigger(plugin, Bukkit.getServer());
                plugin.getMessenger().debug("Triggered MinigameEvent " + event.getName());
                if (event.getMoodModifier() != 0) {
                    plugin.getNotch().modifyMoodLevel(event.getMoodModifier());
                }
                return true;
            } else {
                plugin.getMessenger().debug("Couldn't trigger MinigameEvent " + event.getName());
                return false;
            }
        }
    }
}
