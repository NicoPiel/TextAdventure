package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.GameObject;

import java.util.ArrayList;

public class GameScene extends SceneContent {
      protected String id;
      protected String description;
      
      protected int scriptPosition;
      protected ArrayList<GameObject> sceneObjects;
      
      
      public GameScene (String _id, String _desc) {
            this.id = _id;
            this.description = _desc;
            sceneObjects = new ArrayList<>();
            this.scriptPosition = 0;
      }
      
      public void addObject(GameObject object) {
            if (object != null) {
                  sceneObjects.add(object);
            }
            else System.err.println("Object is empty.");
      }
      
      public GameObject getSceneObjectByName (String _name) throws NullPointerException {
            GameObject object = new GameObject(_name);
            
            if (sceneObjects.contains(object)) {
                  return sceneObjects.get(sceneObjects.indexOf(object));
            }
            
            else throw new NullPointerException("The scene doesn't contain an element with that name.");
      }
      
      public String startScene() {
            return description;
      }
      
      public String getDescription() {
            return this.description;
      }
      
      public ArrayList<GameObject> getSceneObjects() {
            return sceneObjects;
      }
      
      @Override
      public String toString() {
            return id;
      }
}
