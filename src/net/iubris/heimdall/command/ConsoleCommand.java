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
