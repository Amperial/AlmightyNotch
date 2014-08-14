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
import ninja.amp.almightynotch.event.WorldEvent;
import ninja.amp.almightynotch.util.Util;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SoundEvent extends WorldEvent {
    private int amount = 3;

    public SoundEvent() {
        super("Sound");
        setMoods(Mood.BORED, Mood.SLEEPY);
        setProbability(8);
        setDescription("Plays random sounds to all the players in a world.");
        setOccurMessage(Message.SOUND_EVENT);
    }

    @Override
    public void load(ConfigurationSection section) {
        super.load(section);
        amount = section.getInt("Amount", 3);
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, World world) {
        for (Player player : world.getPlayers()) {
            for (int i = 0; i < amount; i++) {
                player.playSound(player.getLocation(), Util.randomEnum(Sound.class), 10, 1);
            }
        }
        plugin.getMessenger().sendMessage(Bukkit.getServer(), getOccurMessage());
    }
}
