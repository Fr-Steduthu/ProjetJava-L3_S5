package hmi;

import java.util.Scanner;
import java.util.regex.Pattern;

public final class HMI {
	public static Scanner input = new Scanner(System.in);

	public static String read() {
            String cmd = null;
            if(HMI.input.hasNext(Command.getRegex()+"|\\d"+"|\\d\\d")) {
                    cmd = HMI.input.next(Command.getRegex()+"|\\d"+"|\\d\\d"); //On considere des entiers de valeur 99 max
            }else {
                    HMI.input.next();
                    cmd = HMI.read();
            }
            return cmd;
	}
	
        public static boolean confirm(String message) {
            HMI.message(message + "[y/n]");
            if(input.hasNext(Pattern.compile("y|n"))) {
                return HMI.input.next(Pattern.compile("y|n")).equals("y");
            }else {
                HMI.input.next();
                return HMI.confirm(message);
            }
        }

	public static void message(String message) {
            System.out.println(message);
	}
	
	public static void error(String message) {
            System.err.println(message);
	}
}