package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.GameObject;
import de.nicrizzos.game.exceptions.GameException;

import java.util.ArrayList;

/**
 * Represents a scene in the game.
 * It possesses a description, objects and sub-scenes (as of version 1.1).
 * @author Nico Piel
 * @version 1.1
 */
public class GameScene extends SceneContent {
      /**
       * A string that contains visual information on the scene.
       */
      protected String description;
      
      /**
       * A list of objects, which can be interacted with, located within the scene.
       */
      protected ArrayList<GameObject> sceneObjects;
      
      /**
       * A list of sub-scenes this scene contains.
       * Each sub-scene is also a GameScene or Battle.
       */
      protected ArrayList<SceneContent> subScenes;
      
      /**
       * Constructs an empty GameScene with a unique ID and a description, so content can be added to it.
       * @param _id Unique ID for the scene.
       * @param _desc Visual description of the scene.
       */
      public GameScene(String _id, String _desc) {
            this.identification = _id;
            this.description = _desc;
            sceneObjects = new ArrayList<>();
            subScenes = new ArrayList<>();
      }
      
      /**
       * Adds an object to the scene's object list.
       * @param object GameObject
       */
      public void addObject(GameObject object) {
            if (object != null) {
                  sceneObjects.add(object);
            } else System.err.println("Object is empty.");
      }
      
      /**
       * Searches for an object located within this scene.
       * @param _id The object's name.
       * @return GameObject with that ID, if it exists.
       * @throws NullPointerException Thrown, if there is no such GameObject in the scene.
       */
      public GameObject getSceneObjectByID(String _id) throws NullPointerException {
            GameObject object = new GameObject(_id);
            
            if (sceneObjects.contains(object)) {
                  return sceneObjects.get(sceneObjects.indexOf(object));
            } else throw new NullPointerException("The scene doesn't contain an element with that ID.");
      }
      
      /**
       * Searches for a sub-scene of this scene or any of its children.
       * @param _id ID to look for.
       * @return SceneContent that was found.
       * @throws GameException Thrown, if there isn't a sub-scene with that name.
       */
      public SceneContent getSubScene(String _id) throws GameException {
            if (!this.hasSubScene(_id)) {
                  for (SceneContent c : subScenes) {
                        if (c instanceof GameScene) {
                              return ((GameScene) c).getSubScene(_id);
                        }
                  }
            }
            else {
                  for (SceneContent c : subScenes) {
                        if (c.getIdentification().equals(_id)) {
                              return c;
                        }
                  }
            }
            
            throw new GameException("No such sub-scene found.");
      }
      
      /**
       * Checks, whether this scene has an immediate sub-scene with the given ID.
       * @param _id ID to look for.
       * @return True, if there is such a sub-scene.
       */
      public boolean hasSubScene(String _id) {
            if (this.subScenes == null || this.subScenes.isEmpty()) {
                  return false;
            }
            for (SceneContent c : subScenes) {
                  if (c != null) {
                        if (c.getIdentification().equals(_id)) {
                              return true;
                        }
                  } else System.err.println("c is null.");
            }
            
            return false;
      }
      
      /**
       * @return The scene's description.
       */
      public String getDescription() {
            return this.description;
      }
      
      /**
       * @return The list of objects in this scene.
       */
      public ArrayList<GameObject> getSceneObjects() {
            return this.sceneObjects;
      }
      
      /**
       * Adds a sub-scene to the scene's list.
       * @param _subScene GameScene or Battle.
       */
      public void addSubScene(SceneContent _subScene) {
            if (_subScene != null) {
                  subScenes.add(_subScene);
            }
      }
      
      /**
       * @return The scene's list of sub-scenes.
       */
      public ArrayList<SceneContent> getSubScenes() {
            return this.subScenes;
      }
      
      @Override
      public String toString() {
            return identification;
      }
      
      /**
       * @param obj Object to compare with.
       * @return True, if IDs are equal.
       */
      @Override
      public boolean equals(Object obj) {
            if (obj instanceof GameScene) {
                  if (this.identification.equals(((GameScene) obj).identification) && !this.description.equals(((GameScene) obj).getDescription())) {
                        try {
                              throw new GameException("There are scenes with the same ID, but different content.");
                        } catch (GameException e) {
                              e.printStackTrace();
                        }
                  }
                  
                  return this.identification.equals(((GameScene) obj).getDescription());
            }
            
            return false;
      }
}
