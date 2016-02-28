import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;


public class CommandLineHandler {
	
	/**
	 * The CommandLineHandler instance.
	 */
	private static CommandLineHandler instance;
	/**
	 * A map that maps all commands names/aliases to their respective command object.
	 */
	private static final Map<String,Command> mcommands;
	/**
	 * A set containing all the commands 1/command.
	 */
	private static final Set<Command> scommands;
	
	/**
	 * @return The CommandLineHandler instance, if not initialized it will initialize it.
	 */
	public static CommandLineHandler getSingleton() {
		if(instance == null)
			instance = new CommandLineHandler(new ChronoTimer(),System.out);
		return instance;
	}
	
	/**
	 * Returns a command with that name or alias.
	 * @param cmd - the name or alias of the command.
	 * @return The command or null if there is no command with that name/alias.
	 */
	public static final Command getCommand(String cmd) {
		return mcommands.get(cmd.toLowerCase());
	}
	
	/**
	 * Adds a given command.
	 * @param c - The command that should be added.
	 * @return true if the command was added successfully, false if there was already a command that shares the name or an alias.
	 */
	public static final boolean addCommand(Command c) {
		if(c == null) return false;
		if(mcommands.containsKey(c.name)) return false;
		for(String a : c.aliases) {
			if(mcommands.containsKey(a)) return false;
		}
		mcommands.put(c.name,c);
		scommands.add(c);
		for(String a : c.aliases) {
			mcommands.put(a,c);
		}
		return true;
	}
	
	/**
	 * Removes a given command.
	 * @param c - The command that should be removed.
	 * @return true if the command was previously added, false if it was never added.
	 */
	public static final boolean removeCommand(Command c) {
		if(c == null) return false;
		if(mcommands.remove(c.name) == null)
			return false;
		for(String a : c.aliases)
			mcommands.remove(a);
		scommands.remove(c);
		return true;
	}
	
	/**
	 * The chronotimer being simulated.
	 */
	private final ChronoTimer timer;
	/**
	 * The stream that all output should be sent to.
	 */
	private PrintStream stream;
	/**
	 * The scanner used for user input.
	 */
	private final Scanner scan;
	/**
	 * Boolean indicating if the CLI should keep running or if it should stop.
	 */
	protected boolean keepRunning;
	
	public static void main(String[] args) {
		
		getSingleton().handle(System.out);
		
	}
	
	/**
	 * Executes a given string as a command with arguements.
	 * @param in - The input string that contains the command name/alias and its arguments.
	 * @return true if the command exists, false if it does not exist.
	 */
	public boolean executeCommand(String in) {
		
		String[] oargs = in.split(" ");
		Command cmd = getCommand(oargs[0]);
		
		if(cmd == null) {
			stream.println("Invalid command, you may want to use the 'help' command.");
			return false;
		}
		String[] args = new String[oargs.length-1];
		for(int i=0;i<oargs.length-1;i++)
			args[i] = oargs[i+1];
		
		cmd.run(stream, timer, args);
		return true;
	}
	
	/**
	 * The main CLI loop. It blocks waiting for input from the user then executes the given command until it should quit.
	 * @param s - The stream it should send all output to.
	 */
	private void handle(PrintStream s) {
		
		String input;
		
		while(keepRunning) {
			
			s.print(">");
			s.flush();
			input = scan.nextLine();
			
			executeCommand(input);
			
		}
		
		s.println("Simulator ended.");
		
	}
	
	public CommandLineHandler(ChronoTimer t, PrintStream s) {
		timer = t;
		scan = new Scanner(System.in);
		stream = s;
		keepRunning = true;
	}
	
