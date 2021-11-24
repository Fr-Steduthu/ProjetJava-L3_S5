package builtin.items;

import core.Inventory;
import core.items.Item;
import core.character.Character;

public class Skygeiir extends Item {

	private static final long serialVersionUID = 2062637203045579827L;

	public Skygeiir() {
		super("Skygeiir");
	}

	@Override
	public String look() {
		return "Skygeiir the sky lance";
	}

	@Override
	protected void onUse(Object target) {
		Character[] npcs = ((Inventory) this.getLocation()).getOwner().getLocation().getNpcs();
		for(Character n : npcs){
			n.kill();
		}
	}

}
