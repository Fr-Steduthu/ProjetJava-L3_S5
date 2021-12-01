package core.place;

import core.character.Character;

public interface RandomlyPlacedCharacter {

        /**
         * @return a character array
         */
	public abstract Character[] getCharacterList();
	
        /**
         * @return a random npc based on the character array
         */
	public default Character getRandomNpc(){
		Character[] list = this.getCharacterList();
		return (Character) list[Math.floorMod((int) Math.random() * list.length, list.length)];
	}
}
