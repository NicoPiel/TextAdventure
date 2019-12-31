package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Chapter {
      private String name;
      private int chapterIndex;
      
      ArrayList<SceneContent> scenes;
      
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
      
      public String getName() {
            return this.name;
      }
      
      public int getChapterIndex() {
            return this.chapterIndex;
      }
}
