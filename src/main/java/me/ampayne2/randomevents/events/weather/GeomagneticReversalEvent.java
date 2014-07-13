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
package me.ampayne2.randomevents.events.weather;

import me.ampayne2.randomevents.RandomEvents;
import me.ampayne2.randomevents.api.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GeomagneticReversalEvent extends WorldEvent {
    private final Map<UUID, Integer> eventTasks = new HashMap<>();

    public GeomagneticReversalEvent() {
        super("GeomagneticReversal");
        setProbability(3);
        setDescription("Makes every player's compass go bonkers.");
        setOccurMessage("Everyone's compass is going bonkers.. looks like a geomagnetic reversal!");
    }

    @Override
    public void trigger(RandomEvents plugin, World world) {
        final UUID worldId = world.getUID();
        eventTasks.put(world.getUID(), Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getWorld(worldId).getPlayers()) {
                    double angle = RANDOM.nextInt((int) (2 * Math.PI));
                    player.setCompassTarget(player.getLocation().add(Math.sin(angle), 0, Math.cos(angle)));
                }
            }
        }, 0, 5));
        plugin.getMessenger().broadcastEventMessage(this);
        final Location spawnLocation = world.getSpawnLocation();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                Bukkit.getScheduler().cancelTask(eventTasks.get(worldId));
                World world = Bukkit.getWorld(worldId);
                if (world != null) {
                    for (Player player : world.getPlayers()) {
                        player.setCompassTarget(spawnLocation);
                    }
                }
                eventTasks.remove(worldId);
            }
        }, plugin.getConfig().getLong("Events.GeomagneticReversal.Duration", 15) * 20);
    }

    @Override
    public boolean canOccur(World world) {
        return super.canOccur(world) && !eventTasks.containsKey(world.getUID());
    }
}