	// Statically initialize all the commands.
	static {
		mcommands = new HashMap<String,Command>();
		scommands = new HashSet<Command>();
		
		addCommand(new Command("help","[command]...","Lists given or all commands.","?") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				Collection<Command> cm;
				if(args.length == 0) {
					cm = scommands;
				} else {
					cm = new LinkedHashSet<Command>();
					for(String arg : args) {
						Command cmd = getCommand(arg.toLowerCase());
						if(cmd == null) {
							stream.println("No command found called '" + arg + "'");
							continue;
						}
						cm.add(cmd);
					}
				}
				if(cm.size() == 0)
					return true;
				stream.println("COMMAND | USAGE | DESCRIPTION | ALIASES");
				stream.println("=======================================");
				for(Command c : cm) {
					stream.println(c + " | " + c + " " + c.usage + " | " + c.description + " | " + c.aliases);
				}
				return true;
			}
		});
		
		addCommand(new Command("quit", "", "Quits the entire simulator.", 0, 0, "exit", "q", "stop") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				getSingleton().keepRunning = false;
				return true;
			}
		});
		
		addCommand(new Command("power", "[on/off]", "Toggles the power on/off for the Chrono timer.", 0, 1, "pow") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				if(args.length == 0) {
					if(timer.togglePower())
						stream.println("Chronotimer has powered on.");
					else
						stream.println("Chronotimer has powered off.");
					return true;
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("on")) {
						timer.setPower(true);
						return true;
					} else if(args[0].equalsIgnoreCase("off")) {
						timer.setPower(false);
						return true;
					}
				}
				return false;
			}
		});
		
		addCommand(new Command("on", "", "Turns the chrono timer on.", 0, 0) {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				getSingleton().executeCommand("power on");
				return true;
			}
		});
		
		addCommand(new Command("off", "", "Turns the chrono timer off.", 0, 0) {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				getSingleton().executeCommand("power off");
				return true;
			}
		});
		
		addCommand(new Command("time", "", "Sets the current time.", 1, 1, "settime") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				//Requires TimeManager to be implemented.
				stream.println("Time is not implemented yet.");
				return true;
			}
		});
		
		addCommand(new Command("reset", "", "Resets the chrono timer back to initial state.", 0, 0) {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				timer.reset();
				stream.println("Chronotimer has been reset.");
				return true;
			}
		});
		
		addCommand(new Command("num", "<number>", "Sets <number> as the next competetor to start.", 1, 1, "queue") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				try {
					int number = Integer.parseInt(args[0]);
				} catch(NumberFormatException e) {
					return false;
				}
				//Implement when we have API for chronotimer written.
				stream.println("Num is not implemented yet.");
				return true;
			}
		});
		
		addCommand(new Command("clear", "<number>", "Clear <number> as the next competetor.", 1, 1, "clr", "dequeue") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				try {
					int number = Integer.parseInt(args[0]);
				} catch(NumberFormatException e) {
					return false;
				}
				//Implement when we have API for chronotimer written.
				stream.println("Clear is not implemented yet.");
				return true;
			}
		});
		
		addCommand(new Command("swap", "", "Swaps the front two racers positions in the current run.", 0, 0) {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				timer.swap();
				return true;
			}
		});
		
		addCommand(new Command("dnf", "", "The next competetor to finish will not finish.", 0, 0) {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				timer.doNotFin();
				return true;
			}
		});
		
		addCommand(new Command("trigger", "<channel>", "Triggers a certain channel.", 1, 1, "trig") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				//Implement when we have API for chronotimer written.
				stream.println("Trigger is not implemented yet.");
				return true;
			}
		});
		
		addCommand(new Command("start", "", "Triggers the first channel.") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				return getSingleton().executeCommand("trigger 1");
			}
		});
		
		addCommand(new Command("finish", "", "Triggers the second channel.") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				return getSingleton().executeCommand("trigger 2");
			}
		});
		
		addCommand(new Command("connect", "<sensor> <channel>", "Connects a specific sensor to a specific channel.", 2, 2, "conn") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				//Implement when we have API for chronotimer written.
				stream.println("Connect is not implemented yet.");
				return true;
			}
		});
		
		addCommand(new Command("disconnect", "<channel>", "Disconnects a sensor from channel <channel>.", 1, 1, "disc") {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				//Implement when we have API for chronotimer written.
				stream.println("Disconnect is not implemented yet.");
				return true;
			}
		});
		
		addCommand(new Command("event", "<type>", "Sets the current run with the given event type.", 1, 1) {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				//Implement when we have API for chronotimer written.
				stream.println("Event is not implemented yet.");
				return true;
			}
		});
		
		addCommand(new Command("newrun", "", "Creates a new run.(Must end a run first)", 0, 0) {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				//Implement when we have API for chronotimer written.
				stream.println("Newrun is not implemented yet.");
				return true;
			}
		});
		
		addCommand(new Command("endrun", "", "Done with the current run.", 0, 0) {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				//Implement when we have API for chronotimer written.
				stream.println("Endrun is not implemented yet.");
				return true;
			}
		});
		
		addCommand(new Command("print", "<run>", "Prints the given run.", 1, 1) {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				try {
					int number = Integer.parseInt(args[0]);
				} catch(NumberFormatException e) {
					return false;
				}
				//Implement when we have API for chronotimer written.
				stream.println("print is not implemented yet.");
				return true;
			}
		});
		
		addCommand(new Command("export", "<run>", "Exports the given run.", 1, 1) {
			public boolean execute(PrintStream stream, ChronoTimer timer, String[] args) {
				try {
					int number = Integer.parseInt(args[0]);
				} catch(NumberFormatException e) {
					return false;
				}
				//Implement in later sprints.
				stream.println("Export is not implemented yet.");
				return true;
			}
		});
		
	}
	
}
