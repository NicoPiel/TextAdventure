package de.nicrizzos.game.scenesystem;

/**
 * Abstract class to represent different kinds of scenes, e.g. GameScenes and Battles.
 */
public abstract class SceneContent {
      /**
       * Unique ID given to this object.
       */
      protected String ID;
      
      /**
       * Sets the scene's ID; don't use this outside of construction.
       * @param ID The new ID.
       */
      public void setID(String ID) {
            this.ID = ID;
      }
      
      /**
       * @return The scene's unique ID.
       */
      public String getID() {
            return this.ID;
      }
}
