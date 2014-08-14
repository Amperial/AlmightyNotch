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
import org.bukkit.Location;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

/**
 * An event that can occur at a location.
 */
public abstract class LocationEvent extends Event<Location> {
    private static final EventHandler handler = new LocationEventHandler();

    public LocationEvent(String name) {
        super(name, handler);
    }

    /**
     * Checks if the location event can occur at a location.
     *
     * @param location The location.
     * @return True if the location is suitable, else false.
     */
    public boolean canOccur(Location location) {
        // TODO: Check if the location is safe
        return true;
    }

    /**
     * Handles attempting to trigger a {@link LocationEvent}.
     */
    public static class LocationEventHandler extends EventHandler<LocationEvent> {
        @Override
        public boolean triggerEvent(AlmightyNotchPlugin plugin, LocationEvent event) {
            List<World> worlds = new ArrayList<>();
            for (String worldName : plugin.getConfig().getStringList("Worlds")) {
                World world = Bukkit.getWorld(worldName);
                if (world != null) {
                    worlds.add(world);
                }
            }
            while (worlds.size() > 0) {
                World world = worlds.get(RANDOM.nextInt(worlds.size()));
                // TODO: Get a random location
            }
            return true;
        }
    }
}
