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
import me.ampayne2.randomevents.api.WorldEvent;
import me.ampayne2.randomevents.util.Util;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SoundEvent extends WorldEvent {
    public SoundEvent() {
        super("Sound");
        setProbability(8);
        setDescription("Plays random sounds to all the players in a world.");
        setOccurMessage("Whoah, what was that? You better check outside.");
    }

    @Override
    public void trigger(RandomEvents plugin, World world) {
        int amount = plugin.getConfig().getInt("Events.Sound.Amount", 3);
        for (Player player : world.getPlayers()) {
            for (int i = 0; i < amount; i++) {
                player.playSound(player.getLocation(), Util.randomEnum(Sound.class), 10, 1);
            }
        }
        plugin.getMessenger().broadcastEventMessage(this);
    }
}
