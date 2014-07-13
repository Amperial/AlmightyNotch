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
import org.bukkit.Location;

/**
 * An event that can occur at a location.
 */
public abstract class LocationEvent extends RandomEvent {
    public LocationEvent(String name) {
        super(name, DefaultEventHandler.LOCATION.getHandler());
    }

    /**
     * Triggers the location event.
     *
     * @param plugin   The {@link me.ampayne2.randomevents.RandomEvents} instance.
     * @param location The location to trigger the event at.
     */
    public abstract void trigger(RandomEvents plugin, Location location);

    /**
     * Checks if the location event can occur at a location.
     *
     * @param location The location.
     * @return True if the location is suitable, else false.
     */
    public boolean canOccur(Location location) {
        return true;
    }
}
