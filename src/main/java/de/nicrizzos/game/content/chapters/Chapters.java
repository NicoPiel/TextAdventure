package de.nicrizzos.game.content.chapters;

import de.nicrizzos.game.scenesystem.Chapter;

import java.util.ArrayList;

public class Chapters {
      
      static ArrayList<Chapter> chapters;
      
      public static void createChapters() {
            System.out.println("Creating chapters..");
            chapters = new ArrayList<>();
            Chapter chapter = Chapter.constructChapterFromFile();
            chapters.add(chapter);
            System.out.println("Done creating chapters..");
      }
      
      public static ArrayList<Chapter> getChapters() {
            return chapters;
      }
}


/*private static Chapter chapterZero = new Chapter("Kapitel 0",
              new SceneContent[] {
                      new GameScene("1",
                              "Du wachst auf. Du bist dumm."),
                      new Battle(1, new Enemy[]{
                              new Enemy("Schmutziger Schleim", 60, 50, 5),
                              new Enemy("Grober Goblin", 100, 60, 30),
                              new Enemy("Zickiger Zigeuner", 140, 62, 22)
                      }),
                      new GameScene("2",
                              """
                                      Die Schlacht ist gewonnen, gut gemacht.
                                      Aber scheiße, da kommt schon der Nächste.
                                      Viel Spaß.
                                      """),
                      new Battle(3, new Enemy[] {
                              new Enemy("Fetter Ficker", 700, 150, 0)
                      }),
                      new GameScene("4",
                              """
                                      Nicht schlecht, der war hart.
                                      """)
              })
*/