package core.places;

import core.items.Item;
import core.character.Character;
import custom.characters.npcs.Grain;
import custom.items.SaND;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlaceIT {
    
    private final String roomName = "A test room";
    
    private Place room;
    private List<Item> itemList;
    private List<Character> npcList;
    private List<Exit> exits;
    
    @Before
    public void setup() {
        room = new Place(roomName);
        itemList = new ArrayList<>();
        npcList = new ArrayList<>();
        exits = new ArrayList<>();
        itemList.add(new SaND());
        npcList.add(new Grain());
        exits.add(new Exit(room, null));
    }
    
    // fill, getItems, getNpcs
    @Test
    public void fillRoom() {
        try {
            room.fill(itemList, npcList);
            assertArrayEquals(itemList.toArray(), room.getItems());
            assertArrayEquals(npcList.toArray(), room.getNpcs());
        } catch (Exception ex) {
            assertTrue("Caught exception while filling room.", false);
        }
    }
    
    // addNpc, removeNpc
    @Test
    public void npc() {
        room.addNpc(npcList.get(0));
        assertArrayEquals(npcList.toArray(), room.getNpcs());
        room.removeNpc(npcList.get(0));
        npcList.remove(0);
        assertArrayEquals(npcList.toArray(), room.getNpcs());
    }
    
    // addItem, removeItem
    @Test
    public void item() {
        room.addItem(itemList.get(0));
        assertArrayEquals(itemList.toArray(), room.getItems());
        room.removeItem(itemList.get(0));
        itemList.remove(0);
        assertArrayEquals(itemList.toArray(), room.getItems());
    }
    
    // setExits, getExits
    @Test
    public void exit() {
        room.setExits((Exit[]) exits.toArray());
        assertArrayEquals(exits.toArray(), room.getExits());
    }
    
    // getName
    @Test
    public void testName() {
        assertEquals(roomName, room.getName());
    }
}
