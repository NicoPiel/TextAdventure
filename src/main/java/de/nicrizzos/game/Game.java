package de.nicrizzos.game;

import de.nicrizzos.game.entities.Player;

public class Game {
      final static Player player = new Player("Nameless");
      
      public Game () {
            player.createPlayer();
      }

      public void InputHandler () {
      
      }
      
      public static Player getActivePlayer() {
            return player;
      }
}
