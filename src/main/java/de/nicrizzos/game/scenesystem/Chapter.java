package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.Game;
import de.nicrizzos.game.GameObject;
import de.nicrizzos.game.entities.Enemy;
import de.nicrizzos.game.exceptions.*;
import org.jdom2.*;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Container for a number of scenes 
 */
public class Chapter {
      private String name;
      private int chapterIndex;
      private SceneContent currentScene;
      
      ArrayList<SceneContent> scenes;
      
      public Chapter() {
            this.name = "ERROR_CHAPTER_NO_NAME";
            SceneContent[] _scenes = new SceneContent[]{
                    new GameScene("ERROR_CHAPTER_NO_NAME", "ERROR_CHAPTER_NO_NAME")
            };
            this.scenes = new ArrayList<>(Arrays.asList(_scenes));
            chapterIndex = 0;
      }
      
      public Chapter(String _name, SceneContent[] _scenes) {
            this.name = _name;
            this.scenes = new ArrayList<>(Arrays.asList(_scenes));
            chapterIndex = 0;
      }
      
      public Chapter(String _name, int _chapterIndex, SceneContent[] _scenes) {
            this.name = _name;
            this.chapterIndex = _chapterIndex;
            this.scenes = new ArrayList<>(Arrays.asList(_scenes));
            chapterIndex = 0;
      }
      
      public String startChapter() throws GameException {
            if (!scenes.isEmpty() && (scenes.get(0) instanceof GameScene))
                  return ((GameScene) scenes.get(0)).startScene();
            
            throw new GameException("Chapter is empty, could not find starting scene.");
      }
      
      public String getCurrentSceneDescription() {
            return getCurrentScene().getDescription();
      }
      
      public SceneContent continueChapter() {
            chapterIndex++;
            
            if (chapterIndex < scenes.size()) {
                  setCurrentScene(scenes.get(chapterIndex));
                  return scenes.get(chapterIndex);
            } else return null;
      }
      
      public void addScene(SceneContent _scene) {
            this.scenes.add(_scene);
      }
      
      public static Chapter constructChapterFromFile(int _chapterID) throws GameException {
            System.out.println("Constructing scenes..");
            String filename = "src/main/java/de/nicrizzos/game/content/chapters/scenes.xml";
            Document doc;
            Chapter output = new Chapter();
            
            // Removes error scene
            output.scenes.remove(0);
            
            try {
                  doc = new SAXBuilder().build(filename);
                  System.out.println("Opening '" + filename + "'..");
                  
                  List<Element> chapters = doc.getRootElement().getChildren("Chapter");
                  
                  System.out.println("Retrieved chapter list..");
                  
                  List<Element> sceneList = null;
                  
                  if (chapters != null && !chapters.isEmpty()) {
                        for (Element e : chapters) {
                              if (Integer.parseInt(e.getAttributeValue("id")) == _chapterID) {
                                    sceneList = e.getChildren();
                              }
                        }
                        
                        if (sceneList != null && !sceneList.isEmpty()) {
                              for (Element scene : sceneList) {
                                    System.out.println("Constructing scenes for chapter " + _chapterID);
            
                                    if (scene.getName().equals("Scene")) {
                                          output.addScene(constructScene(scene));
                                    } else if (scene.getName().equals("Battle")) {
                                          output.addScene(constructBattle(scene, output.getScenes().size()));
                                    }
                              }
                        }
                        else System.err.println("Scene list empty.");
                  }
                  else System.err.println("Chapter list empty.");
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
      
      private static GameScene constructScene (Element scene) throws GameException {
            String sceneID = scene.getAttributeValue("id");
            
            System.out.println("Constructing: " + scene.getName() + " " + sceneID);
            String newSceneName = scene.getAttributeValue("id");
      
            List<Element> descriptionElementList = scene
                    .getChild("Head")
                    .getChild("Texts")
                    .getChildren();
      
            StringBuilder description = new StringBuilder();
      
            for (Element e : descriptionElementList) {
                  description.append(e.getText());
                  description.append("\n");
            }
      
            GameScene newScene = new GameScene(newSceneName, description.toString());
      
            List<Element> actionList = scene
                    .getChild("Head")
                    .getChild("Actions")
                    .getChildren();
      
            for (Element e : actionList) {
                  if (e != null) {
                        newScene.addObject(
                                new GameObject(
                                        e.getAttributeValue("name"),
                                        e.getAttributeValue("id"),
                                        Boolean.parseBoolean(e.getAttributeValue("specialAction"))
                                )
                        );
                        
                        //System.out.println("Added object " + e.getAttributeValue("name"));
                  } else throw new GameException("Adding object failed.");
            }
      
            //System.out.println("Building sub-scenes for scene " + sceneID);
      
            Element subScenesElement = scene.getChild("SubScenes");
            
            if (subScenesElement != null) {
                  List<Element> subScenes = subScenesElement.getChildren();
                  
                  if (subScenes != null && !subScenes.isEmpty()) {
                        for (Element e : subScenes) {
                              newScene.addSubScene(constructScene(e));
                        }
                  }
                  else {
                        System.err.println("Sub-scenes empty.");
                  }
            }
      
            System.out.println("Scene " + sceneID + " done.");
      
            return newScene;
      }
      
      private static Battle constructBattle(Element _battle, int _index) {
            System.out.println("Constructing: " + _battle.getName());
            List<Element> enemies = _battle.getChildren();
            ArrayList<Enemy> newEnemies = new ArrayList<>();
      
            for (Element e : enemies) {
                  newEnemies.add(new Enemy(
                          e.getAttributeValue("name"),
                          Integer.parseInt(e.getAttributeValue("hitPoints")),
                          Integer.parseInt(e.getAttributeValue("damage")),
                          Integer.parseInt(e.getAttributeValue("defense")))
                  );
            }
            
            return new Battle(_index, newEnemies);
      }
      
      public void setCurrentScene(SceneContent _currentScene) {
            this.currentScene = _currentScene;
      }
      
      public GameScene getCurrentScene() {
            if (currentScene instanceof GameScene) {
                  return (GameScene) currentScene;
            }
            
            return null;
      }
      
      public GameScene getSceneByID (String _id) {
            for (SceneContent scn : scenes) {
                  if (scn instanceof GameScene) {
                        if (scn.getIdentification().equals(_id)) {
                              return (GameScene) scn;
                        }
                        else {
                              try {
                                    return (GameScene) ((GameScene) scn).getSubScene(_id);
                              }
                              catch (GameException e) {
                                    System.err.println("Something went wrong with the sub-scenes.");
                                    e.printStackTrace();
                              }
                        }
                  }
            }
            
            return null;
      }
      
      public Battle getCurrentBattle() {
            if (currentScene instanceof Battle) {
                  return (Battle) currentScene;
            }
            
            return null;
      }
      
      public void setName(String name) {
            this.name = name;
      }
      
      public String getName() {
            return this.name;
      }
      
      public void setChapterIndex(int _chapterIndex) {
            this.chapterIndex = _chapterIndex;
      }
      
      public int getChapterIndex() {
            return this.chapterIndex;
      }
      
      public ArrayList<SceneContent> getScenes() {
            return scenes;
      }
}
