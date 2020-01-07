package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.GameObject;
import de.nicrizzos.game.entities.Enemy;
import de.nicrizzos.game.exceptions.GameException;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Container for a number of scenes.
 * This will be constructed by the <code>Chapters</code> class.
 *
 * @author Nico Piel
 * @version 1.1
 * @see de.nicrizzos.game.content.chapters.Chapters
 */
public class Chapter {
      /**
       * The chapter's name. Currently, pretty much useless.
       */
      private String name;
      
      private String ID;
      
      /**
       * The index of the current scene or how far along the chapter the game is.
       */
      private int chapterIndex;
      
      /**
       * The scene that is currently being displayed.
       */
      private SceneContent currentScene;
      
      /**
       * A list of GameScenes; this will contain all the scenes belonging to a single chapter.
       */
      ArrayList<SceneContent> scenes;
      
      /**
       * Constructs an empty chapter, so it can be filled with content.
       */
      public Chapter() {
            this.name = "ERROR_CHAPTER_EMPTY";
            SceneContent[] _scenes = new SceneContent[]{
                    new GameScene("ERROR_CHAPTER_EMPTY", "ERROR_CHAPTER_EMPTY")
            };
            this.scenes = new ArrayList<>(Arrays.asList(_scenes));
            chapterIndex = 0;
      }
      
      /**
       * @return The current scene's description string.
       */
      public String getCurrentSceneDescription() {
            return getCurrentScene().getDescription();
      }
      
      /**
       * Continue through the chapter by index.
       *
       * @return The next scene.
       * @deprecated
       */
      public SceneContent continueChapter() {
            chapterIndex++;
            
            if (chapterIndex < scenes.size()) {
                  setCurrentScene(scenes.get(chapterIndex));
                  return scenes.get(chapterIndex);
            } else return null;
      }
      
      /**
       * Adds a scene to the chapter's scene list.
       *
       * @param _scene Scene to be added.
       */
      public void addScene(SceneContent _scene) {
            this.scenes.add(_scene);
      }
      
      /**
       * Builds an internal structure out of an XML file's description of a chapter.
       * Use this each time the <code>Chapters</code> class is wiped from memory.
       *
       * @param _chapterID The chapter's unique ID.
       * @return A finished chapter object.
       * @throws GameException Thrown
       */
      public static Chapter constructChapterFromFile(String _chapterID) throws GameException {
            System.out.println("Constructing scenes..");
            // The path to the 'scenes.xml' file.
            final String filename = "src/main/java/de/nicrizzos/game/content/chapters/scenes.xml";
            // The raw document object.
            Document doc;
            // The empty chapter the will now be filled with content.
            Chapter output = new Chapter();
            
            // This removes the error scene that an empty chapter contains by default.
            output.scenes.remove(0);
            
            try {
                  doc = new SAXBuilder().build(filename);
                  System.out.println("Opened '" + filename + "'..");
                  
                  // Gets a list of chapters from the XML document.
                  List<Element> chapters = doc.getRootElement().getChildren("Chapter");
                  
                  System.out.println("Retrieved chapter list..");
                  
                  // An empty list that will later contain all the scenes.
                  List<Element> sceneList = null;
                  
                  // Search for the chapter with the given ID.
                  if (chapters != null && !chapters.isEmpty()) {
                        for (Element e : chapters) {
                              if (e.getAttributeValue("id").equals(_chapterID)) {
                                    sceneList = e.getChildren();
                                    output.setName(e.getAttributeValue("name"));
                                    output.setID(e.getAttributeValue("id"));
                              }
                        }
                        
                        if (sceneList != null && !sceneList.isEmpty()) {
                              for (Element scene : sceneList) {
                                    System.out.println("Constructing scenes for chapter " + _chapterID);
                                    
                                    if (scene.getName().equals("Scene")) {
                                          output.addScene(constructScene(scene));
                                    } else if (scene.getName().equals("Battle")) {
                                          output.addScene(constructBattle(scene, output.getScenes().size()));
                                    } else throw new GameException("Wrong Scene tags in the XML file.");
                              }
                        } else throw new GameException("No such chapter found.");
                  } else throw new GameException("Chapter list is empty. Check the XML file.");
            } catch (IOException e) {
                  System.err.println("There was a problem opening the file '" + filename + "'.");
                  e.printStackTrace();
            } catch (JDOMException e) {
                  System.err.println("JDOM ran into a problem.");
                  e.printStackTrace();
            }
            output.setCurrentScene(output.getScenes().get(0));
            
            return output;
      }
      
