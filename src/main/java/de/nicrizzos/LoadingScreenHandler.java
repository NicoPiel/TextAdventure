package de.nicrizzos;

import de.nicrizzos.game.Game;
import de.nicrizzos.game.utils.SQLiteManager;

public class LoadingScreenHandler {
      public static Game newGame(String _name, SQLiteManager sql) {
            final Game game = new Game(_name);
      
            sql.startSQL();
            sql.savePlayer(Game.getActivePlayer());
            sql.stopSQL();
      
            System.out.println("New player " + Game.getActivePlayer().getName() + " created.");
      
            return game;
      }
      
      public static Game loadGame(SQLiteManager _sql) {
            return null;
      }
      
      public static void saveGame(SQLiteManager _sql) {
            
      }
}
