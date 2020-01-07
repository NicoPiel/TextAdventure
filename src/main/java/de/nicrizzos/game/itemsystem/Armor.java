package de.nicrizzos.game.itemsystem;

public class Armor extends Item {
      private int rawDefense;
      private String slot;
      
      public Armor(String _name, String _ID) {
            this.name = _name;
            this.uniqueID = _ID;
            this.itemLevel = 1;
      }
      
      public Armor(String _name, String _ID, int _itemLevel) {
            this.name = _name;
            this.uniqueID = _ID;
            this.itemLevel = _itemLevel;
      }
      
      public int getDefenseValue () {
            return this.rawDefense * this.itemLevel;
      }
      
      public void setRawDefense(int rawDefense) {
            this.rawDefense = rawDefense;
      }
      
      public int getRawDefense() {
            return rawDefense;
      }
      
      public void setSlot(String slot) {
            this.slot = slot;
      }
      
      public String getSlot() {
            return slot;
      }
}
      

