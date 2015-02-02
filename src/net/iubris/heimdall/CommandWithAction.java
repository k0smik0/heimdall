/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (CommandWithAction.java) is part of Heimdall.
 * 
 *     CommandWithAction.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     CommandWithAction.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.heimdall;

import net.iubris.heimdall.actions.CommandAction;
import net.iubris.heimdall.command.ConsoleCommand;

public class CommandWithAction {

	private final Enum<? extends ConsoleCommand> consoleCommand;
	private final CommandAction commandAction;
	
	public CommandWithAction(Enum<? extends ConsoleCommand> consoleCommand, CommandAction commandAction) {
		this.consoleCommand = consoleCommand;
		this.commandAction = commandAction;
	}
	
	public Enum<? extends ConsoleCommand> getConsoleCommand() { return consoleCommand; }
//	public ConsoleCommand getConsoleCommand() { return consoleCommand; }

	public CommandAction getCommandAction() { return commandAction; }

	@Override
	public int hashCode() {
		return consoleCommand.hashCode() ^ commandAction.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof CommandWithAction))
			return false;
		CommandWithAction cwa = (CommandWithAction) o;
		return this.consoleCommand.equals(cwa.getConsoleCommand()) &&
				this.commandAction.equals(cwa.getCommandAction());
	}	
	
}
