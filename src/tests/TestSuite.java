package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	tests.core.InventoryIT.class,
	tests.core.QuestIT.class,
	
	tests.core.character.CharacterIT.class,
	tests.core.character.NPCIT.class,
	
	tests.core.items.ItemIT.class,
	tests.core.items.DamageEnhancerIT.class,
	
	tests.core.places.ExitIT.class,
	tests.core.places.PlaceIT.class
})
public class TestSuite {

}
