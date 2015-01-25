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
