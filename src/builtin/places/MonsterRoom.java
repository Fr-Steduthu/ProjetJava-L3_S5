package builtin.places;

import core.places.Place;
import custom.characters.monsters.Skeleton;
import custom.characters.monsters.Slime;
import custom.characters.monsters.Zombie;

public class MonsterRoom extends Place {

	private static final long serialVersionUID = 7175721422325800766L;

	public MonsterRoom() {
		super("Hell's kitchen");
		this.addNpc(new Zombie());
		this.addNpc(new Slime());
		this.addNpc(new Skeleton());
	}
}
