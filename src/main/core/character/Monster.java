package core.character;

import core.Quest;

public class Monster extends Character{

	private static final long serialVersionUID = -5488094578355543197L;

	public Monster(String name, double maxHP, double attackDamage, int inventoryCapacity, int equipment_capacity) {
            super(name, maxHP, 0.0, inventoryCapacity, equipment_capacity);
            this.attackDamage = attackDamage;
            this.isLootable = true;
        }
	
	public Monster(String name, double maxHP, double attackDamage, int inventoryCapacity) {
            super(name, maxHP, 0.0, inventoryCapacity, 0);
            this.attackDamage = attackDamage;
            this.isLootable = true;
        }

	@Override
	public void interact(Quest q) {
		this.inspect();
	}
}