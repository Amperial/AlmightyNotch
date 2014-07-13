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
package me.ampayne2.randomevents.api.handlers;

import me.ampayne2.randomevents.RandomEvents;
import me.ampayne2.randomevents.api.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles attempting to trigger a {@link me.ampayne2.randomevents.api.WorldEvent}.
 */
public class WorldEventHandler extends EventHandler<WorldEvent> {

    @Override
    public boolean triggerEvent(RandomEvents plugin, WorldEvent event) {
        List<World> worlds = new ArrayList<>();
        for (String worldName : plugin.getConfig().getStringList("Worlds")) {
            World world = Bukkit.getWorld(worldName);
            if (world != null) {
                worlds.add(world);
            }
        }
        while (worlds.size() > 0) {
            World world = worlds.get(RANDOM.nextInt(worlds.size()));
            if (event.canOccur(world)) {
                event.trigger(plugin, world);
                return true;
            } else {
                worlds.remove(world);
            }
        }
        return false;
    }
}
