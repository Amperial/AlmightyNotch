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
package me.ampayne2.almightynotch.event.world;

import me.ampayne2.almightynotch.AlmightyNotchPlugin;
import me.ampayne2.almightynotch.event.WorldEvent;
import org.bukkit.World;

public class FireworksEvent extends WorldEvent {
    public FireworksEvent() {
        super("Fireworks");
        setProbability(3);
        setDescription("Shoots fireworks around every player.");
        setOccurMessage("It's like the 4th of July!");
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, World world) {
        // TODO: Shoot fireworks

        //plugin.getMessenger().broadcastEventMessage(this);
    }
}
