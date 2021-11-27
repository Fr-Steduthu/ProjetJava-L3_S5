import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	core.InventoryIT.class,
	core.QuestIT.class,
	
	core.character.CharacterIT.class,
	core.character.NPCIT.class,
	
	core.items.ItemIT.class,
	core.items.DamageEnhancerIT.class,
	
	core.places.ExitIT.class,
	core.places.PlaceIT.class
})
public class TestSuite {

}
