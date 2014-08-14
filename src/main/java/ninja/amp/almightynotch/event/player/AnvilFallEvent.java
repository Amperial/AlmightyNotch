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
package ninja.amp.almightynotch.event.player;

import ninja.amp.almightynotch.AlmightyNotchPlugin;
import ninja.amp.almightynotch.Message;
import ninja.amp.almightynotch.Mood;
import ninja.amp.almightynotch.event.PlayerEvent;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class AnvilFallEvent extends PlayerEvent {
    private int fallHeight = 15;

    public AnvilFallEvent() {
        super("AnvilFall");
        setMoods(Mood.BORED, Mood.DISPLEASED);
        setProbability(5);
        setDescription("Drops an anvil onto a random player.");
        setOccurMessage(Message.ANVIL_FALL_EVENT);
        setMoodModifier(10);
    }

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        fallHeight = section.getInt("FallHeight", 15);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void trigger(AlmightyNotchPlugin plugin, Player player) {
        // Drop the anvil as high above the player as possible up to the configured fall height
        Location location = player.getLocation().getBlock().getLocation();
        for (int i = 0; i <= fallHeight; i++) {
            location.add(0, 1, 0);
            if (location.getBlock().getType() != Material.AIR || i == fallHeight) {
                location.subtract(0, 1, 0);
                location.getBlock().setType(Material.ANVIL);
                location.getBlock().setData((byte) 8);
                plugin.getMessenger().sendMessage(player, getOccurMessage());
                return;
            }
        }
    }

    @Override
    public boolean canOccur(AlmightyNotchPlugin plugin, Player player) {
        return player.getLocation().getBlock().getType() == Material.AIR && player.getEyeLocation().getBlock().getType() == Material.AIR;
    }
}
