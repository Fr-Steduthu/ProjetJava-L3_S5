package tests.core.items;

import main.core.Inventory;
import main.core.character.Character;
import main.core.character.Player;
import main.core.items.Item;
import main.core.places.Place;
import main.builtin.items.SaND;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ItemIT {

    private Item item;
    private Inventory inv;
    private Place room;
    private Player player;
    
    @Before
    public void setup() {
        item = new SaND();
        inv = new Inventory(null, 0, 0);
        room = new Place("Test room");
        player = new Player("A test entity") {

			private static final long serialVersionUID = 580166051356204320L;

			public Inventory getClassInventory() {
                return super.inventory;
        	}
        };
    }
    
    // getName
    @Test
    public void testDefName() {
        assertEquals("SaND", item.getName());
    }
    
    // setName
    @Test
    public void testCustomName() {
        item.setName("SaND!!");
        assertEquals("SaND!!", item.getName());
    }
    
    // setLocation (Inv)
    @Test
    public void inventoryLocation(){
        item.setLocation(inv);
        assertEquals(inv, item.getLocation());
    }
    
    // setLocation (Inv)
    @Test
    public void placeLocation(){
        item.setLocation(room);
        assertEquals(room, item.getLocation());
    }
    
    // getLocation
    @Test
    public void defLocation(){
        assertEquals(null, item.getLocation());
    }
    
    // take
    @Test
    public void takeItem(){
        player.setLocation(room);
        room.addItem(item);
        item.giveTo(player);
        assertEquals(player.getClassInventory(), item.getLocation()); // If this fails, please make sure that Character's getClassInventory method is available.
    }
    
    // use
    @Test
    public void useItem(){
        item = new Item("Healing item") {
			private static final long serialVersionUID = 5818009875775401024L;
			
			{
				this.isUsable = true;
				this.isCurrentlyUsable = true;
			}

			@Override
			public String look() {
				return null;
			}

			@Override
			protected boolean onUse(Object target) {
				((Character) target).heal(3.0);
				return false;
			}
        	
        };
        player.setLocation(room);
        room.addItem(item);
        item.giveTo(player);
        
        double beforeOperation = player.getHP();
        
        player.setArmor(0.0);
        player.hurt(3.0);
        
        
        item.use(player);
        
        assertEquals(beforeOperation, player.getHP(), 0.0);
    }
}
