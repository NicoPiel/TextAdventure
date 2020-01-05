package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.GameObject;

import java.util.ArrayList;

public class GameScene extends SceneContent {
      protected String id;
      protected String description;
      
      protected int scriptPosition;
      protected ArrayList<GameObject> sceneScript;
      
      
      public GameScene (String _id, String _desc) {
            this.id = _id;
            this.description = _desc;
            this.scriptPosition = 0;
      }
      
      public String startScene() {
            return description;
      }
      
      public String getDescription() {
            return this.description;
      }
}
