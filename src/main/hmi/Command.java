package hmi;

public enum Command {
	GO, HELP, LOOK, ATTACK, TAKE, USE, QUIT, BACK, /*TALK, */INTERACT, ME;
    
    public static String getRegex() {
    	String[] commands = {"go", "attack", "help", "look", "quit", "take", "use", "back", "interact", "me"};
    	return Regex.regex(commands);
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
		case INTERACT:
			return "INTERACT";
                case ME:
			return "ME";
		default:
			return null;
		}
	}
        
    public static Command toCommand(String cmd) {
        switch(cmd.toUpperCase()) {
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
			case "INTERACT":
				return INTERACT;
                        case "ME":
                                return ME;
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
    		+ "\t[INTERACT] allows you to talk to or inspect an entity in the room\n"
                + "\t[ME] allows you to see your stats and inventory\n"
    		);
	}
}
