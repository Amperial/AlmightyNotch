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
import me.ampayne2.almightynotch.event.PlayerEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class OreSmeltEvent extends PlayerEvent {
    public OreSmeltEvent() {
        super("OreSmelt");
        setMoods(Mood.SATISFIED);
        setProbability(7);
        setDescription("Smelts all the ore in a random player's inventory.");
        setOccurMessage(Message.ORE_SMELT_EVENT);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void trigger(AlmightyNotchPlugin plugin, Player player) {
        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack != null && isSmeltableOre(itemStack.getType())) {
                smelt(itemStack);
            }
        }
        player.updateInventory();
        plugin.getMessenger().sendMessage(player, getOccurMessage());
    }

    @Override
    public boolean canOccur(AlmightyNotchPlugin plugin, Player player) {
        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack != null && isSmeltableOre(itemStack.getType())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if a Material is a smeltable ore.
     *
     * @param material The Material.
     * @return True if the Material is a smeltable ore, else false.
     */
    private boolean isSmeltableOre(Material material) {
        switch (material) {
            case COAL_ORE:
            case IRON_ORE:
            case GOLD_ORE:
            case QUARTZ_ORE:
                return true;
            default:
                return false;
        }
    }

    /**
     * Smelts an ItemStack.
     *
     * @param itemStack The ItemStack to smelt.
     */
    private void smelt(ItemStack itemStack) {
        switch (itemStack.getType()) {
            case COAL_ORE:
                itemStack.setType(Material.COAL);
                break;
            case IRON_ORE:
                itemStack.setType(Material.IRON_INGOT);
                break;
            case GOLD_ORE:
                itemStack.setType(Material.GOLD_INGOT);
                break;
            case QUARTZ_ORE:
                itemStack.setType(Material.QUARTZ);
                break;
        }
    }
}
