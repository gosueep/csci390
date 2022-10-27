package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    // check item is properly inserted into the app's list of items
    void GildedRoseConstructor() {
        Item[] items = new Item[] { new Item("NameTest", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("NameTest", app.items[0].name);
    }

    @Test
    // Test Item constructor
    void ItemConstructor() {
        Item item = new Item("NAME", 1, 0);
        assertEquals("NAME", item.name);
        assertEquals(1, item.sellIn);
        assertEquals(0, item.quality);
        assertEquals("NAME, 1, 0", item.toString());
    }

    @Test
    // Checking if empty name, and values
    void EmptyItems() {
        Item[] items = new Item[] { new Item("", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("", app.items[0].name);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    // Updating should fail if items are null
    // Eventually null checks or custom exceptions could be raised, having this test fail
    void NullItem() {
        Item[] items = new Item[] { new Item(null, 0, 0), null };
        GildedRose app = new GildedRose(items);

        boolean caught = false;
        try {
            app.updateQuality();
        }
        catch(NullPointerException e) {
            caught = true;
        }

        if(!caught)
            assert false;
        else
            assert true;
    }


    @Test
    // Tests on updating Aged Brie once
    void AgedBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 0),
                                    new Item("Aged Brie", 0, 50),
                                    new Item("Aged Brie", 5, 25)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // AgedBrie 1
        assertEquals(2, app.items[0].quality);      // Quality increments by 2 if sellIn < 0
        assertEquals(-1, app.items[0].sellIn);      // sellIn decreases

        // AgedBrie 2
        assertEquals(50, app.items[1].quality);     // Quality should not exceed 50 for Aged Brie
        assertEquals(-1, app.items[1].sellIn);      // sellIn decreases

        // AgedBrie 3
        assertEquals(26, app.items[2].quality);     // Quality should only increase once, since sellIn >= 0
        assertEquals(4, app.items[2].sellIn);       // sellIn should decrease by one
    }

    @Test
    // Tests on updating Aged Brie several times
    void AgedBrieBound() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 0),
                new Item("Aged Brie", 0, 50),
                new Item("Aged Brie", 5, 25)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // Test 50 is the upper bound of quality for Aged Brie
        for (int i = 0; i < 25; i++) {
            app.updateQuality();
        }
        assertEquals(50, app.items[0].quality);
        assertEquals(50, app.items[1].quality);
    }


    @Test
    // Tests updating on Sulfuras, Hand of Ragnaros Items
    void Sulfuras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 50, 100) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].sellIn);      // sellIn should be unchanged
        assertEquals(100, app.items[0].quality);    // quality should be unchanged
    }

    @Test
    // Tests updating on Sulfuras, Hand of Ragnaros items several times
    void SulfurasBounds() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 50, 100),
                                    new Item("Sulfuras, Hand of Ragnaros", -5, 30),
                                    new Item("Sulfuras, Hand of Ragnaros", -5, 50)};
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < 50; i++) {
            app.updateQuality();
        }
        assertEquals(50, app.items[0].sellIn);      // sellIn should be unchanged
        assertEquals(100, app.items[0].quality);    // quality cannot increase beyond 50, unless already set
        assertEquals(-5, app.items[1].sellIn);      // sellIn should be unchanged
        assertEquals(30, app.items[1].quality);     // quality cannot increase beyond 50
        assertEquals(-5, app.items[2].sellIn);      // sellIn should be unchanged
        assertEquals(50, app.items[2].quality);     // quality cannot increase beyond 50
    }

    @Test
    // Tests updating on "Backstage passes to a TAFKAL80ETC concert" Items
    void Backstage() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 5),
                                    new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5),
                                    new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50),
                                    new Item("Backstage passes to a TAFKAL80ETC concert", 0, -5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        // Item 1 - sellIn < 6
        assertEquals(4, app.items[0].sellIn);       // sellIn decreases
        assertEquals(8, app.items[0].quality);      // quality should add 3
        // Item 2 - sellIn < 11
        assertEquals(9, app.items[1].sellIn);       // sellIn decreases
        assertEquals(7, app.items[1].quality);      // quality should add 2
        // Item 3 - sellIn = 0
        assertEquals(-1, app.items[2].sellIn);      // sellIn decreases
        assertEquals(0, app.items[2].quality);     // quality should become 0
        // Item 3 - sellIn = 0, negative quality
        assertEquals(0, app.items[3].quality);     // quality should become 0
    }

    @Test
    // Tests updating on "Backstage passes to a TAFKAL80ETC concert" items several times
    void BackstageBounds() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 5),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 5),
                new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50)};
        GildedRose app = new GildedRose(items);
        for (int i = 0; i < 50; i++) {
            app.updateQuality();
        }
        // After sellIn has passed, quality for backstage passes should be 0
        assertEquals(0, app.items[0].quality);
        assertEquals(0, app.items[1].quality);
        assertEquals(0, app.items[2].quality);
    }

}
