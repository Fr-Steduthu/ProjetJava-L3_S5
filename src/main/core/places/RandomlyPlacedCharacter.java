package main.core.places;

import main.core.character.Character;

public interface RandomlyPlacedCharacter {

	public abstract Character[] getCharacterList();
	
	public default Character getRandomNpc(){
		Character[] list = this.getCharacterList();
		return (Character) list[Math.floorMod((int) Math.random() * list.length, list.length)];
	}
}