      /**
       * Builds a scene based on the XMl file.
       * This will recursively add sub-scenes to the scene.
       *
       * @param scene An XML element that will be scanned for its content.
       * @return The finished scene, ready to be added to a chapter.
       * @throws GameException Thrown whenever a tag is misplaced or empty.
       * @see GameScene
       */
      private static GameScene constructScene(Element scene) throws GameException {
            // Get the scene's unique ID.
            String sceneID = scene.getAttributeValue("id");
            
            System.out.println("Constructing: " + scene.getName() + " " + sceneID);
            
            // Get all the <Text> tags to build a description string.
            List<Element> descriptionElementList = scene
                    .getChild("Head")
                    .getChild("Texts")
                    .getChildren();
            
            StringBuilder description = new StringBuilder();
            
            // Append the <Text> tags' content to the StringBuilder.
            for (Element e : descriptionElementList) {
                  description.append(e.getText());
                  description.append("\n");
            }
            
            // Build a new scene object with the scene's ID and the description.
            GameScene newScene = new GameScene(sceneID, description.toString());
            
            // Get all the <Action> tags
            List<Element> actionList = scene
                    .getChild("Head")
                    .getChild("Actions")
                    .getChildren();
            
            // Add each action to the scene's object list.
            for (Element e : actionList) {
                  if (e != null) {
                        newScene.addObject(
                                new GameObject(
                                        e.getAttributeValue("name"),
                                        e.getAttributeValue("id"),
                                        Boolean.parseBoolean(e.getAttributeValue("specialAction"))
                                )
                        );
                  } else throw new GameException("Adding object failed. No such object in the scene.");
            }
            
            // Get the <SubScenes> tag.
            Element subScenesElement = scene
                    .getChild("SubScenes");
            
            if (subScenesElement != null) {
                  // Get all <SubScene> tags.
                  List<Element> subScenes = subScenesElement.getChildren();
                  
                  // Add each sub-scene to the scene's sub-scene list.
                  if (subScenes != null && !subScenes.isEmpty()) {
                        for (Element e : subScenes) {
                              newScene.addSubScene(constructScene(e));
                        }
                  } else throw new GameException("There are no sub-scenes even though there is a <SubScene> tag.");
            }
            
            System.out.println("Scene " + sceneID + " done.");
            
            return newScene;
      }
      
      /**
       * Builds a Battle object based on the XML file.
       *
       * @param _battle An XML element that will be scanned for its content.
       * @param _index  The chapter's current index, so the battle knows where to go after it's done.
       * @return A Battle object that can then be started in the scene.
       * @see Battle
       * @see Enemy
       */
      private static Battle constructBattle(Element _battle, int _index) {
            System.out.println("Constructing: " + _battle.getName());
            
            // Get all <Enemy> tags.
            List<Element> enemies = _battle.getChildren();
            
            // List to be filled with enemies.
            ArrayList<Enemy> newEnemies = new ArrayList<>();
            
            // Construct a new enemy object for each tag.
            for (Element e : enemies) {
                  newEnemies.add(new Enemy(
                          e.getAttributeValue("name"),
                          Integer.parseInt(e.getAttributeValue("hitPoints")),
                          Integer.parseInt(e.getAttributeValue("damage")),
                          Integer.parseInt(e.getAttributeValue("defense")))
                  );
            }
            
            // Build the new Battle object and return it to the calling function.
            return new Battle(_index, newEnemies);
      }
      
      /**
       * Sets the chapter's current scene.
       *
       * @param _currentScene Scene object.
       */
      public void setCurrentScene(SceneContent _currentScene) {
            this.currentScene = _currentScene;
      }
      
      /**
       * Searches for a scene using its unique ID.
       *
       * @param _id The scene's unique ID.
       * @return A GameScene
       */
      public GameScene getSceneByID(String _id) {
            // Go through all the chapter's scenes.
            for (SceneContent scn : scenes) {
                  // Only search for sub-scenes, if it's a GameScene.
                  // Battles shouldn't have sub-scenes, so this would produce a NullPointer.
                  if (scn instanceof GameScene) {
                        // End the search, if scene was found.
                        if (scn.getIdentification().equals(_id)) {
                              return (GameScene) scn;
                        }
                        // Recursively check all sub-scenes.
                        else {
                              try {
                                    return (GameScene) ((GameScene) scn).getSubScene(_id);
                              } catch (GameException e) {
                                    System.err.println("Something went wrong with the sub-scenes.");
                                    e.printStackTrace();
                              }
                        }
                  }
            }
            
            return null;
      }
      
      /**
       * @return The chapter's current scene, ignoring its class; use cautiously.
       */
      public SceneContent getCurrentSceneIgnoreClass() {
            return this.currentScene;
      }
      
      /**
       * @return The chapter's current scene, if scene is a GameScene.
       */
      public GameScene getCurrentScene() {
            if (currentScene instanceof GameScene) {
                  return (GameScene) currentScene;
            }
            
            return null;
      }
      
      /**
       * @return Battle object, if current scene is a Battle.
       */
      public Battle getCurrentBattle() {
            if (currentScene instanceof Battle) {
                  return (Battle) currentScene;
            }
            
            return null;
      }
      
      /**
       * Sets the chapter's name.
       *
       * @param name Value
       */
      public void setName(String name) {
            this.name = name;
      }
      
      /**
       * @return The chapter's name.
       */
      public String getName() {
            return this.name;
      }
      
      /**
       * Sets the chapter's current index.
       *
       * @param _chapterIndex Value
       */
      public void setChapterIndex(int _chapterIndex) {
            this.chapterIndex = _chapterIndex;
      }
      
      /**
       * @return The chapter's current index.
       */
      public int getChapterIndex() {
            return this.chapterIndex;
      }
      
      public void setID(String ID) {
            this.ID = ID;
      }
      
      public String getID() {
            return ID;
      }
      
      /**
       * @return The chapter's list of scenes.
       */
      public ArrayList<SceneContent> getScenes() {
            return scenes;
      }
}
