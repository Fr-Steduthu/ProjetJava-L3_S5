package core;

import core.items.Equipment;
import core.items.Item;
import core.character.Player;
import custom.items.SaND;
import custom.items.WoodGloves;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InventoryIT {

    private final Integer invSize = 5;
    private final Integer eInvSize = 1;
    private Inventory inv;
    private Inventory eInv;
    private Player character;
    
    private Item item;
    private Equipment equipment;
    
    @Before
    public void setup() {
        character = new Player("I am a test entity for the equipments", 42.0, 42.0, eInvSize, eInvSize);
        inv = new Inventory(null, invSize, 0);
        eInv = new Inventory(character, eInvSize, eInvSize);
        item = new SaND();
        equipment = new WoodGloves();
    }
    
    // getOwners
    @Test
    public void sameOwners() {
        assertEquals(character, eInv.getOwner());
    }
    
    // getItems
    @Test
    public void emptyInv() {
        Item[] items = inv.getItems();
        Item[] empty = new Item[]{};
        assertArrayEquals(empty, items);
    }
    
    // getEquipments
    @Test
    public void emptyEqpmt() {
        Equipment[] equipped = inv.getEquipment();
        Equipment[] empty = new Equipment[]{};
        assertArrayEquals(empty, equipped);
    }

    // isFull (empty)
    @Test
    public void notFullInv() {
        assertFalse(inv.isFull());
    }
    
    // isFull (full)
    @Test
    public void fullInv() {
        for (int i = 0; i < invSize; i++) {
            inv.addItem(item);
        }
        assertTrue(inv.isFull());
    }
    
    // isFullyGeared (empty)
    @Test
    public void notFullyGeared() {
        assertFalse(eInv.isFullyGeared());
    }
    
    // isFullyGeared (full)
    @Test
    public void fullyGeared() {
        for (int i = 0; i < eInvSize; i++) {
            eInv.addItem(equipment);
            eInv.equip(equipment);
        }
        assertTrue(eInv.isFullyGeared());
    }
    
    // contains (not here)
    @Test
    public void notInInv() {
        assertFalse(eInv.contains(item));
    }
    
    // contains (here)
    @Test
    public void inInv() {
        // Seems like the test starts from an empty inventory.. THIS IS FINE '-'
        inv.addItem(item);
        assertTrue(inv.contains(item));
    }
    
    // isEquiped (not here)
    @Test
    public void notEquipped() {
        assertFalse(eInv.isEquiped(equipment));
    }
    
    // isEquiped (here)
    @Test
    public void equipped() {
        eInv.addItem(equipment);
        eInv.equip(equipment);
        assertTrue(eInv.isEquiped(equipment));
    }
    
    // findItem (not here)
    @Test
    public void notFound() {
        inv.addItem(item);
        assertEquals(-1, inv.findItem(equipment));
    }
    
    // findItem (here)
    @Test
    public void found() {
        inv.addItem(item);
        assertEquals(0, inv.findItem(item));
    }
    
    
    
    // FindEquipment (false)
    
    // Add item (false puis true)
    
    // Remove Item (false puis true)
    
    // FindEquipment (true)
    
    // Unequip
}
