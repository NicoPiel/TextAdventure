package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.GameObject;

import java.util.ArrayList;
import java.util.Arrays;

public class GameScene {
      protected String name;
      protected String description;
      
      protected int scriptPosition;
      protected ArrayList<GameObject> sceneScript;
      
      
      public GameScene (String _name, String _desc, GameObject[] _sceneScript) {
            this.name = _name;
            this.description = _desc;
            this.sceneScript = new ArrayList<>(Arrays.asList(_sceneScript));
            this.scriptPosition = 0;
      }
      
      public String startScene() {
            return description;
      }
      
      public Object continueScene () {
            if (scriptPosition < sceneScript.size() - 1)
                  return sceneScript.get(++scriptPosition);
            else return description;
      }
}
