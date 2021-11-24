package builtin.places.entrances;

import builtin.characters.npcs.TutoMan;
import core.places.Place;

public class TutorialRoom extends Place {

	private static final long serialVersionUID = -4595227551149509157L;

	public TutorialRoom() {
		super("Tutorial room");
		this.addNpc(new TutoMan());
	}

}
