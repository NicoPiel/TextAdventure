package de.nicrizzos.game.content.chapters;

import de.nicrizzos.game.scenesystem.Chapter;
import de.nicrizzos.game.scenesystem.GameScene;
import de.nicrizzos.game.scenesystem.SceneContent;
import java.util.ArrayList;

public class Chapters {
      
      static ArrayList<Chapter> chapters;
      
      public static void createChapters() {
            SceneContent[] chaptersArray = new SceneContent[] {
                  new GameScene("Scene One",
                          "Du wachst auf. Du bist dumm.",
                          null)
            };
            
            Chapter chapterZero = new Chapter("Kapitel 0", chaptersArray);
            
            chapters = new ArrayList<>();
            chapters.add(chapterZero);
      }
}
