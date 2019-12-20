package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.GameObject;

import java.util.ArrayList;

public class GameScene {
      protected String name;
      protected String description;
      
      protected int scriptPosition;
      protected ArrayList<GameObject> sceneScript;
      
      
      public GameScene (String _name, String _desc, ArrayList<GameObject> _sceneScript) {
            this.name = _name;
            this.description = _desc;
            this.sceneScript = _sceneScript;
            this.scriptPosition = 0;
      }
      
      public String startScene() {
            return description;
      }
      
      public String Continue () {
            if (scriptPosition < sceneScript.size() - 1)
                  return sceneScript.get(++scriptPosition).toString();
            else return description;
      }
}
