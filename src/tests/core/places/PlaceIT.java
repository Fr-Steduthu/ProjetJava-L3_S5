package core.places;

import builtin.characters.monsters.Blob;
import core.items.Item;
import core.character.Character;
import builtin.characters.npcs.Grain;
import builtin.items.SaND;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PlaceIT {
    
    private final String roomName = "A test room";
    
    private Place room;
    private Place room2;
    private List<Item> itemList;
    private List<Character> npcList;
    private List<Exit> exits;
    
    @Before
    public void setup() {
        room = new Place(roomName);
        room2 = new Place(roomName);
        itemList = new ArrayList<>();
        npcList = new ArrayList<>();
        exits = new ArrayList<>();
        itemList.add(new SaND());
        npcList.add(new Grain());
        exits.add(new Exit(room, room2)); // An exit must now know the two connected rooms
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
    
    // addNpc, removeNpc (NPC)
    @Test
    public void npc() {
        room.addNpc(npcList.get(0));
        assertArrayEquals(npcList.toArray(), room.getNpcs());
        assertEquals(1, room.getNpcs().length);
        room.removeNpc(npcList.get(0));
        npcList.remove(0);
        assertArrayEquals(npcList.toArray(), room.getNpcs());
    }
    
    // addNpc, removeNpc (Monster)
    @Test
    public void monster() {
        npcList.remove(npcList.get(0));
        npcList.add(new Blob());
        room.addNpc(npcList.get(0));
        assertArrayEquals(npcList.toArray(), room.getNpcs());
        assertEquals(1, room.getNpcs().length);
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
    
    public Exit[] exitToArray(ArrayList<Exit> exitsAList) {
        Exit[] exits = {};
        return exitsAList.toArray(exits);
    }
    
    // setExits, getExits
    @Test
    public void exit() {
        room.setExits(exitToArray((ArrayList<Exit>) exits));
        assertArrayEquals(exits.toArray(), room.getExits());
    }
    
    // addExits and removeExits tested indirectly by ExitIT's removeToAndAddFrom
    
    // getName
    @Test
    public void testName() {
        assertEquals(roomName, room.getName());
    }
}
