/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (CommandExceptionListener.java) is part of Heimdall.
 * 
 *     CommandExceptionListener.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     CommandExceptionListener.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.heimdall.listeners;

import java.io.Console;

import net.iubris.heimdall.command.ConsoleCommand;

public class CommandExceptionListener implements ExceptionListener {
	private static final String COMMAND_ERROR = "Command error [%1$s]: [%2$s]%n";
	
	private final Console console;
	private final ConsoleCommand consoleCommand;

	public CommandExceptionListener(Console console, ConsoleCommand /*String*/ command) {
		this.console = console;
		this.consoleCommand = command;
	}

	@Override
	public void handleException(Exception e) {
		console.printf( COMMAND_ERROR, consoleCommand, e.getMessage() );
		e.printStackTrace();
	}
}
