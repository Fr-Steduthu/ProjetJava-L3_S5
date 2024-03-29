package tests.core.character;

import org.junit.Before;
import org.junit.Test;

import core.character.Character;
import core.character.NPC;
import core.place.Place;

import static org.junit.Assert.*;

public class NPCIT {
    
    private NPC npc;
    private Place location;
    
    @Before
    public void setup() {
        npc = new NPC("Test npc", "I am a test npc", 0);
        location = new Place("Test location");
        location.addNpc(npc);
    }
    
    // Tests the npc location when in the room and after talking to it
    @Test
    public void testNpcLocation() {
        Character[] arr = new Character[]{npc};
        assertArrayEquals(arr, location.getNpcs());
        npc.interact(null);
        arr = new Character[]{};
        assertArrayEquals(arr, location.getNpcs());
    }
}
