package tests.core;

import org.junit.Before;
import org.junit.Test;

import core.builtin.items.SaND;
import core.builtin.items.WoodGloves;
import core.character.Player;
import core.game.Inventory;
import core.item.Equipment;
import core.item.Item;

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
    
    // getItems (not empty)
    @Test
    public void notEmptyInv() {
        inv.addItem(item);
        Item[] items = inv.getItems();
        Item[] someItems = new Item[1];
        someItems[0] = item;
        assertArrayEquals(someItems, items);
    }
    
    // getEquipments (empty)
    @Test
    public void emptyEquipemt() {
        Equipment[] equipped = inv.getEquipment();
        Equipment[] empty = new Equipment[]{};
        assertArrayEquals(empty, equipped);
    }
    
    // getEquipments (not empty)
    @Test
    public void notEmptyEqpmt() {
        eInv.addItem(equipment);
        eInv.equip(equipment);
        Equipment[] equipped = eInv.getEquipment();
        Equipment[] someEquipments = new Equipment[1];
        someEquipments[0] = equipment;
        assertArrayEquals(someEquipments, equipped);
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
            inv.addItem(new SaND());
        }
        assertTrue(inv.isFull());
    }
    
    @Test
    public void overfillItems() {
        for (int i = 0; i < invSize; i++) {
            item = new SaND();
            inv.addItem(item);
            assertEquals(i, inv.findItem(item));
        }
        for (int i = 0; i < 42; i++) {
            item = new SaND();
            inv.addItem(item);
            assertEquals(-1, inv.findItem(item));
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
    
    @Test
    public void overfillEquipments() {
        for (int i = 0; i < eInvSize; i++) {
            eInv.addItem(equipment);
            assertTrue(eInv.equip(equipment));
            assertEquals(i, eInv.findEquipment(equipment));
        }
        for (int i = 0; i < 42; i++) {
            equipment = new WoodGloves();
            eInv.addItem(equipment);
            assertFalse(eInv.equip(equipment));
            assertEquals(-1, eInv.findEquipment(equipment));
        }
        assertTrue(eInv.isFullyGeared());
    }
    
    // contains (not here)
    @Test
    public void notInInv() {
        assertFalse(eInv.contains(item));
    }
    
    // An overfill test to check for a missing item
    @Test
    public void overfillMissing() {
        for (int i = 0; i < invSize; i++) {
            assertTrue(inv.addItem(new SaND()));
        }
        
        for (int i = 0; i < 42; i++) {
            Item missing = new SaND();
            inv.addItem(missing);
            assertFalse(inv.contains(missing));
        }
        
    }
    
    // contains (here)
    @Test
    public void inInv() {
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
    
    // Try to overfill the equipped items, search for not equipped item
    @Test
    public void overfillEquipped() {
        eInv.addItem(equipment);
        eInv.equip(equipment);
        Equipment missing = new WoodGloves();
        for (int i = 0; i < 42; i++) {
            eInv.addItem(missing);
            assertFalse(eInv.equip(missing));
        }
        assertTrue(eInv.isEquiped(equipment));
        assertFalse(eInv.isEquiped(missing));
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
    
    // Overfill and then look for an item
    @Test
    public void overfillMissingLook() {
        for (int i = 0; i < 42; i++) {
            inv.addItem(new SaND());
        }
        
        Item missing = new SaND();
        
        inv.addItem(missing);
        assertEquals(-1, inv.findItem(missing));
    }
    
    // FindEquipment (false)
    @Test
    public void notInEquiped() {
        assertEquals(-1, eInv.findEquipment(equipment));
    }
    
    // FindEquipment (true)
    @Test
    public void hasInEquiped() {
        eInv.addItem(equipment);
        eInv.equip(equipment);
        assertEquals(0, eInv.findEquipment(equipment));
    }
    
    // Add item (true)
    @Test
    public void canBeAdded() {
        assertTrue(inv.addItem(item));
    }
    
    // Add item (false)
    @Test
    public void cannotBeAdded() {
        for (int i = 0; i < invSize; i++) {
            inv.addItem(new SaND());
        }
        assertFalse(inv.addItem(item));
    }
    
    // Remove Item (false)
    @Test
    public void cannotBeRemoved() {
        assertFalse(inv.removeItem(item));
    }
    
    // Remove Item (true)
    @Test
    public void canBeRemoved() {
        inv.addItem(item);
        assertTrue(inv.removeItem(item));
    }
    
    // Equip (false)
    @Test
    public void cannotBeEquipped() {
        assertFalse(eInv.equip(equipment));
    }
    
    // Equip (true)
    @Test
    public void canBeEquipped() {
        eInv.addItem(equipment);
        assertTrue(eInv.equip(equipment));
    }
    
    // Unequip (false)
    @Test
    public void cannotBeUnequipped() {
        assertFalse(eInv.unequip(equipment));
    }
    
    // Unequip (true)
    @Test
    public void canBeUnequipped() {
        eInv.addItem(equipment);
        eInv.equip(equipment);
        assertTrue(eInv.unequip(equipment));
    }
}
