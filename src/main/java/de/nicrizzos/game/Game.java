package de.nicrizzos.game;

import de.nicrizzos.game.entities.Player;
import de.nicrizzos.game.scenesystem.Chapter;

public class Game {
      final static Player player = new Player("Nameless");
      
      private Chapter currentChapter;
      
      public Game (String _name) {
            player.createPlayer(_name);
      }
      
      /**
       * <p>
       * Submit strings defined in each scene to control actions.
       * Will then decide what to do.
       * </p>
       */
      public void SubmitInput (String _input) {
      
      }
      
      public static Player getActivePlayer() {
            return player;
      }
}
