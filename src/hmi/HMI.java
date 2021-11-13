package hmi;

public class HMI {
	
	public static Command read() {
		return Command.ATTACK;
	}

	public static void message(String message) {
		System.out.println(message);
	}
	
}