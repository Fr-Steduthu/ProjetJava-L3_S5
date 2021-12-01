package core.place;

import core.character.Monster;

public interface RandomlyPlacedMobs {

	public abstract Monster[] getMonsterList();
	
	public default Monster getRandomMonster(){
		Monster[] list = this.getMonsterList();
		return (Monster) list[Math.floorMod((int) Math.random() * list.length, list.length)];
	}
}
