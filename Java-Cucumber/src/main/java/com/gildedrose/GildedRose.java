package com.gildedrose;

class GildedRose {
  static final String AGED_BRIE = "Aged Brie";
  static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
  static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
  static final int MAX_QUALITY = 50;
  static final int MIN_QUALITY = 0;
  private static final int DAYS_UNTIL_EXPIRATION = 6;
  private static final int EXPIRATION_THRESHOLD = 11;
  public static final int MIN_SELL_IN = 0;

  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (Item item : items) {
      if (isAgedBrie(item) || isBackstage(item)) {
        increaseQuality(item);
        if (isBackstage(item)) {
          if (item.sellIn < EXPIRATION_THRESHOLD) {
            increaseQuality(item);
          }
          if (item.sellIn < DAYS_UNTIL_EXPIRATION) {
            increaseQuality(item);
          }
        }
      } else {
        decreaseQuality(item);
      }

      if (isNotSulfuras(item)) {
        item.sellIn--;
      }

      if (item.sellIn < MIN_SELL_IN) {
        if (isAgedBrie(item) && item.quality < MAX_QUALITY) {
          item.quality--;
        } else if (isBackstage(item)) {
          item.quality = MIN_QUALITY;
        } else if (!isAgedBrie(item) && item.quality > MAX_QUALITY) {
          decreaseQuality(item);
        }
      }
    }
  }


  private boolean isBackstage(Item item) {
    return item.name.equals(BACKSTAGE);
  }

  private boolean isNotSulfuras(Item item) {
    return !item.name.equals(SULFURAS);
  }

  private boolean isAgedBrie(Item item) {
    return item.name.equals(AGED_BRIE);
  }

  private void increaseQuality(Item item) {
    if (item.quality < MAX_QUALITY) {
      item.quality++;
    }
  }

  private void decreaseQuality(Item item) {
    if (item.quality > MIN_QUALITY && isNotSulfuras(item)) {
      item.quality--;
    }
  }

}
