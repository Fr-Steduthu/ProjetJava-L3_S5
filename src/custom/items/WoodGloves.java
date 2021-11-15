package custom.items;

import core.items.Equipment;


public class WoodGloves extends Equipment {

    private static final long serialVersionUID = 1L;

    public WoodGloves() {
        super("Wood Gloves");
    }

    @Override
    public String look() {
        return "Some wood gloves. Using them gives you 1.5 more damage points.";
    }

    @Override
    protected void onEquip(Object target) {
        // TODO
    }

    @Override
    protected void onDeEquip() {
        // TODO
    }

    
    
}
