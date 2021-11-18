package hmi;

import java.util.regex.Pattern;

public enum Command {
	GO, HELP, LOOK, ATTACK, TAKE, USE, QUIT;
        
        public static Pattern getPattern() {
            Pattern p = Pattern.compile("GO | HELP | LOOK | ATTACK | TAKE | USE | QUIT");
            return p;
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
		default:
			return null;
		}
        }
	
	/*
	Command(Command use, int item1) {
		if(use == Command.USE);
	}*/

	public static void help() {
		// TODO Auto-generated method stub
		HMI.message("To show this page, use the action [HELP] in the menu.");
                HMI.message(" /t* [GO] : allows");
                HMI.message(" /t* [ATTACK] : allows you to attack a monster.");
                HMI.message(" /t* [LOOK] : allows you to look at something to obtain extra informations.");
                HMI.message(" /t* [QUIT] : allows you to quit (and save) the game.");
                HMI.message(" /t* [TAKE] : allows you to pick up an item.");
                HMI.message(" /t* [USE] : allows you to choose an item to use.");
	}
}
