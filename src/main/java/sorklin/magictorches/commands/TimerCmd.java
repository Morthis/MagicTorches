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
import sorklin.magictorches.internals.Messaging;
import sorklin.magictorches.internals.Properties;
import sorklin.magictorches.internals.TorchEditor;

public class TimerCmd extends GenericCmd {
    
    /*Default the generic to must be executed by a player, and no minimum arguments.
    String permission = "";
    boolean mustBePlayer = true;
    int minArg = 0;
    */
    
    public TimerCmd(CommandSender cs, String args[]){
        super(cs, args);
        this.permission = Properties.permCreateTimer;
    }
    
    public boolean execute() throws MissingOrIncorrectParametersException, InsufficientPermissionsException{
        errorCheck();

        TorchEditor te = mt.editQueue.get(player);
        
        if(args.length > 1){
            try {
                te.setTimeOut(Double.parseDouble(args[1]));
            } catch (NumberFormatException nfe) { 
                throw new MissingOrIncorrectParametersException("Unable to parse number: " + args[1]);
            }
        }
        
        if(!mt.editQueue.containsKey(player))
            return true;
        
        mt.editQueue.get(player).setNextType(Properties.MtType.TIMER);
        Messaging.send(player, "`gReceiver type set to `wTIMER`g.");
        
        return true;
    }
}
