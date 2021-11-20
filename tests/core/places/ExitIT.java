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
    public void testExit() {
        Place[] places = exit.getRooms();
        assertEquals(room1, places[0]);
        assertEquals(room2, places[1]);
    }
}