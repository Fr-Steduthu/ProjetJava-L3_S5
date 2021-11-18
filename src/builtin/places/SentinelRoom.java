package builtin.places;

import core.places.Place;
import custom.characters.monsters.Sentinel;

public class SentinelRoom extends Place {

	private static final long serialVersionUID = 8761765906267402044L;

	public SentinelRoom() {
		super("Sentinel's vault");
		this.addNpc(new Sentinel());
	}

}
