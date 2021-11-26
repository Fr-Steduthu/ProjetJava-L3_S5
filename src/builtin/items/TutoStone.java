package builtin.items;

import core.items.Item;
import core.places.Place;
import builtin.characters.npcs.TutoMan;
import core.Inventory;
import core.character.Character;

public class TutoStone extends Item {
	private static final long serialVersionUID = 1085466083168420693L;

	public TutoStone() {
		super("Tutorial stone");
	}

	@Override
	public String look() {
		return "A special stone. When used, it will summon the Tuto-MAN in your current room and inflict 5 points of damage to everyone.";
	}

	@Override
	protected void onUse(Object target) {
                Character npcs[];
		if(target instanceof Place) {
			((Place) target).addNpc(new TutoMan());
                        npcs = ((Place) target).getNpcs();
			for(Character c : npcs) {
				c.kill();
			}
		}else if(target instanceof Character) {
			((Character) target).getLocation().addNpc(new TutoMan());
                        npcs = ((Character) target).getLocation().getNpcs();
		} else {
                        npcs = new Character[]{};
                }
                
                for(Character c : npcs) {
                    c.hurt(5.0);
		}
                
		((Inventory)this.getLocation()).getOwner().hurt(5.0);
	}

}
