package de.nicrizzos.game;

import de.nicrizzos.game.content.chapters.Chapters;
import de.nicrizzos.game.entities.Player;
import de.nicrizzos.game.scenesystem.Chapter;

/**
 * Represents a 'game', i.e. a state containing all relevant information for playing through the game.
 */
public class Game {
      /**
       * Player object that cannot be modified.
       */
      final static Player player = new Player("Nameless");
      
      /**
       * Contains the game's current
       */
      private Chapter currentChapter;
      
      public Game (String _name) {
            Chapters.createChapters();
            currentChapter = Chapters.getChapters().get(0);
            player.createPlayer(_name);
      }
      
      /**
       * @return The active player in the game.
       */
      public static Player getActivePlayer() {
            return player;
      }
      
      /**
       * Set the game's current chapter.
       * @param _currentChapter A chapter object.
       */
      public void setCurrentChapter(Chapter _currentChapter) {
            this.currentChapter = _currentChapter;
      }
      
      /**
       * @return The game's current chapter.
       */
      public Chapter getCurrentChapter() {
            return this.currentChapter;
      }
}
