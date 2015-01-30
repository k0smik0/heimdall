package net.iubris.heimdall.actions;

import java.io.Console;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

import net.iubris.heimdall.command.ConsoleCommand;

public class QuitAction implements CommandAction {
	
	private final Set<PreExit> preExits;
	
	private final Set<String> errorsStrings = new HashSet<>();
	private final File errorsFile = new File("errors.txt");
	
	public QuitAction(Set<PreExit> preExits) {
		this.preExits = preExits;
	}
	
	public QuitAction(PreExit preExit) {
		this.preExits = new HashSet<>();
		preExits.add(preExit);
	}
	
	public QuitAction() {
		preExits = new HashSet<>();
//		PrintStream errorPrintStream = null; 
//		try {
//			errorPrintStream = new PrintStream(errorsFile) {
//				@Override
//				public void println(String x) {
//					if (!errorsStrings.contains(x)) {
//						errorsStrings.add(x);
//						if (!errorsFile.exists()) {
//							try {
//								errorsFile.createNewFile();
//							} catch (IOException e) {
//								System.out.println("unable to create "+errorsFile.getName()+"; check your filesystem");
//							}
//						}
//						super.println(x);
//					}				
//				}			
//			};
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
//		System.setErr(errorPrintStream);
	}
	
	public void addPreExit(PreExit preExit) {
		preExits.add(preExit);
	}
	
	@Override
	public void exec(Console console, String... params) {
		if (errorsStrings.size()>0) {
			console.printf("some errors occured; see '"+errorsFile.getName()+"'");
		}
		if (preExits.size()>0)
			preExits.stream().forEach(p->p.doPreExiting());
		console.printf("Bye%n");
		System.exit(0);
	}
	
	@FunctionalInterface
	public interface PreExit {
		void doPreExiting();
	}
	
	public enum QuitCommand implements ConsoleCommand {
		Q;
		@Override
		public String getHelpMessage() {
			return message;
		}
		private String message = 
//				HelpAction.tab(1)+"'"+name()+
				"exit";
	}
}


