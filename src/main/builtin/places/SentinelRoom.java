package main.builtin.places;

import main.builtin.characters.monsters.Sentinel;
import main.core.places.Place;

public class SentinelRoom extends Place {

	private static final long serialVersionUID = 8761765906267402044L;

	public SentinelRoom() {
		super("Sentinel's vault");
		this.addNpc(new Sentinel());
	}

}
