package de.nicrizzos.game;

import de.nicrizzos.game.content.chapters.Chapters;
import de.nicrizzos.game.content.items.Items;
import de.nicrizzos.game.entities.Player;
import de.nicrizzos.game.exceptions.GameException;
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
            createGame(_name);
      }
      
      private void createGame (String _name) {
            try {
                  Chapters.createChapters();
                  Items.createItems();
                  player.createPlayer(_name);
                  
                  currentChapter = Chapters.getChapters().get(0);
            }
            catch (GameException e) {
                  System.err.println(e.getMessage());
            }
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
