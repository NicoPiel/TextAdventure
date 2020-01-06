package de.nicrizzos.game.scenesystem;

/**
 * Abstract class to represent different kinds of scenes, e.g. GameScenes and Battles.
 */
public abstract class SceneContent {
      /**
       * Unique ID given to this object.
       */
      protected String identification;
      
      /**
       * Sets the scene's ID; don't use this outside of construction.
       * @param identification The new ID.
       */
      public void setIdentification(String identification) {
            this.identification = identification;
      }
      
      /**
       * @return The scene's unique ID.
       */
      public String getIdentification() {
            return this.identification;
      }
}
