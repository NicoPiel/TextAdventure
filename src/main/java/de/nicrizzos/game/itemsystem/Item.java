package de.nicrizzos.game.itemsystem;

import java.util.Random;

public abstract class Item {
      protected String name;
      protected String uniqueID;
      protected int itemLevel;
      
      public Item () {
            this.name = "IT_EMPTY";
            this.uniqueID = "IT_EMPTY_" + new Random().nextInt();
      }
      
      public Item (String _ID) {
            this.uniqueID = _ID;
      }
      
      public Item (String _ID, String _name) {
            this.name = _name;
            this.uniqueID = _ID;
      }
      
      public void setName(String name) {
            this.name = name;
      }
      
      public String getName() {
            return name;
      }
      
      public String getUniqueID() {
            return uniqueID;
      }
      
      public int getItemLevel() {
            return itemLevel;
      }
      
      public void setItemLevel(int itemLevel) {
            this.itemLevel = itemLevel;
      }
}
