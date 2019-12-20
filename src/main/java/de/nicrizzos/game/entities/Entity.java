package de.nicrizzos.game.entities;

import de.nicrizzos.game.GameObject;

/**
 * An abstract class defining an 'entity', as in a 'thing'
 */
public abstract class Entity extends GameObject {
      public Entity () {
            super();
      }
      
      public Entity (String _name) {
            super(_name);
      }
      
      public Entity (String _name, String _ID) {
            super(_name, _ID);
      }
}
