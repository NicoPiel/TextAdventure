package de.nicrizzos.game.itemsystem;

public class Armor extends Item {
      private int rawDefense;
      private String slot;
      
      public Armor(String _name, String _ID, String _slot, int _rawDefense, int _itemLevel) {
            this.name = _name;
            this.uniqueID = _ID;
            this.slot = _slot;
            this.rawDefense = _rawDefense;
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
      
      @Override
      public boolean equals(Object obj) {
            if (obj instanceof Armor) {
                  return this.uniqueID.equals(((Item) obj).getUniqueID());
            }
            
            return false;
      }
}
      

