package core.character;

import core.game.Quest;

public class Monster extends Character{

	private static final long serialVersionUID = -5488094578355543197L;

        /**
         * The monster's default constructor
         * 
         * @param name
         * The monster's name
         * 
         * @param maxHP
         * The monster's maximum health
         * 
         * @param attackDamage
         * The monster's attack damage
         * 
         * @param inventoryCapacity
         * The monster's inventory capacity
         * 
         * @param equipment_capacity
         * The monster's equipment capacity
         */
	public Monster(String name, double maxHP, double attackDamage, int inventoryCapacity, int equipment_capacity) {
            super(name, maxHP, 0.0, inventoryCapacity, equipment_capacity);
            this.attackDamage = attackDamage;
            this.isLootable = true;
        }
	
        /**
         * The monster's reduced constructor, used for monsters without equipement capacity
         * 
         * @param name
         * The monster's name
         * 
         * @param maxHP
         * The monster's maximum health
         * 
         * @param attackDamage
         * The monster's attack damage
         * 
         * @param inventoryCapacity
         * The monster's inventory capacity
         */
	public Monster(String name, double maxHP, double attackDamage, int inventoryCapacity) {
            super(name, maxHP, 0.0, inventoryCapacity, 0);
            this.attackDamage = attackDamage;
            this.isLootable = true;
        }

        /**
         * Monster's interaction
         * 
         * See character's interact for more info
         */
	@Override
	public void interact(Quest q) {
		this.inspect();
	}
}
