package core.builtin.places;

import core.builtin.characters.npcs.TutoMan;
import core.place.Place;

public class TutorialRoom extends Place {

	private static final long serialVersionUID = -4595227551149509157L;

	public TutorialRoom() {
		super("Tutorial room");
		this.addNpc(new TutoMan());
	}

}
