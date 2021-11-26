package builtin.characters.npcs;

import builtin.items.TutoStone;
import core.Quest;
import core.character.NPC;
import hmi.HMI;

public class TutoMan extends NPC {

    private static final long serialVersionUID = 1443591607874829245L;
    
    private final String[] tipsArray = new String[]{"Using HELP gives you all available commands", "ATTACKing a Character decreases it's health points", "You can TAKE items", "Nah. You'll be fine without my advices"};

    public TutoMan() {
        super("Tuto-MAN", "I don't know if you know but..", 1);
        this.isAbleToSpeak = true;
    }
    
    @Override
    public void interact(Quest context) {
        int tipSelected = (int) (Math.random() * (tipsArray.length - 0));
        this.speak();
        this.speak(tipsArray[tipSelected]);
        this.getLocation().addItem(new TutoStone());
        HMI.message("The Tuto-MAN dropped some kind of stone");
        this.getLocation().removeNpc(this);
    }  
}
