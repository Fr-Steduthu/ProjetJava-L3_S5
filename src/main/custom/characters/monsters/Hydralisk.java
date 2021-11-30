package main.custom.characters.monsters;

import main.core.character.Monster;
import main.core.character.State;

public class Hydralisk extends Monster {

	private static final long serialVersionUID = 6919651768245985734L;

	public Hydralisk() {
		super("Hydralisk", 20.0, 1.5, 0);
		this.currentState = State.IMMUNE_ALL;
	}

}
