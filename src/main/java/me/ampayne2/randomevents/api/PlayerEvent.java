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
import org.bukkit.entity.Player;

/**
 * An event that can occur to a player.
 */
public abstract class PlayerEvent extends RandomEvent {
    public PlayerEvent(String name) {
        super(name, DefaultEventHandler.PLAYER.getHandler());
    }

    /**
     * Triggers the player event.
     *
     * @param plugin The {@link me.ampayne2.randomevents.RandomEvents} instance.
     * @param player The player to trigger the event for.
     */
    public abstract void trigger(RandomEvents plugin, Player player);

    /**
     * Checks if the player event can occur to a player.
     *
     * @param player The player.
     * @return True if the event can occur to the player.
     */
    public boolean canOccur(Player player) {
        return true;
    }
}
