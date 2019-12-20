package de.nicrizzos.game.content.chapters;

import de.nicrizzos.game.scenesystem.Chapter;
import de.nicrizzos.game.scenesystem.GameScene;

import java.util.ArrayList;
import java.util.List;

public class Chapters {
      
      static ArrayList<Chapter> chapters;
      
      public static void createChapters() {
            chapters = new ArrayList<>();
            
            ArrayList<GameScene> scenesChapterOne = new ArrayList<>(List.of(
                  new GameScene (
                          "1",
                          "Test scene",
                          new ArrayList<>()
                  )
            ));
            chapters.add(new Chapter("Chapter One", scenesChapterOne));
      }
}
