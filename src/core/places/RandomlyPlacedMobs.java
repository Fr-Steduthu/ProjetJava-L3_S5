package core.places;

import core.character.Monster;

public interface RandomlyPlacedMobs {

	public abstract Monster[] getMonsterList();
	
	public default Monster getRandomMonster(){
		return (Monster) this.getMonsterList()[Math.floorMod((int) Math.random() * this.getMonsterList().length, this.getMonsterList().length)];
	}
}
