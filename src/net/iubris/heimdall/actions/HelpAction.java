/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (HelpAction.java) is part of Heimdall.
 * 
 *     HelpAction.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     HelpAction.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.heimdall.actions;

import java.io.Console;
import java.util.Set;
import java.util.stream.Collectors;

import net.iubris.heimdall.actions.QuitAction.QuitCommand;
import net.iubris.heimdall.command.ConsoleCommand;

public class HelpAction implements CommandAction {
	
	private final String helpString;
	
	public HelpAction(String title, Set<Enum<? extends ConsoleCommand>> consoleCommands) {
		helpString = ""
				+title+"\n"
				+consoleCommands.stream()
				.map(c->tab(1)+"'"+c.name().toLowerCase()+"': "+((ConsoleCommand)c).getHelpMessage())
				.collect(Collectors.joining("\n"))
				.concat("\n").concat(tab(1)).concat("'"+QuitCommand.Q.name().toLowerCase()+"': "+QuitCommand.Q.getHelpMessage())
				.concat("\n").concat(tab(1)).concat("'"+HelpCommand.H.name().toLowerCase()+"': "+HelpCommand.H.getHelpMessage()+"\n");
	}

	@Override
	public void exec(Console console, String... params) {
		console.printf(helpString);
	}

	public static String tab(int many) {
		String tab = "";
		for (int i = 1; i <= many; i++) {
			tab += "\t";
		}
		
		return tab;
	}
	
	public enum HelpCommand implements ConsoleCommand {
		H;
		@Override
		public String getHelpMessage() {
			return helpMessage;
		}
		private String helpMessage = "display this help";
	}
}
