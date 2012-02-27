/*
 * Copyright (C) 2012 Sorklin <sorklin at gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sorklin.magictorches.commands;

import org.bukkit.command.CommandSender;
import sorklin.magictorches.Exceptions.InsufficientPermissionsException;
import sorklin.magictorches.Exceptions.MissingOrIncorrectParametersException;
import sorklin.magictorches.MagicTorches;
import sorklin.magictorches.internals.Messaging;
import sorklin.magictorches.internals.Properties;

public class PriceCmd extends GenericCmd {
    
    /*Default the generic to must be executed by a player, and no minimum arguments.
    String permission = "";
    boolean mustBePlayer = true;
    int minArg = 0;
    */
    
    public PriceCmd(CommandSender cs, String args[]){
        super(cs, args);
        this.permission = Properties.permAccess;
    }
    
    public boolean execute() throws MissingOrIncorrectParametersException, InsufficientPermissionsException{
        errorCheck();
        if(!Properties.useEconomy)
            return true;
        
        if(MagicTorches.get().editQueue.containsKey(player)){
            Messaging.send(player, "`YThe current subtotal for the array is `a" 
                    + MagicTorches.econ
                        .format(MagicTorches.get().editQueue.get(player).priceArray()));
        } else {
            Messaging.send(player, "`rYou are not creating or editing an array.");
        }
        
        return true;
    }
}
