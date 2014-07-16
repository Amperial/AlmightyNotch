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
import me.ampayne2.almightynotch.event.PlayerEvent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

public class FoodCookEvent extends PlayerEvent {
    public FoodCookEvent() {
        super("FoodCook");
        setProbability(7);
        setDescription("Cooks all the food in a player's inventory.");
        setOccurMessage("You got lucky and your food was cooked!");
    }

    @Override
    @SuppressWarnings("deprecation")
    public void trigger(AlmightyNotchPlugin plugin, Player player) {
        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack != null && isCookableFood(itemStack)) {
                cook(itemStack);
            }
        }
        player.updateInventory();
        //plugin.getMessenger().sendEventMessage(player, this);
    }

    @Override
    public boolean canOccur(AlmightyNotchPlugin plugin, Player player) {
        for (ItemStack itemStack : player.getInventory()) {
            if (itemStack != null && isCookableFood(itemStack)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if an ItemStack is food that can be cooked.
     *
     * @param itemStack The ItemStack.
     * @return True if the ItemStack is a food that can be cooked, else false.
     */
    @SuppressWarnings("deprecation")
    private boolean isCookableFood(ItemStack itemStack) {
        switch (itemStack.getType()) {
            case PORK:
            case POTATO:
            case RAW_BEEF:
            case RAW_CHICKEN:
                return true;
            case RAW_FISH:
                return itemStack.getData().getData() == 0 || itemStack.getData().getData() == 1;
            default:
                return false;
        }
    }

    /**
     * Cooks an ItemStack.
     *
     * @param itemStack The ItemStack to cook.
     */
    @SuppressWarnings("deprecation")
    private void cook(ItemStack itemStack) {
        switch (itemStack.getType()) {
            case PORK:
                itemStack.setType(Material.GRILLED_PORK);
                break;
            case POTATO:
                itemStack.setType(Material.BAKED_POTATO);
                break;
            case RAW_BEEF:
                itemStack.setType(Material.COOKED_BEEF);
                break;
            case RAW_CHICKEN:
                itemStack.setType(Material.COOKED_CHICKEN);
                break;
            case RAW_FISH:
                byte data = itemStack.getData().getData();
                itemStack.setType(Material.COOKED_FISH);
                itemStack.setData(new MaterialData(itemStack.getType(), data));
                break;
        }
    }
}
