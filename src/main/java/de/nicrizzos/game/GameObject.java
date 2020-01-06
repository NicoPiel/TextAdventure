package de.nicrizzos.game;

import de.nicrizzos.game.entities.Entity;

public class GameObject {
      protected String identification;
      protected String name;
      protected boolean specialAction;
      
      public GameObject () {
            this.name = "<nameless entity>";
            this.identification = this.name;
            this.specialAction = false;
            System.err.println("You shouldn't use empty GameObjects!");
      }
      
      public GameObject (String _name) {
            this.name = _name;
            this.identification = this.name;
            this.specialAction = false;
      }
      
      public GameObject (String _name, String _ID) {
            this.name = _name;
            this.identification = _ID;
            this.specialAction = false;
      }
      
      public GameObject (String _name, String _ID, boolean _specialAction) {
            this.name = _name;
            this.identification = _ID;
            this.specialAction = _specialAction;
      }
      // GETTERS
      
      public String getName () {
            return this.name;
      }
      
      public String getID () {
            return this.identification;
      }
      
      public boolean isPlayer() {
            return false;
      }
      
      @Override
      public boolean equals(Object obj) {
            if (obj instanceof GameObject)
                  return (this.name.equals(((GameObject) obj).getName())) && this.identification.equals(((GameObject) obj).getID());
            else return false;
      }
}
