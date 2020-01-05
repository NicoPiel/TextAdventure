package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.Game;
import de.nicrizzos.game.GameObject;
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
            SceneContent[] _scenes = new SceneContent[] {
                    new GameScene("ERROR_CHAPTER_NO_NAME", "ERROR_CHAPTER_NO_NAME")
            };
            this.scenes = new ArrayList<>(Arrays.asList(_scenes));
            chapterIndex = 0;
      }
      
      public Chapter (String _name, SceneContent[] _scenes) {
            this.name = _name;
            this.scenes = new ArrayList<>(Arrays.asList(_scenes));
            chapterIndex = 0;
      }
      
      public Chapter (String _name, int _chapterIndex, SceneContent[] _scenes) {
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
            }
            
            else return null;
      }
      
      public void addScene(SceneContent _scene) {
            this.scenes.add(_scene);
      }
      
      public static Chapter constructChapterFromFile() {
            System.out.println("Constructing scenes..");
            String filename = "src/main/java/de/nicrizzos/game/content/chapters/scenes.xml";
            Document doc;
            Chapter output = new Chapter();
            output.scenes.remove(0);
            
            try {
                  doc = new SAXBuilder().build(filename);
                  
                  Element newSceneElement = doc.getRootElement()
                          .getChild("Chapter")
                          .getChild("Scenes")
                          .getChild("Scene");
                  
                  
                  String newSceneName = newSceneElement.getAttributeValue("id");
                  
                  List<Element> descriptionElementList = newSceneElement
                                  .getChild("Head")
                                  .getChild("Texts")
                                  .getChildren();
                  
                  StringBuilder description = new StringBuilder();
                  
                  for (Element e : descriptionElementList) {
                        description.append(e.getText());
                        description.append("\n");
                  }
                  
                  GameScene newScene = new GameScene(newSceneName, description.toString());
                  
                  List<Element> actionList = newSceneElement
                          .getChild("Head")
                          .getChild("Actions")
                          .getChildren();
                  
                  for (Element e : actionList) {
                        newScene.addObject(new GameObject(e.getAttributeValue("name")));
                  }
                  
                  output.addScene(newScene);
            }
            catch (IOException e) {
                  System.err.println("There was a problem opening the file '" + filename + "'.");
                  e.printStackTrace();
            }
            catch (JDOMException e) {
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
}
