package de.nicrizzos.game;

import de.nicrizzos.game.entities.Player;

public class Game {
      final static Player player = new Player("Nameless");
      
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
