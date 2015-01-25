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