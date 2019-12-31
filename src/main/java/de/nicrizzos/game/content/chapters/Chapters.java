package de.nicrizzos.game.content.chapters;

import de.nicrizzos.game.entities.Enemy;
import de.nicrizzos.game.scenesystem.Battle;
import de.nicrizzos.game.scenesystem.Chapter;
import de.nicrizzos.game.scenesystem.GameScene;
import de.nicrizzos.game.scenesystem.SceneContent;
import java.util.ArrayList;

public class Chapters {
      
      static ArrayList<Chapter> chapters;
      
      public static void createChapters() {
            SceneContent[] chaptersArray = new SceneContent[] {
                  new GameScene("Scene One",
                          "Du wachst auf. Du bist dumm."),
                    new Battle(new Enemy[] {
                            new Enemy ("Schmutziger Schleim", 60, 50, 5),
                            new Enemy("Grober Goblin", 100, 60, 30),
                            new Enemy("Zickiger Zigeuner", 140, 62, 22)
                    })
            };
            
            Chapter chapterZero = new Chapter("Kapitel 0", chaptersArray);
            
            chapters = new ArrayList<>();
            chapters.add(chapterZero);
      }

      public static ArrayList<Chapter> getChapters() {
            return chapters;
      }
}
