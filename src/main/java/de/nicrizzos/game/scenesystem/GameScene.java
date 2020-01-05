package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.GameObject;

import java.util.ArrayList;
import java.util.Arrays;

public class GameScene extends SceneContent {
      protected String name;
      protected String description;
      
      protected int scriptPosition;
      protected ArrayList<GameObject> sceneScript;
      
      
      public GameScene (String _name, String _desc) {
            this.name = _name;
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
