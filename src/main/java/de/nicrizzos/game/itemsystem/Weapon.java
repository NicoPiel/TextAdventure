package de.nicrizzos.game.itemsystem;

public class Weapon extends Item {
      private int rawDamage;
      
      public Weapon (String _name, String _ID, int _rawDamage, int _itemLevel) {
            this.name = _name;
            this.uniqueID = _ID;
            this.rawDamage = _rawDamage;
            this.itemLevel = _itemLevel;
      }
      
      public int getDamageValue () {
            return rawDamage * itemLevel;
      }
      
      public int getRawDamage() {
            return rawDamage;
      }
      
      public void setRawDamage(int rawDamage) {
            this.rawDamage = rawDamage;
      }
      
      @Override
      public boolean equals(Object obj) {
            if (obj instanceof Weapon) {
                  return this.uniqueID.equals(((Item) obj).getUniqueID());
            }
            
            return false;
      }
}
