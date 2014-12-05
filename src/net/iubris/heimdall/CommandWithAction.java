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
