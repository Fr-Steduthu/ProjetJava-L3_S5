package core.places;

import core.character.NPC;

public interface RandomlyPlacedNpcs {

	public abstract NPC[] getNpcList();
	
	public default NPC getRandomNpc(){
		NPC[] list = this.getNpcList();
		return (NPC) list[Math.floorMod((int) Math.random() * list.length, list.length)];
	}
}