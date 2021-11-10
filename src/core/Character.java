package core;

//import javax.annotation.ParametersAreNonnullByDefault;
public abstract class Character {
	private final String NAME;
	private float maxHP;
	private float maxAbilityRessource;
	
	private float hp;
	private float ar;
	
	//@ParametersAreNonnullByDefault
	public Character(String name, float maxHP, float maxAbilityRessource) {
		this.NAME = name;
		this.maxHP = maxHP;
		this.maxAbilityRessource = maxAbilityRessource;
		
		this.hp = this.maxHP;
		this.ar = this.maxAbilityRessource;
	}
}
