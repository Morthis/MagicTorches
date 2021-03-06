package sorklin.magictorches.internals.interfaces;

import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
* Copyright (C) 2011 Sorklin <sorklin@gmail.com>
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

public interface MTReceiver {
    public Location getLocation();
    public boolean receive(boolean signal);
    public void reset();
    public void setLocation(Location loc);
    public Location getParent();
    public void setParent(Location parent);
    public void teleportTo(Player player);
    public double getDelay();
    @Override
    public String toString();
    @Override
    public boolean equals(Object obj);
    @Override
    public int hashCode();
}
