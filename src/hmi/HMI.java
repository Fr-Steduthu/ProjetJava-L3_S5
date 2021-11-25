package hmi;

import java.io.PrintStream;
import java.util.Scanner;

public final class HMI {
	private static Scanner input;
	private static PrintStream output;
	private static PrintStream errorOutput;
	private static PrintStream debug;
	
	private static String inputDelimiter = "[\\t\\n\\x0B\\f\\r]"; //\x0b = "BELL" character
	
	static {
		HMI.input = new Scanner(System.in).useDelimiter(HMI.inputDelimiter);
		
		HMI.output = System.out;
		HMI.errorOutput = System.err;
		HMI.debug = System.err;
	}
	
	public static String read(String message, String regex) {
			//HMI.error(regex);
			HMI.message(message);
			
			if(input.hasNext(regex)) {
				String res = input.next(regex);
				if(res.equals("\n")) {
					return HMI.read(message, regex);
				}
				return res;
				
			}else {
				String err = HMI.input.next();
				if(!err.equals("")) {
					HMI.message("An error has occured, please, try again.");
				}
				return HMI.read(message, regex);
			}
		
	}
	
	public static String readCommand(String message) {
		return HMI.read(message, Command.getRegex());
	}
	
	public static boolean confirm(String message) {
		return HMI.read(message+"[y|n]", "y|n").equals("y");
	}

	public static void clear() {
		//TODO ? Esce-ce seulement possible ? https://stackoverflow.com/questions/2979383/how-to-clear-the-console
	}
	
	public static void message(String message) {
            HMI.output.println(message);
	}
	
	public static void error(String message) {
            HMI.errorOutput.println(message);
	}
	
	public static void debug(String message) {
		HMI.debug.println("\t[DEBUG] " + message);
	}
	
	public static void close() {
		HMI.input.close();
		//HMI.output.close(); //On laisse a l'utilisateur le soin de fermer le jeu (System.out en ce moment)
		HMI.errorOutput.close();
		HMI.debug.close();
	}
}