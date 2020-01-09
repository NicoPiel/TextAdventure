package de.nicrizzos.game.savesystem;

import de.nicrizzos.game.Game;
import de.nicrizzos.game.content.chapters.Chapters;
import de.nicrizzos.game.exceptions.GameException;
import de.nicrizzos.game.utils.SQLiteManager;

public class SaveSystem {
      public static Game newGame(String _name, SQLiteManager _sql) {
            _sql.startSQL();
            
            final Game game = new Game(_name);
            _sql.savePlayer(game.getActivePlayer());
            
            System.out.println("New player " + game.getActivePlayer().getName() + " created.");
            
            try {
                  game.setCurrentChapter(Chapters.getChapterByID("1"));
            }catch (GameException ex){
                  ex.printStackTrace();
            }
            
            _sql.stopSQL();
            return game;
      }
      
      public static Game loadGame(SQLiteManager _sql) {
            _sql.startSQL();
            final Game game = new Game(_sql.getPlayerName());
            
            try {
                  game.setCurrentChapter(Chapters.getChapterByID(_sql.getCurrentChapter()));
                  game.getCurrentChapter().setCurrentScene(game.getCurrentChapter().getSceneByID(_sql.getCurrentScene()));
            }
            catch (GameException e) {
                  e.printStackTrace();
            }
            
            _sql.stopSQL();
            return game;
      }
      
      public static void saveGame(Game _game, SQLiteManager _sql) {
            _sql.save(
                    _game.getActivePlayer(),
                    _game.getCurrentChapter().getCurrentScene().getID(),
                    _game.getCurrentChapter().getID()
            );
      }
}
