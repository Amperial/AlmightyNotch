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
package me.ampayne2.almightynotch.event.player;

import me.ampayne2.almightynotch.AlmightyNotchPlugin;
import me.ampayne2.almightynotch.Message;
import me.ampayne2.almightynotch.Mood;
import me.ampayne2.almightynotch.event.MinigameEvent;
import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PvPEvent extends MinigameEvent implements Listener {
    public PvPEvent() {
        super("PvP");
        setMoods(Mood.SATISFIED, Mood.GENEROUS);
        setProbability(5);
        setDescription("Rewards the first player to kill another player.");
        setOccurMessage(Message.PVP_EVENT);
        setMoodModifier(10);
    }

    @Override
    public void trigger(AlmightyNotchPlugin plugin, Server server) {
        super.trigger(plugin, server);
        plugin.getMessenger().sendMessage(Bukkit.getServer(), getOccurMessage());
    }

    @org.bukkit.event.EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (isRunning() && event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            // TODO: Reward killer
        }
    }

    @Override
    public void startMinigame(AlmightyNotchPlugin plugin) {
    }

    @Override
    public void stopMinigame(AlmightyNotchPlugin plugin) {
    }
}
