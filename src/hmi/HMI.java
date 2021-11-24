package hmi;

import java.io.PrintStream;
import java.util.Scanner;

public final class HMI {
	public static Scanner input;
	public static PrintStream output;
	public static PrintStream errorOutput;
	
	static {
		HMI.input = new Scanner(System.in);
		HMI.output = System.out;
		HMI.errorOutput = System.err;
	}
	
	public static String read(String message, String regex) {
		
		HMI.message(message + "\n["+regex+"]");
		
		if(input.hasNext(regex)) {
			return HMI.input.next(regex);
			
		}else {
			HMI.input.next();
			return HMI.read(message, regex);
		}
		
	}
	
	public static String read(String message) {
		String cmd = null;
		
		if(HMI.input.hasNext(Command.getRegex())) {
			cmd = HMI.input.next(Command.getRegex());
			
		}else if(HMI.input.hasNextInt()) {
			cmd = Integer.toString(HMI.input.nextInt());
			
		}else{
			HMI.input.next();
			cmd = HMI.read(message);
		}
		return cmd;
	}
	
	public static boolean confirm(String message) {
		return HMI.read(message, "y|n").equals("y");
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
}