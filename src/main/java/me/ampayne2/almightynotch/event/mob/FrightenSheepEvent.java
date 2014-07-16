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
import me.ampayne2.almightynotch.event.WorldEvent;
import org.bukkit.World;

public class FrightenSheepEvent extends WorldEvent {
    public FrightenSheepEvent() {
        super("FrightenSheep");
        setProbability(2);
        setDescription("Makes all the sheep in the world drop their wool.");
        setOccurMessage("Something must have spooked the sheep! They all jumped out of their skin.");
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, World world) {
        // TODO: Shear all sheep

        //plugin.getMessenger().broadcastEventMessage(this);
    }
}
