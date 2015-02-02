/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (CommandAction.java) is part of Heimdall.
 * 
 *     CommandAction.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     CommandAction.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.heimdall.actions;

import java.io.Console;

public interface CommandAction {
//	Command getCommand();
	void exec(Console console, String... params) throws Exception;
	
	default void handleError(Console console, String message) {
		console.printf(message);
	}
	
	final static String WRONG_ARGUMENTS_NUMBER = "not enough arguments; type 'h' for help\n";
	final static String WRONG_ARGUMENT = "wrong argument: type 'h' for help\n";
}
