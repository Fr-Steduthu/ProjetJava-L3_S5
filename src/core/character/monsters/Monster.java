package core.character.monsters;

import core.character.Character;
import hmi.HMI;

public class Monster extends Character{

	private static final long serialVersionUID = -5488094578355543197L;

	public Monster(String name, float maxHP, int inventoryCapacity, float attackDamage) {
        super(name, maxHP, 0.0f, inventoryCapacity);
        this.attackDammage = attackDamage;
    }

	@Override
	public void interact() {
		HMI.message(getNAME() + " : " + this.getHP() + "/" + ((int)(this.maxHP+0.5)) + "health points\n\t"+(int)this.armor+" armor"); 
	}
}
