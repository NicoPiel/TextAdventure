package de.nicrizzos.game;

/**
 * Represents an object in the game world.
 * Extend if necessary.
 */
public class GameObject {
      /**
       * Unique ID to identify the object by.
       */
      protected String identification;
      
      /**
       * Non-unique name for the object.
       */
      protected String name;
      
      /**
       * Define whether the object can be interacted with in a special way (i.e. with a key).
       */
      protected boolean specialAction;
      
      /**
       * Creates an empty game object. Shouldn't use this.
       * @deprecated 1.0
       */
      public GameObject () {
            this.name = "<nameless entity>";
            this.identification = this.name;
            this.specialAction = false;
            System.err.println("You shouldn't use empty GameObjects!");
      }
      
      /**
       * Creates a game object by ID. Its name will be 'Empty object'.
       * @param _id The ID for this game object.
       */
      public GameObject (String _id) {
            this.name = "Empty object";
            this.identification = _id;
            this.specialAction = false;
      }
      
      /**
       * Creates a game object by name and ID. Shouldn't use.
       * @param _name The name for this game object.
       * @param _ID The unique ID for this object.
       * @deprecated 1.0
       */
      public GameObject (String _name, String _ID) {
            this.name = _name;
            this.identification = _ID;
            this.specialAction = false;
      }
      
      /**
       * Creates a game object by name and ID. Also defined whether a special action is possible.
       * @param _name The name for this game object.
       * @param _ID The unique ID for this object.
       * @param _specialAction Whether the object can be interacted with in a special way (i.e. with a key).
       */
      public GameObject (String _name, String _ID, boolean _specialAction) {
            this.name = _name;
            this.identification = _ID;
            this.specialAction = _specialAction;
      }
      // GETTERS
      
      /**
       * @return The object's name.
       */
      public String getName () {
            return this.name;
      }
      
      /**
       * @return The object's ID.
       */
      public String getID () {
            return this.identification;
      }
      
      /**
       * @return Whether the object is the player.
       */
      public boolean isPlayer() {
            return false;
      }
      
      /**
       * @param obj Object to compare with.
       * @return True, IDs are equal.
       */
      @Override
      public boolean equals(Object obj) {
            if (obj instanceof GameObject)
                  return this.identification.equals(((GameObject) obj).getID());
            else return false;
      }
}
