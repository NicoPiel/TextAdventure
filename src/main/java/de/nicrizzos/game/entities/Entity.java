package de.nicrizzos.game.entities;

/**
 * An abstract class defining an 'entity', as in a 'thing'
 */
public abstract class Entity {
      protected String identification;
      protected String name;
      
      /**
       * Will create a nameless entity, shouldn't use.
       */
      public Entity () {
            this.name = "<nameless entity>";
            this.identification = this.name;
            System.err.println("You shouldn't use empty entities!");
      }
      
      /**
       *
       * @param _name
       */
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
      
      public boolean isPlayer() {
            return false;
      }
}
