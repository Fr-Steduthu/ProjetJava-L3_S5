package core;

import core.items.Item;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InventoryIT {

    private Inventory inv;
    
    @Before
    public void setup() {
        inv = new Inventory(null, 5, 1);
    }
    
    @Test
    public void isEmptyInv() {
        Item[] items = inv.getItems();
        Item[] empty = new Item[]{};
        assertArrayEquals(empty, items);
    }
}
