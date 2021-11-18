package core;

import core.items.Equipment;
import core.items.Item;
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
    
    private Item item;
    private Equipment equipment;
    
    @Before
    public void setup() {
        inv = new Inventory(null, invSize, 0);
        eInv = new Inventory(null, eInvSize, eInvSize);
        item = new SaND();
        equipment = new WoodGloves();
    }
    
    @Test
    public void emptyInv() {
        Item[] items = inv.getItems();
        Item[] empty = new Item[]{};
        assertArrayEquals(empty, items);
    }
    
    @Test
    public void emptyEqpmt() {
        Equipment[] equipped = inv.getEquipment();
        Equipment[] empty = new Equipment[]{};
        assertArrayEquals(empty, equipped);
    }

    @Test
    public void notFullInv() {
        assertFalse(inv.isFull());
    }
    
    @Test
    public void fullInv() {
        for (int i = 0; i < invSize; i++) {
            inv.addItem(item);
        }
        assertTrue(inv.isFull());
    }
    
    @Test
    public void isOverfilledInv() {
        for (int i = 0; i < 40; i++) {
            inv.addItem(item);
        }
        assertTrue(inv.isFull());
    }
    
    @Test
    public void notFullyGeared() {
        assertFalse(eInv.isFullyGeared());
    }
    
    @Test
    public void notInInv() {
        assertFalse(eInv.contains(item));
    }
    
    @Test
    public void inInv() {
        assertTrue(inv.contains(item));
    }
    
    @Test
    public void notEquipped() {
        assertFalse(eInv.isEquiped(equipment));
    }
    
    @Test
    public void found() {
        assertEquals(inv.findItem(item), 0);
    }
    
    @Test
    public void notFound() {
        assertEquals(inv.findItem(equipment), -1);
    }
    
    // FindEquipment (false)
    
    // Add item (false puis true)
    
    // Remove Item (false puis true)
    
    // This is later because we need to test first the add item (item must be in inv to be equipped
    @Test
    public void fullyGeared() {
        for (int i = 0; i < eInvSize; i++) {
            eInv.equip(equipment);
        }
        assertTrue(eInv.isFullyGeared());
    }
    
    // IsEquipped (vraiment équipé)
    
    // FindEquipment (true)
    
    // Unequip
}
