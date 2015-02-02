/*******************************************************************************
 * Copyleft (c) 2015, "Massimiliano Leone - <maximilianus@gmail.com> - https://plus.google.com/+MassimilianoLeone"
 * This file (ConsoleCommand.java) is part of Heimdall.
 * 
 *     ConsoleCommand.java is free software: you can redistribute it and/or modify
 *     it under the terms of the Lesser GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 * 
 *     ConsoleCommand.java is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See   the Lesser GNU General Public License for more details.
 * 
 *     You should have received a copy of the Lesser GNU General Public License along with .  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package net.iubris.heimdall.command;

import net.iubris.heimdall.actions.HelpAction;

public interface ConsoleCommand {
	String getHelpMessage();
	
//	static class Utils {
//		static 
		default 
		public String getPrefix(Enum<? extends ConsoleCommand> e, int tabs) {
			return HelpAction.tab(tabs)+"'"+e.name()+"': ";
		}
//	}
}
