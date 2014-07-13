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
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

public class SolarFlareEvent extends WorldEvent {
    public SolarFlareEvent() {
        super("SolarFlare");
        setProbability(3);
        setDescription("Sets all mobs in the world on fire.");
        setOccurMessage("It's so hot the chickens are laying hard-boiled eggs...");
    }

    @Override
    public void trigger(RandomEvents plugin, World world) {
        for (LivingEntity entity : world.getLivingEntities()) {
            entity.setFireTicks(plugin.getConfig().getInt("Events.SolarFlare.Duration", 5) * 20);
        }
        plugin.getMessenger().broadcastEventMessage(this);
    }

    @Override
    public boolean canOccur(World world) {
        long time = world.getTime();
        return super.canOccur(world) && time < 12300 || time > 23850;
    }
}
