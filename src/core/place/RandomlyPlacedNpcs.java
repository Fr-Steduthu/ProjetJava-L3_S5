package core.place;

import core.character.NPC;

public interface RandomlyPlacedNpcs {

        /**
         * @return a NPC array
         */
	public abstract NPC[] getNpcList();
	
        /**
         * @return a random NPC based on the NPC array
         */
	public default NPC getRandomNpc(){
		NPC[] list = this.getNpcList();
		return (NPC) list[Math.floorMod((int) Math.random() * list.length, list.length)];
	}
}
