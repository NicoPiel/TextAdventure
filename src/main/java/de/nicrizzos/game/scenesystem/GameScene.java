package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.GameObject;
import de.nicrizzos.game.exceptions.GameException;

import java.util.ArrayList;

public class GameScene extends SceneContent {
      protected String id;
      protected String description;
      
      protected int scriptPosition;
      protected ArrayList<GameObject> sceneObjects;
      protected ArrayList<SceneContent> subScenes;
      
      
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
      
      public SceneContent getSubScene (String _id) throws GameException {
            for (SceneContent c : subScenes) {
                  if (c.getIdentification().equals(_id)) {
                        return c;
                  }
            }
            
            throw new GameException("There is no such scene.");
      }
      
      public String getDescription() {
            return this.description;
      }
      
      public ArrayList<GameObject> getSceneObjects() {
            return sceneObjects;
      }
      
      public void addSubScene (SceneContent _subScene) {
            subScenes.add(_subScene);
      }
      
      public ArrayList<SceneContent> getSubScenes() {
            return subScenes;
      }
      
      @Override
      public String toString() {
            return id;
      }
      
      @Override
      public boolean equals(Object obj) {
            if (obj instanceof GameScene) {
                  return (this.id.equals(((GameScene) obj).id) && this.id.equals(((GameScene) obj).description));
            }
            
            return false;
      }
}
