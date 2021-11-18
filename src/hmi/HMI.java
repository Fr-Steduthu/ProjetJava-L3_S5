package hmi;

import java.util.NoSuchElementException;
import java.util.Scanner;

public final class HMI {
	
	public static Command read(String arg) {
                System.out.println("Please enter a command with this form CMD [arg]. If you need help, enter HELP :");
                String cmd;
                Scanner sc;
                sc = new Scanner(System.in);
                try {
                    cmd = sc.next(Command.getPattern());
                    arg = sc.next();
                } catch (NoSuchElementException | IllegalStateException e) {
                    cmd = null;
                    arg = null;
                    if (e instanceof NoSuchElementException) {
                        System.out.println("This command does not exit, please try again.");
                    } else {
                        System.out.println("You may have closed the reader, please try again.");
                    }
                }
                sc.close();
                return Command.toCommand(cmd);
	}

	public static void message(String message) {
		System.out.println(message);
	}
	
}