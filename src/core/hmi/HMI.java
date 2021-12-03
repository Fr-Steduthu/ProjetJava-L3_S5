package core.hmi;

import java.io.PrintStream;
import java.util.Scanner;

public final class HMI {
        /**
         * The input scanner used during the game
         */
	private static Scanner input;
        
        /**
         * The output stream used for the text
         */
	private static PrintStream output;
        
        /**
         * The output stream used for the errors
         */
	private static PrintStream errorOutput;
        
        /**
         * The output stream used for debugging purposes
         */
	private static PrintStream debug;
	
        /**
         * The delimiter for the inputs
         */
	private static String inputDelimiter = "[\\t\\n\\x0B\\f\\r]"; //\x0b = "BELL" character
	
	static {
		HMI.input = new Scanner(System.in).useDelimiter(HMI.inputDelimiter);
		
		HMI.output = System.out;
		HMI.errorOutput = System.err;
		HMI.debug = System.err;
	}
	
        /**
         * Sets up the current scanner to use
         * 
         * @param input 
         * The scanner to use
         */
	public void setInput(Scanner input){
		HMI.input.close();
		HMI.input = input;
	}
	
        /**
         * Reads some info using a message and a regex
         * 
         * @param message
         * The message to read
         * 
         * @param regex
         * The regex to use for reading
         * 
         * @return a string if the function found a concording one from the input
         */
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
        
        /**
         * Reads a number
         * 
         * @param message
         * The message to read
         * 
         * @param min
         * The minimum number awaited
         * 
         * @param max
         * The maximum number awaited
         * 
         * @return an integer if the function found one concording to the input
         */
	public static int readNumber(String message, int min, int max) {
			HMI.message(message);
			
			if(input.hasNextInt()) {
				int res = input.nextInt();
                                if(res < min || max < res) {
					HMI.message("The given number is not between " + min + " and " + max + ", please try again.");
                                        return HMI.readNumber(message, min, max);
				}
				return res;
				
			}else {
				String err = HMI.input.next();
				if(!err.equals("")) {
					HMI.message("An error has occured, please, try again.");
				}
				return HMI.readNumber(message, min, max);
			}
		
	}
	
        /**
         * Reads a command
         * 
         * @param message
         * The command to read
         * 
         * @return a string containing a command 
         */
	public static String readCommand(String message) {
		//HMI.input.useDelimiter("\\s");
		String retVal =  HMI.read(message, Command.getRegex());
		//HMI.input.useDelimiter(HMI.inputDelimiter);
		return retVal;
	}
	
        /**
         * Asks the user to confirm based on a message
         * 
         * @param message
         * The message to send to the user
         * 
         * @return if the user accepted or not
         */
	public static boolean confirm(String message) {
		return HMI.read(message+"[y|n]", "y|n").equals("y");
	}

        /**
         * Clears the output
         */
	public static void clear() {
		try{  
			HMI.output.print("\033[H\033[2J");  
			HMI.output.flush();  
		}catch(Exception e) {
			HMI.errorOutput.println(e.getStackTrace().toString());
		}
	}
	
        /**
         * Prints a message
         * 
         * @param message 
         * The message to print
         */
	public static void message(String message) {
            HMI.output.println(message);
	}
	
        /**
         * Prints an error
         * 
         * @param message 
         * The error to print
         */
	public static void error(String message) {
            HMI.errorOutput.println(message);
	}
	
        /**
         * Prints a debug message
         * 
         * @param message 
         * The debug message to print
         */
	public static void debug(String message) {
		HMI.debug.println("\t[DEBUG] " + message);
	}
	
        /**
         * Closes the input and outputs
         */
	public static void close() {
		HMI.input.close();
		//HMI.output.close(); //On laisse a l'utilisateur le soin de fermer le jeu (System.out en ce moment)
		HMI.errorOutput.close();
		HMI.debug.close();
	}
}