package hmi;

import java.util.Scanner;

public final class HMI {
	public static Scanner input = new Scanner(System.in);
	
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

	public static void message(String message) {
            System.out.println(message);
	}
	
	public static void error(String message) {
            System.err.println(message);
	}
}