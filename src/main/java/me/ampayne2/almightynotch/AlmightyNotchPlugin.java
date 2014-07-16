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
package me.ampayne2.almightynotch;

import me.ampayne2.amplib.AmpJavaPlugin;
import me.ampayne2.amplib.messenger.DefaultMessage;

import java.util.EnumSet;

/**
 * The main class of the AlmightyNotch plugin.
 */
public class AlmightyNotchPlugin extends AmpJavaPlugin {
    private AlmightyNotch notch;

    @Override
    public void onEnable() {
        DefaultMessage.PREFIX.setMessage("&9[&7AlmightyNotch&9]&7 ");
        DefaultMessage.RELOAD.setMessage("Reloaded AlmightyNotch.");
        enableAmpLib();
        getMessenger().registerMessages(EnumSet.allOf(Message.class));

        notch = new AlmightyNotch(this);
    }

    @Override
    public void onDisable() {
        notch = null;

        disableAmpLib();
    }

    /**
     * Gets the {@link me.ampayne2.almightynotch.AlmightyNotch} instance.
     *
     * @return The {@link me.ampayne2.almightynotch.AlmightyNotch} instance.
     */
    public AlmightyNotch getNotch() {
        return notch;
    }
}
