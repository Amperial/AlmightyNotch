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
package me.ampayne2.randomevents.events.mob;

import me.ampayne2.randomevents.RandomEvents;
import me.ampayne2.randomevents.api.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;

import java.util.UUID;

public class DinnerboneEvent extends WorldEvent {
    public DinnerboneEvent() {
        super("Dinnerbone");
        setProbability(2);
        setDescription("Causes all skeletons in the world to turn upside down.");
        setOccurMessage("We've been receiving sightings of upside down skeletons.");
    }

    @Override
    public void trigger(RandomEvents plugin, World world) {
        for (LivingEntity entity : world.getLivingEntities()) {
            if (entity.getType() == EntityType.SKELETON && entity.getCustomName() == null) {
                entity.setCustomName("Dinnerbone");
            }
        }
        plugin.getMessenger().broadcastEventMessage(this);
        final UUID worldId = world.getUID();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld(worldId);
                if (world != null) {
                    for (LivingEntity entity : world.getLivingEntities()) {
                        if (entity.getType() == EntityType.SKELETON && entity.getCustomName() != null && entity.getCustomName().equals("Dinnerbone")) {
                            entity.setCustomName(null);
                        }
                    }
                }
            }
        }, plugin.getConfig().getLong("Events.Dinnerbone.Duration", 15) * 20);
    }
}
