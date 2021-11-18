package custom.characters.monsters;

import core.character.Monster;

public class Skeleton extends Monster {

	private static final long serialVersionUID = 888878383441034641L;

	public Skeleton() {
        super("Skeleton", 8.0f, 2.0f, 0, 1);
        this.isLootable = true;
        // TODO : this.inventory.add()
    }
}
