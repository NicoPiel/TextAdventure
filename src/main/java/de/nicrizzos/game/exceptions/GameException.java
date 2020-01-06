package de.nicrizzos.game.exceptions;

/**
 * This exception should be thrown whenever there would occur a game-breaking bug.
 */
public class GameException extends Exception {
      public GameException (String _message) {
            super(_message);
      }
}
