package net.iubris.heimdall.actions;

import java.io.Console;
import java.util.List;

public interface CommandAction {
//	Command getCommand();
	void exec(Console console, List<String> params) throws Exception;
}