package hmi;

import java.util.Scanner;
import java.util.regex.Pattern;

public final class HMI {

	public static String read() {
		String cmd = null;
        Scanner input = new Scanner(System.in);
        //while(!input.hasNext(Command.getRegex()+"|\\d"+"|\\d\\d"));
        cmd = input.next(Command.getRegex()+"|\\d"+"|\\d\\d"); //On considère des eniters de valeur 99 max
        input.close();
        return cmd;
	}
	
	/*
	public static String[] read() {
		String cmd = null;
		String arg1 = null;
		String arg2 = null;
        Scanner input = new Scanner(System.in);
        
        try {
        	HMI.message("Please enter a command (<CMD> [arg1] [arg2]). If you need help, enter HELP :");
            cmd = input.next(Command.getPattern());
            Command command = Command.toCommand(cmd);
            
            if(command != Command.HELP && command != Command.QUIT && command != Command.GO) {
	            arg1 = input.next();
	            if(command == Command.USE) {
	            	if(input.hasNext()) {
	            		arg2 = input.next();
	            		input.close();
	            		String out[] = {cmd, arg1, arg2};
	            		return out;
	            	}
	            }
	            
	            input.close();
	            String[] out = {cmd, arg1};
	            return out;
	            
            }else{
            	input.close();
            	String out[] = {cmd};
            	return out;
            }
            
            
        } catch (Exception e) {
            if(e instanceof NoSuchElementException) {
                HMI.error("This command does not exist, type help to see visible commands; please try again.");
                input.close();
                return HMI.read();
            } else {
                HMI.error("An error has occured; goobye !.");
                assert(false);
                input.close();
                return null;
            }
        }
	}/**/
	
    public static boolean confirm(String message) {
        HMI.message(message + "[y/n]");

        Scanner sc = new Scanner(System.in);
        String answer = null;
        //while(!sc.hasNext()) {}
    	answer = sc.next(Pattern.compile("y|n"));
        sc.close();
        
        return answer.equals("y");
    }

	public static void message(String message) {
		System.out.println(message);
	}
	
	public static void error(String message) {
		System.err.println(message);
	}
}