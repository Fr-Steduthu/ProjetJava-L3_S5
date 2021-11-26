package tests.core;

import builtin.places.entrances.TutorialRoom;
import core.Quest;
import core.character.Player;
import core.places.Place;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QuestIT {
    
    private Quest quest;
    private Player player;
    private Place[] dungeonRooms;
    private Place spawnRoom;
    private String objective;
    private Object objectiveObj;
    
    @Before
    public void setup() {
        player = new Player("A test player");
        spawnRoom = new TutorialRoom();
        objective = "Do nothing. This is a test.";
        objectiveObj = spawnRoom;
        dungeonRooms = new Place[]{spawnRoom, new Place("A test room")};
        quest = new Quest(player, spawnRoom, dungeonRooms, objective, (Place) objectiveObj);
    }
    
     @Test
     public void testGetsAndConstructor() {
         assertEquals(objective, quest.getObjective());
         assertEquals(objectiveObj, quest.getObjectiveObject());
         assertArrayEquals(dungeonRooms, quest.getDungeon());
         assertEquals(spawnRoom, quest.getStartingPoint());
         assertEquals(player, quest.getPlayer());
         assertEquals(spawnRoom, quest.getPlayer().getLocation());
     }
     
     @Test
     public void isRoomHere() {
         assertEquals(0, quest.find(spawnRoom));
         assertEquals(-1, quest.find(new Place("Not there")));
     }
     
     @Test
     public void newPlayer() {
         Player newPlayer = new Player("Replacement for the other");
         quest.setPlayer(newPlayer);
         assertEquals(newPlayer, quest.getPlayer());
     }
}
