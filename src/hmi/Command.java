package hmi;

import java.util.regex.Pattern;

public enum Command {
	GO, HELP, LOOK, ATTACK, TAKE, USE, QUIT, BACK;
        
    public static Pattern getPattern() {
        return Pattern.compile(Command.getRegex());
    }
    
    public static String getRegex() {
    	return "GO|HELP|LOOK|ATTACK|TAKE|USE|QUIT|BACK";
    }
        
	@Override
	public String toString() {
		switch(this) {
		case GO :
			return "GO";
		case ATTACK:
			return "ATTACK";
		case HELP:
			return "HELP";
		case LOOK:
			return "LOOK";
		case QUIT:
			return "QUIT";
		case TAKE:
			return "TAKE";
		case USE:
			return "USE";
		case BACK:
			return "BACK";
		default:
			return null;
		}
	}
        
    public static Command toCommand(String cmd) {
        switch(cmd) {
			case "GO" :
				return GO;
			case "ATTACK":
				return ATTACK;
			case "HELP":
				return HELP;
			case "LOOK":
				return LOOK;
			case "QUIT":
				return QUIT;
			case "TAKE":
				return TAKE;
			case "USE":
				return USE;
			case "BACK":
				return BACK;
			default:
				return null;
		}
    }


	public static void help() {
		HMI.message(
			"To show this page, use the action [HELP] in the menu.\n"
			+ "\t[GO] : allows you to continue to the next room\n"
			+ "\t[ATTACK] : allows you to attack a monster.\n"
    		+ "\t[LOOK] : allows you to look at something to obtain extra informations.\n"
    		+ "\t[QUIT] : allows you to quit (and save) the game.\n"
    		+ "\t[TAKE] : allows you to pick up an item.\n"
        	+ "\t[USE] : allows you to choose an item to use.\n"
    		+ "\t[BACK] : cancel current action\n"
    		);
	}
}
