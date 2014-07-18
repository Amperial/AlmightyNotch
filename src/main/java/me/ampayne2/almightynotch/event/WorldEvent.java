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
package me.ampayne2.almightynotch.event;

import me.ampayne2.almightynotch.AlmightyNotchPlugin;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

/**
 * An event that can occur to a world.
 */
public abstract class WorldEvent extends Event<World> {
    private static final EventHandler handler = new WorldEventHandler();

    public WorldEvent(String name) {
        super(name, handler);
    }

    /**
     * Handles attempting to trigger a {@link WorldEvent}.
     */
    public static class WorldEventHandler extends EventHandler<WorldEvent> {
        @Override
        public boolean triggerEvent(AlmightyNotchPlugin plugin, WorldEvent event) {
            List<World> worlds = new ArrayList<>();
            for (String worldName : plugin.getConfig().getStringList("Worlds")) {
                World world = Bukkit.getWorld(worldName);
                if (world != null) {
                    worlds.add(world);
                }
            }
            while (worlds.size() > 0) {
                World world = worlds.get(RANDOM.nextInt(worlds.size()));
                if (event.canOccur(plugin, world)) {
                    event.trigger(plugin, world);
                    plugin.getMessenger().debug("Triggered WorldEvent " + event.getName() + " for world " + world.getName());
                    if (event.getMoodModifier() != 0) {
                        plugin.getNotch().modifyMoodLevel(event.getMoodModifier());
                    }
                    return true;
                } else {
                    worlds.remove(world);
                    plugin.getMessenger().debug("Couldn't trigger WorldEvent " + event.getName() + " for world " + world.getName());
                }
            }
            return false;
        }
    }
}
