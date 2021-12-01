package core.builtin.places;

import core.builtin.characters.monsters.Skeleton;
import core.builtin.characters.monsters.Slime;
import core.builtin.characters.monsters.Zombie;
import core.place.Place;

public class MonsterRoom extends Place {

	private static final long serialVersionUID = 7175721422325800766L;

	public MonsterRoom() {
		super("Hell's kitchen");
		this.addNpc(new Zombie());
		this.addNpc(new Slime());
		this.addNpc(new Skeleton());
	}
}
