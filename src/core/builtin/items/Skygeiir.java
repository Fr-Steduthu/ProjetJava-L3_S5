package core.builtin.items;

import core.character.Character;
import core.game.Inventory;
import core.item.Item;

public class Skygeiir extends Item {

	private static final long serialVersionUID = 2062637203045579827L;

	public Skygeiir() {
		super("Skygeiir");
	}

	@Override
	public String look() {
		return "Skygeiir the sky lance. A dev item, you shouldn't have this unless you are a dev. Kills every single npc and monster in the room.";
	}

	@Override
	protected boolean onUse(Object target) {
		if(target != null) {
			return false;
		}
		
		Character[] npcs = ((Inventory) this.getLocation()).getOwner().getLocation().getNpcs();
		for(Character n : npcs){
			n.kill();
		}
		
		return true;
	}

}
