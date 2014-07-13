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
package me.ampayne2.randomevents.events.player;

import me.ampayne2.randomevents.RandomEvents;
import me.ampayne2.randomevents.api.PlayerEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class AnvilFallEvent extends PlayerEvent {
    public AnvilFallEvent() {
        super("AnvilFall");
        setProbability(5);
        setDescription("Drops an anvil onto a random player.");
        setOccurMessage("&4Look out below!");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void trigger(RandomEvents plugin, Player player) {
        // Drop the anvil as high above the player as possible up to the configured fall height
        int fallHeight = plugin.getConfig().getInt("Events.AnvilFall.FallHeight", 15);
        Location location = player.getLocation().getBlock().getLocation();
        for (int i = 0; i <= fallHeight; i++) {
            location.add(0, 1, 0);
            if (location.getBlock().getType() != Material.AIR || i == fallHeight) {
                location.subtract(0, 1, 0);
                location.getBlock().setType(Material.ANVIL);
                location.getBlock().setData((byte) 8);
                plugin.getMessenger().sendEventMessage(player, this);
                return;
            }
        }
    }

    @Override
    public boolean canOccur(Player player) {
        return super.canOccur(player) && player.getLocation().getBlock().getType() == Material.AIR && player.getEyeLocation().getBlock().getType() == Material.AIR;
    }
}
