package de.nicrizzos.game.content.chapters;

import de.nicrizzos.game.exceptions.GameException;
import de.nicrizzos.game.scenesystem.Chapter;

import java.util.ArrayList;

public class Chapters {
      
      static ArrayList<Chapter> chapters;
      
      public static void createChapters() {
            System.out.println("Creating chapters..");
            chapters = new ArrayList<>();
            try {
                  Chapter chapter = Chapter.constructChapterFromFile(1);
                  chapters.add(chapter);
            }
            catch (GameException e) {
                  System.out.println(e.getMessage());
            }
            
            System.out.println("Done creating chapters..");
      }
      
      public static ArrayList<Chapter> getChapters() {
            return chapters;
      }
}