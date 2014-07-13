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
package me.ampayne2.randomevents.api;

import me.ampayne2.randomevents.RandomEvents;

/**
 * An event that rewards the first player to complete the minigame.
 */
public abstract class MinigameEvent extends RandomEvent {
    private boolean isRunning = false;

    public MinigameEvent(String name) {
        super(name, DefaultEventHandler.MINIGAME.getHandler());
    }

    /**
     * Triggers the minigame event.
     *
     * @param plugin The {@link me.ampayne2.randomevents.RandomEvents} instance.
     */
    public void trigger(RandomEvents plugin) {
        isRunning = true;
    }

    /**
     * Checks if the minigame event can occur.
     *
     * @return True if the minigame event isn't already running.
     */
    public boolean canOccur() {
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
     * Lets the minigame event be triggered again.
     */
    public void reset() {
        isRunning = false;
    }
}
