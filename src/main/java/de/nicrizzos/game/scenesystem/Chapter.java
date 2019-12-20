package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.exceptions.*;

import java.util.ArrayList;

public class Chapter {
      private String name;
      
      ArrayList<GameScene> scenes;
      
      public Chapter (String _name, ArrayList<GameScene> _scenes) {
            this.name = _name;
            this.scenes = _scenes;
      }
      
      public String startChapter() throws GameException {
            if (!scenes.isEmpty())
                  return scenes.get(0).startScene();
            
            throw new GameException("Chapter is empty, could not find starting scene.");
      }
      
      public String getName() {
            return name;
      }
}
