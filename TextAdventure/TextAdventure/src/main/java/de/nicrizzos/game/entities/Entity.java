package de.nicrizzos.game.entities;

/**
 * An abstract class defining an 'entity', as in a 'living thing'
 */
public abstract class Entity {
      protected String identification;
      protected String name;
      
      /**
       * Will
       */
      public Entity () {
            this.name = "<nameless entity>";
            this.identification = this.name;
      }
      
      public Entity (String _name) {
            this.name = _name;
            this.identification = this.name;
      }
      
      public Entity (String _name, String _ID) {
            this.name = _name;
            this.identification = _ID;
      }
      
      // GETTERS
      
      public String getName () {
            return this.name;
      }
      
      public String getID () {
            return this.identification;
      }
}
