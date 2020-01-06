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

public class Chapter {
      private String name;
      private int chapterIndex;
      
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
      
      public SceneContent continueChapter() {
            chapterIndex++;
            
            if (chapterIndex < scenes.size()) {
                  return scenes.get(chapterIndex);
            } else return null;
      }
      
      public void addScene(SceneContent _scene) {
            this.scenes.add(_scene);
      }
      
      public static Chapter constructChapterFromFile() throws GameException {
            System.out.println("Constructing scenes..");
            String filename = "src/main/java/de/nicrizzos/game/content/chapters/scenes.xml";
            Document doc;
            Chapter output = new Chapter();
            
            // Removes error scene
            output.scenes.remove(0);
            
            try {
                  doc = new SAXBuilder().build(filename);
                  System.out.println("Opening '" + filename + "'..");
                  
                  List<Element> sceneList = doc
                          .getRootElement()
                          .getChild("Chapter")
                          .getChild("Scenes")
                          .getChildren();
                  
                  for (Element scene : sceneList) {
                        System.out.println("Scene list isn't empty.");
                        
                        if (scene.getName().equals("Scene")) {
                              System.out.println("Constructing: " + scene.getName());
                              String newSceneName = scene.getAttributeValue("id");
      
                              List<Element> descriptionElementList = scene
                                      .getChild("Head")
                                      .getChild("Texts")
                                      .getChildren();
      
                              System.out.println("Acquired description list.");
                              
                              StringBuilder description = new StringBuilder();
      
                              for (Element e : descriptionElementList) {
                                    description.append(e.getText());
                                    description.append("\n");
                              }
      
                              System.out.println("Built description.");
      
                              GameScene newScene = new GameScene(newSceneName, description.toString());
      
                              List<Element> actionList = scene
                                      .getChild("Head")
                                      .getChild("Actions")
                                      .getChildren();
      
                              System.out.println("Acquired action list.");
      
                              if (actionList != null && !actionList.isEmpty()) {
                                    for (Element e : actionList) {
                                          System.out.println("Adding objects..");
                                          
                                          if (e != null) {
                                                System.out.println(e.getAttributeValue("name"));
                                                newScene.addObject(new GameObject(e.getAttributeValue("name")));
                                                System.out.println("Added object " + e.getAttributeValue("name"));
                                          }
                                          else throw new GameException("Adding object failed.");
                                    }
                              }
                              else System.err.println("Action list empty.");
                              
                              System.out.println("Done.");
      
                              output.addScene(newScene);
                        }
                        
                        else if (scene.getName().equals("Battle")) {
                              System.out.println("Constructing: " + scene.getName());
                              List<Element> enemies = scene.getChildren();
                              ArrayList<Enemy> newEnemies = new ArrayList<>();
                              
                              for (Element e : enemies) {
                                    newEnemies.add(new Enemy(
                                            e.getAttributeValue("name"),
                                            Integer.parseInt(e.getAttributeValue("hitPoints")),
                                            Integer.parseInt(e.getAttributeValue("damage")),
                                            Integer.parseInt(e.getAttributeValue("defense")))
                                    );
                              }
                              
                              output.addScene(new Battle(output.getScenes().size(), newEnemies));
                        }
                  }
            } catch (IOException e) {
                  System.err.println("There was a problem opening the file '" + filename + "'.");
                  e.printStackTrace();
            } catch (JDOMException e) {
                  System.err.println("JDOM ran into a problem.");
                  e.printStackTrace();
            }
            
            return output;
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
