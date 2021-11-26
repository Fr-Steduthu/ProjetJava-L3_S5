package tests.core.character;

import core.Inventory;
import core.items.Item;
import core.places.Place;
import builtin.items.SaND;
import core.Quest;
import core.character.Character;
import core.character.State;

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
    private Character nonLootableCharacter;
    private Inventory inv;
    private Place room;
    
    @Before
    public void setup() {
        character = new core.character.Character(DEF_NAME, DEF_HP, DEF_AR, DEF_INV_CAP, DEF_EQ_CAP) {

			private static final long serialVersionUID = -1034399115046921467L;
			{
				this.isLootable = true;
			}
			
			@Override
            public void interact(Quest context) {
                System.out.println("This is not supposed to be tested.");
            }
        };
        
        nonLootableCharacter = new Character(DEF_NAME, DEF_HP, DEF_AR, DEF_INV_CAP, DEF_EQ_CAP) {
			private static final long serialVersionUID = 5390515375152353402L;

			@Override
            public void interact(Quest context) {
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
        assertEquals(State.ALIVE, character.getState());
        assertEquals(1.0, character.getDamage(), 0.0);
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
        assertEquals(armor, (int) character.getArmor(), 0.0);
        character.hurt(dmg);
        assertEquals(DEF_HP, character.getHP(), 0.0);
    }
    
    // setState
    @Test
    public void newState() {
        State state = State.DEAD;
        character.setState(state);
        assertEquals(state, character.getState());
    }
    
    // setAttackDamage
    @Test
    public void newDamage() {
        double dmg = 3.0;
        character.setDamage(dmg);
        assertEquals(dmg, character.getDamage(), 0.0);
    }
    
    // attack
    @Test
    public void attackHimself() {
        double dmg = 3.0;
        character.setDamage(dmg);
        character.attack(character);
        assertEquals(DEF_HP - dmg, character.getHP(), 0.0);
    }
    
    // kill
    @Test
    public void suicide() {
        character.kill();
        assertEquals(0.0, character.getHP(), 0.0);
    }
    
    // equip is tested in InventoryIT, they use the same methods
    
    // getLoot
    @Test
    public void testLoots() {
        Item item = new SaND();
        character.addItem(item); // If this fails, please make sure that Character's getClassInventory method is available. (It's a comment by default btw)
        inv.addItem(item);
        assertArrayEquals(inv.getItems(), character.getInventory());
    }
    
    // onDeath
    @Test
    public void dropLoots() {
        character.addItem(new SaND()); // If this fails, please make sure that Character's getClassInventory method is available. (It's a comment by default btw)
        character.setLocation(room);
        Item[] oldInvItems = character.getInventory();
        character.onDeath(null);
        assertArrayEquals(inv.getItems(), character.getInventory());
        assertArrayEquals(oldInvItems, room.getItems());
    }
    
    // onDeath, except the caracter isn't lootable
    @Test
    public void dropLootsNonLootable() {
        nonLootableCharacter.addItem(new SaND()); // If this fails, please make sure that Character's getClassInventory method is available. (It's a comment by default btw)
        nonLootableCharacter.setLocation(room);
        Item[] empty = new Item[]{};
        nonLootableCharacter.onDeath(null);
        assertArrayEquals(empty, room.getItems());
    }
    
    // give and take already tested in inventory, they use the same methods
}