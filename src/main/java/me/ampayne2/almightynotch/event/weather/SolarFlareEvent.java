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
package me.ampayne2.almightynotch.event.weather;

import me.ampayne2.almightynotch.AlmightyNotchPlugin;
import me.ampayne2.almightynotch.Message;
import me.ampayne2.almightynotch.Mood;
import me.ampayne2.almightynotch.event.WorldEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

public class SolarFlareEvent extends WorldEvent {
    public SolarFlareEvent() {
        super("SolarFlare");
        setMoods(Mood.DISPLEASED);
        setProbability(3);
        setDescription("Sets all mobs in the world on fire.");
        setOccurMessage(Message.SOLAR_FLARE_EVENT);
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, World world) {
        for (LivingEntity entity : world.getLivingEntities()) {
            entity.setFireTicks(plugin.getConfig().getInt("Events.SolarFlare.Duration", 5) * 20);
        }
        plugin.getMessenger().sendMessage(Bukkit.getServer(), getOccurMessage());
    }

    @Override
    public boolean canOccur(AlmightyNotchPlugin plugin, World world) {
        long time = world.getTime();
        return time < 12300 || time > 23850 && !world.hasStorm();
    }
}
