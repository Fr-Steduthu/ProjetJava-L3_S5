package hmi;

public enum Command {
	GO, HELP, LOOK, ATTACK, TAKE, USE, QUIT;
	
	@Override
	public String toString() {
		switch(this) {
		case GO :
			return "Go";
		case ATTACK:
			return "Attack";
		case HELP:
			return "Help";
		case LOOK:
			return "Look";
		case QUIT:
			return "Quit";
		case TAKE:
			return "Take";
		case USE:
			return "Use";
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
