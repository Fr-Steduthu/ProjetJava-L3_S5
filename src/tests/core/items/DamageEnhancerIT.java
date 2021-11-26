package tests.core.items;


import core.character.Player;
import core.character.Monster;
import core.character.NPC;
import builtin.items.LightIronSpear;
import builtin.items.ReinforcedGloves;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DamageEnhancerIT {
    private Player p1;
    private Monster m1;
    private NPC n1;
    private LightIronSpear spear;
    private ReinforcedGloves gloves;
    
    @Before
    public void setUp () {
        p1 = new Player("Patate");
        m1 = new Monster("Epluche-patate", 10.0, 20.0, 10, 1);
        n1 = new NPC("Carotte", "Pour la carotte !", 10, 1);
        spear = new LightIronSpear();
        gloves = new ReinforcedGloves();
    }
    
    @Test
    public void testOnEquip () {
        double dmgPlayer1 = p1.getDamage();
        double dmgMonster1 = m1.getDamage();
        double dmgNPC1 = n1.getDamage();
        
        p1.addItem(spear);
        p1.equip(spear);
        m1.addItem(spear);
        m1.equip(spear);
        n1.addItem(gloves);
        n1.equip(gloves);
        
        assertEquals(p1.getDamage(), dmgPlayer1 + spear.getDamage(), 0.0);
        assertEquals(m1.getDamage(), dmgMonster1 + spear.getDamage(), 0.0);
        assertEquals(n1.getDamage(), dmgNPC1 + gloves.getDamage(), 0.0);
        
        p1.unequip(spear);
        m1.unequip(spear);
        n1.unequip(gloves);
    }
    
    @Test
    public void testOnUnequip () {
        
        p1.addItem(spear);
        p1.equip(spear);
        m1.addItem(spear);
        m1.equip(spear);
        n1.addItem(gloves);
        n1.equip(gloves);
        
        double dmgPlayer2 = p1.getDamage();
        double dmgMonster2 = m1.getDamage();
        double dmgNPC2 = n1.getDamage();
        
        p1.unequip(spear);
        m1.unequip(spear);
        n1.unequip(gloves);
        
        assertEquals(p1.getDamage(), dmgPlayer2 - spear.getDamage(), 0.0);
        assertEquals(m1.getDamage(), dmgMonster2 - spear.getDamage(), 0.0);
        assertEquals(n1.getDamage(), dmgNPC2 - gloves.getDamage(), 0.0);
    }
}
