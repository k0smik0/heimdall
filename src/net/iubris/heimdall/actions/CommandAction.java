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