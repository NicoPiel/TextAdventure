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
      
      public void addObject(GameObject object) {
            sceneScript.add(object);
      }
      
      public GameObject getSceneObjectByName (String _name) throws NullPointerException {
            GameObject object = new GameObject(_name);
            
            if (sceneScript.contains(object)) {
                  return sceneScript.get(sceneScript.indexOf(object));
            }
            
            else throw new NullPointerException("The scene doesn't contain an element with that name.");
      }
      
      public String startScene() {
            return description;
      }
      
      public String getDescription() {
            return this.description;
      }
      
      public ArrayList<GameObject> getSceneScript() {
            return sceneScript;
      }
}
