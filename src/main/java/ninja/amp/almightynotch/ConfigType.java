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
package ninja.amp.almightynotch;

/**
 * An enumeration of the AlmightyNotch custom config types.
 */
public enum ConfigType implements ninja.amp.amplib.config.ConfigType {
    EVENTS("Events.yml"),
    NOTCH("Notch.yml");

    private final String fileName;

    private ConfigType(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Gets the ConfigType's file name.
     *
     * @return The file name of the ConfigType.
     */
    public String getFileName() {
        return fileName;
    }
}
