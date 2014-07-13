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
import org.bukkit.World;

/**
 * An event that can occur to a world.
 */
public abstract class WorldEvent extends RandomEvent {
    public WorldEvent(String name) {
        super(name, DefaultEventHandler.WORLD.getHandler());
    }

    /**
     * Triggers the world event.
     *
     * @param plugin The {@link me.ampayne2.randomevents.RandomEvents} instance.
     * @param world  The world to trigger the event at.
     */
    public abstract void trigger(RandomEvents plugin, World world);

    /**
     * Checks if the world event can occur at a world.
     *
     * @param world The world.
     * @return True if the world is enabled in the config and suitable, else false.
     */
    public boolean canOccur(World world) {
        return true;
    }
}
