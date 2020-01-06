package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.GameObject;
import de.nicrizzos.game.exceptions.GameException;

import java.util.ArrayList;

public class GameScene extends SceneContent {
      protected String description;
      
      protected int scriptPosition;
      protected ArrayList<GameObject> sceneObjects;
      protected ArrayList<SceneContent> subScenes;
      
      
      public GameScene(String _id, String _desc) {
            this.identification = _id;
            this.description = _desc;
            sceneObjects = new ArrayList<>();
            subScenes = new ArrayList<>();
            this.scriptPosition = 0;
      }
      
      public void addObject(GameObject object) {
            if (object != null) {
                  sceneObjects.add(object);
            } else System.err.println("Object is empty.");
      }
      
      public GameObject getSceneObjectByName(String _name) throws NullPointerException {
            GameObject object = new GameObject(_name);
            
            if (sceneObjects.contains(object)) {
                  return sceneObjects.get(sceneObjects.indexOf(object));
            } else throw new NullPointerException("The scene doesn't contain an element with that name.");
      }
      
      public String startScene() {
            return description;
      }
      
      public SceneContent getSubScene(String _id) throws GameException {
            if (this.hasSubScene(_id)) {
                  for (SceneContent c : subScenes) {
                        if (c.getIdentification().equals(_id)) {
                              return c;
                        }
                  }
            }
            
            throw new GameException("There is no such scene.");
      }
      
      public boolean hasSubScene(String _id) {
            if (this.subScenes == null || this.subScenes.isEmpty()) {
                  return false;
            }
            for (SceneContent c : subScenes) {
                  if (c != null) {
                        if (c.getIdentification().equals(_id)) {
                              return true;
                        }
                  }
                  else System.err.println("c is null.");
            }
            
            return false;
      }
      
      public String getDescription() {
            return this.description;
      }
      
      public ArrayList<GameObject> getSceneObjects() {
            return sceneObjects;
      }
      
      public void addSubScene(SceneContent _subScene) {
            if (_subScene != null) {
                  subScenes.add(_subScene);
            }
      }
      
      public ArrayList<SceneContent> getSubScenes() {
            return this.subScenes;
      }
      
      @Override
      public String toString() {
            return identification;
      }
      
      @Override
      public boolean equals(Object obj) {
            if (obj instanceof GameScene) {
                  return (this.identification.equals(((GameScene) obj).identification) && this.identification.equals(((GameScene) obj).description));
            }
            
            return false;
      }
}
