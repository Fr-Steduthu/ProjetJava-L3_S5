package builtin.places;

import builtin.characters.monsters.Skeleton;
import builtin.characters.monsters.Slime;
import builtin.characters.monsters.Zombie;
import core.places.Place;

public class MonsterRoom extends Place {

	private static final long serialVersionUID = 7175721422325800766L;

	public MonsterRoom() {
		super("Hell's kitchen");
		this.addNpc(new Zombie());
		this.addNpc(new Slime());
		this.addNpc(new Skeleton());
	}
}
