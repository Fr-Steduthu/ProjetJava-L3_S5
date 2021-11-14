package core.character.monsters;

import core.character.State;

public class Hydralisk extends Monster {

	private static final long serialVersionUID = 6919651768245985734L;

	public Hydralisk() {
		super("Hydralisk", 20.0f, 0, 1.5f);
		this.currentState = State.IMMUNE;
	}

}
