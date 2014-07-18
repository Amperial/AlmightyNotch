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
package me.ampayne2.almightynotch.event.mob;

import me.ampayne2.almightynotch.AlmightyNotchPlugin;
import me.ampayne2.almightynotch.Message;
import me.ampayne2.almightynotch.Mood;
import me.ampayne2.almightynotch.event.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Skeleton;

import java.util.UUID;

public class DinnerboneEvent extends WorldEvent {
    private long duration = 15 * 20;

    public DinnerboneEvent() {
        super("Dinnerbone");
        setMoods(Mood.BORED);
        setProbability(2);
        setDescription("Causes all skeletons in the world grow ears.");
        setOccurMessage(Message.DINNERBONE_EVENT);
        setMoodModifier(5);
    }

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        duration = section.getLong("Duration", 15) * 20;
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, World world) {
        for (LivingEntity entity : world.getLivingEntities()) {
            if (entity.getType() == EntityType.SKELETON && entity.getCustomName() == null && entity.getPassenger() == null) {
                LivingEntity ears = (LivingEntity) world.spawnEntity(entity.getLocation(), EntityType.SKELETON);
                ears.setCustomName("Dinnerbone");
                entity.setPassenger(ears);
            }
        }
        plugin.getMessenger().sendMessage(Bukkit.getServer(), getOccurMessage());
        final UUID worldId = world.getUID();
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld(worldId);
                if (world != null) {
                    for (LivingEntity entity : world.getLivingEntities()) {
                        if (entity.getType() == EntityType.SKELETON && entity.getPassenger() != null && entity.getPassenger() instanceof Skeleton) {
                            LivingEntity ears = (LivingEntity) entity.getPassenger();
                            if (ears.getCustomName() != null && ears.getCustomName().equals("Dinnerbone")) {
                                entity.setPassenger(null);
                                ears.remove();
                            }
                        }
                    }
                }
            }
        }, duration);
    }
}
