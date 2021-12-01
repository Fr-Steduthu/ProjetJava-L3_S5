package core.builtin.places;

import core.builtin.characters.monsters.Sentinel;
import core.place.Place;

public class SentinelRoom extends Place {

	private static final long serialVersionUID = 8761765906267402044L;

	public SentinelRoom() {
		super("Sentinel's vault");
		this.addNpc(new Sentinel());
	}

}
