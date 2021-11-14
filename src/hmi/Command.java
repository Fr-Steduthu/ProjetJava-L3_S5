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
		
	}
}
