package core.items;

import core.Inventory;
import core.character.Player;
import core.places.Place;
import builtin.items.SaND;
import builtin.items.SmallPot;
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
        player = new Player("A test entity");
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
        item.take(player);
        assertEquals(player.getClassInventory(), item.getLocation());
    }
    
    // use
    @Test
    public void useItem(){
        item = new SmallPot();
        player.setLocation(room);
        room.addItem(item);
        item.take(player);
        double beforeOperation = player.getHP();
        player.hurt(3.0);
        item.use(player);
        assertEquals(beforeOperation, player.getHP(), 0.0);
    }
}
