package core.character;

import core.Inventory;
import core.places.Place;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterIT {
    
    private final String DEF_NAME = "This is a test entity";
    private final double DEF_HP = 20.0;
    private final double DEF_AR = 20.0;
    private final Integer DEF_INV_CAP = 3;
    private final Integer DEF_EQ_CAP = 3;
    
    private Character character;
    private Inventory inv;
    private Place room;
    
    @Before
    public void setup() {
        character = new Character(DEF_NAME, DEF_HP, DEF_AR, DEF_INV_CAP, DEF_EQ_CAP) {
            @Override
            public void interact() {
                System.out.println("This is not supposed to be tested.");
            }
        };
        inv = new Inventory(null, 3, 1);
        room = new Place("Test room");
    }
    
    // constructor test
    @Test
    public void checkSetup() {
        assertEquals(DEF_NAME, character.getName());
        assertArrayEquals(inv.getItems(), character.getInventory());
        assertEquals(null, character.getLocation());
        assertEquals(DEF_HP, character.getHP(), 0.0);
        assertEquals(DEF_AR, character.getAR(), 0.0);
    }
    
    // setLocation
    @Test
    public void hasNewLocation() {
        character.setLocation(room);
        assertEquals(room, character.getLocation());
    }
    
    // hurt + heal
    @Test
    public void health() {
        double dmg = 3.0;
        character.hurt(dmg);
        assertEquals(DEF_HP - dmg, character.getHP(), 0.0);
        character.heal(dmg);
        assertEquals(DEF_HP, character.getHP(), 0.0);
    }

    // useAR + giveAR, regenAR doesn't regen AR by default
    @Test
    public void AR() {
        double used = 3.0;
        character.useAR(used);
        assertEquals(DEF_HP - used, character.getAR(), 0.0);
        character.giveAR(used);
        assertEquals(DEF_HP, character.getAR(), 0.0);
    }
    
    // armor, should block damage
    @Test
    public void armor() {
        double dmg = 3.0;
        double armor = 4.0;
        character.setArmor(armor);
        character.hurt(dmg);
        assertEquals(DEF_HP, character.getHP(), 0.0);
    }
}