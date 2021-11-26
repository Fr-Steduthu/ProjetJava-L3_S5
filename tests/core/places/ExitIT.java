package core.places;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ExitIT {
    
    private Place room1;
    private Place room2;
    private Exit exit;
    
    @Before
    public void setup() {
        room1 = new Place("Test room 1");
        room2 = new Place("Test room 2");
        exit = new Exit(room1, room2);
    }
    
    @Test
    public void correctExits() {
        Place[] places = exit.getRooms();
        assertEquals(room1, places[0]);
        assertEquals(room2, places[1]);
    }
    
    @Test
    public void ommitToGetOtherRoom() {
        assertEquals(room2, exit.getRoomOmmiting(room1));
        assertEquals(room1, exit.getRoomOmmiting(room2));
    }
    
    @Test
    public void removeToAndAddFrom() {
        Exit[] currentExits = room1.getExits();
        exit.removeFrom(room1);
        assertArrayEquals(new Exit[]{}, room1.getExits());
        exit.addTo(room1);
        assertArrayEquals(currentExits, room1.getExits());
    }
    
    @Test
    public void openClose() {
        assertTrue(exit.canPassThrough(null));
        exit.close();
        assertFalse(exit.canPassThrough(null));
        exit.open();
        assertTrue(exit.canPassThrough(null));
    }
}
