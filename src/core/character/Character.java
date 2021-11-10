package core.character;

//import javax.annotation.ParametersAreNonnullByDefault;
public abstract class Character {
	private final String NAME;
	private float maxHP;
	private float maxAbilityRessource;
	
	private float hp;
	private float ar;
	
	private Inventory inventory;
	
	//@ParametersAreNonnullByDefault
	public Character(String name, float maxHP, float maxAbilityRessource, int inventoryCapacity) {
		this.NAME = name;
		this.maxHP = maxHP;
		this.maxAbilityRessource = maxAbilityRessource;
		
		this.hp = this.maxHP;
		this.ar = this.maxAbilityRessource;
		
		this.inventory = new Inventory(inventoryCapacity);
	}
}
