package core.place;

import core.character.Monster;

public interface RandomlyPlacedMobs {

        /**
         * @return a monster array
         */
	public abstract Monster[] getMonsterList();
	
        /**
         * @return a random monster based on the monster array
         */
	public default Monster getRandomMonster(){
		Monster[] list = this.getMonsterList();
		return (Monster) list[Math.floorMod((int) Math.random() * list.length, list.length)];
	}
}
