/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package core.items;

/**
 *
 * @author ntimmers
 */
import core.character.Player;
import core.character.Monster;
import core.character.NPC;
import custom.items.LightIronSpear;
import custom.items.ReinforcedGloves;

import org.junit.After;
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
        m1 = new Monster("Epluche-patate", 10.0f, 20.0f, 1000);
        n1 = new NPC("Carotte", "Pour la carotte !", 1000);
        spear = new LightIronSpear();
        gloves = new ReinforcedGloves();
    }
    
    @Test
    public void testOnEquip () {
        float dmgPlayer = p1.getDamage();
        float dmgMonster = m1.getDamage();
        float dmgNPC = n1.getDamage();
        
        p1.give(spear);
        p1.equip(spear);
        m1.give(spear);
        m1.equip(spear);
        n1.give(gloves);
        n1.equip(gloves);
        
        assertSame(p1.getDamage(), dmgPlayer + spear.getDamage());
        assertSame(m1.getDamage(), dmgMonster + spear.getDamage());
        assertSame(n1.getDamage(), dmgNPC + gloves.getDamage());
        
        p1.unequip(spear);
        m1.unequip(spear);
        n1.unequip(gloves);
    }
    
        @Test
    public void testOnUnequip () {
        
        p1.give(spear);
        p1.equip(spear);
        m1.give(spear);
        m1.equip(spear);
        n1.give(gloves);
        n1.equip(gloves);
        
        float dmgPlayer = p1.getDamage();
        float dmgMonster = m1.getDamage();
        float dmgNPC = n1.getDamage();
        
        p1.unequip(spear);
        m1.unequip(spear);
        n1.unequip(gloves);
        
        assertSame(p1.getDamage(), dmgPlayer - spear.getDamage());
        assertSame(m1.getDamage(), dmgMonster - spear.getDamage());
        assertSame(n1.getDamage(), dmgNPC - gloves.getDamage());
    }
}
