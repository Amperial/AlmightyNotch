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
package ninja.amp.almightynotch.event.mob;

import ninja.amp.almightynotch.AlmightyNotchPlugin;
import ninja.amp.almightynotch.Message;
import ninja.amp.almightynotch.Mood;
import ninja.amp.almightynotch.event.LocationEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class MobHordeEvent extends LocationEvent {
    public MobHordeEvent() {
        super("MobHorde");
        setMoods(Mood.BORED);
        setProbability(4);
        setDescription("Spawns a horde of mobs at a random location.");
        setOccurMessage(Message.MOB_HORDE_EVENT);
        setMoodModifier(10);
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, Location location) {
        // TODO: Spawn mob horde

        plugin.getMessenger().sendMessage(Bukkit.getServer(), getOccurMessage(), String.valueOf(location.getBlockX()), String.valueOf(location.getBlockZ()));
    }
}
