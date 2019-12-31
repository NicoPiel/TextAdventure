package de.nicrizzos.game.utils;

import de.nicrizzos.game.entities.Player;

import java.sql.*;


public class SQLiteManager {
      /**
       * The connection variable the SQLManager will be using
       * @see java.sql.Connection
       */
      protected java.sql.Connection connection;
      
      /**
       * The save slot that should be written to.
       */
      private int slot;
      
      /**
       * Use this to create an SQLManager for the respective save slot.
       * @param _slot
       */
      public SQLiteManager(int _slot) {
            slot = _slot;
      }
      
      /**
       * Establishes a connection to the respective save database.
       */
      public void startSQL() {
            try {
                  Class.forName("org.sqlite.JDBC"); //this accesses the Driver in JDBC.
                  System.out.println("jdbc driver successfully accessed.");
            } catch (ClassNotFoundException e) {
                  e.printStackTrace();
                  System.err.println("jdbc driver unavailable!");
                  return;
            }
            try {
                  connection = DriverManager.getConnection("jdbc:sqlite:src/saves/save" + slot + ".db");
                  // With the method getConnection() from DriverManager, we're trying to set
                  // the connection's url, username, password to the variables we made earlier and
                  // trying to get a connection at the same time. JDBC allows us to do this.
                  System.out.println("Opened sql connection.");
            } catch (SQLException e) {
                  e.printStackTrace();
                  System.err.println("Could not open MySQL connection.");
            }
      }
      
      /**
       * Terminates the connection to the SQL server; always use this when you're done using the database.
       */
      public void stopSQL() {
            try {
                  if (connection != null && !connection.isClosed()) {
                        // Checking if the connection is null to avoid receiving a null-pointer
                        connection.close(); // Closing the connection.
                        System.out.println("Con closed");
                  }
            } catch (Exception e) {
                  e.printStackTrace();
            }
      }
      
      /**
       * Fetch the SQLManager's current database connection.
       * @return The current connection.
       * @see java.sql.Connection
       * @see DriverManager#getConnection(String)
       */
      public java.sql.Connection getConnection() {
            return this.connection;
      }
      
      /**
       * Will execute an SQL statement and save the results for further enquiry.
       * @param sql The SQL query as a string literal.
       * @return The query's results.
       * @see ResultSet
       */
      public ResultSet executeWithResult(String sql) {
            
            ResultSet results = null;
            try {
                  PreparedStatement stmt = this.getConnection().prepareStatement(sql);
                  results = stmt.executeQuery();
            } catch (SQLException e) {
                  e.printStackTrace();
            }
            
            return results;
      }
      
      /**
       * Will execute an SQL statement which does not expect a return value (e.g. update).
       * @param sql The SQL query as a string literal.
       * @return Returns true, if everything went ok.
       */
      public boolean executeUpdate(String sql) {
            
            try {
                  PreparedStatement stmt = this.getConnection().prepareStatement(sql);
                  stmt.executeUpdate();
                  
                  return true;
            } catch (SQLException e) {
                  e.printStackTrace();
                  
                  return false;
            }
            
      }
      
      /**
       * Checks if a player exists in the database.
       * @return True, if the database isn't empty.
       */
      public boolean checkIfPlayerExists() {
            String sql = "Select * FROM player;";
            try {
                  ResultSet result = executeWithResult(sql);
                  if (result.next()) {
                        System.out.println("true");
                        return true;
                  }
            } catch (SQLException e) {
                  e.printStackTrace();
                  return false;
            }
            return false;
      }
      
      /**
       * Fetch the player's chosen name.
       * @return The player's name.
       */
      public String getPlayerName() {
            String sql = "Select name FROM player;";
            try {
                  ResultSet result = executeWithResult(sql);
                  if (result.next()) {
                        System.out.println(result.getString("name"));
                        return result.getString("name");
                  }
            } catch (SQLException e) {
                  e.printStackTrace();
                  return null;
            }
            return null;
            
      }
      
      /**
       * Dummy method, will later delete saves.
       */
      public void deleteSave() {
            String sql = "DELETE FROM player WHERE true;";
            this.executeUpdate(sql);
      }
      
      /**
       * Saves the player's current stats to the database.
       * @param _player The player whose stats to use.
       */
      public void savePlayer(Player _player) {
            String sql =
                    "UPDATE player SET " +
                    "strength=" + _player.getStrength() +
                    ", dexterity=" + _player.getDexterity() +
                    ", magic=" + _player.getMagic() +
                    ", vitality=" + _player.getVitality() +
                    ", defense=" + _player.getDefensiveness() +
                    ", lightFooted=" + _player.getLightFooted() +
                    ", slightOfHand=" + _player.getSlightOfHand() +
                    ", perception=" + _player.getPerception() +
                    ", survivalism=" + _player.getSurvivalism() +
                    ", knowledge=" + _player.getKnowledge() +
                    ", rhetoric=" + _player.getRhetoric() +
                    ", level=" + _player.getLevel() +
                    ", exp=" + _player.getExperience() +
                    ", expNextLvl=" + _player.getExperienceRequiredForNextLevel() +
                    ", health=" + _player.getHealth() +
                    ", currentHealth=" + _player.getCurrentHealth() +
                    ", mana=" + _player.getMana() +
                    ", currentMana=" + _player.getCurrentMana() +
                    ", skillPoints=" + _player.getSkillPoints() +
                    ", created=1" +
                    ", canLevelUp=1" +
                    " WHERE name='" + _player.getName() + "';";
            executeUpdate(sql);
      }
      
      /**
       * Will write the player's into the database.
       * @param _name The player's name.
       */
      public void setPlayer(String _name) {
            String sql = "INSERT INTO player(name) VALUES ('" + _name + "')";
            executeUpdate(sql);
      }
      
      /**
       * Searches the databases for this stat and returns and integer value. Returns 0, if empty or something goes wrong.
       *
       * @param _stat The stat to look for, represented as a string.
       * @return The stat's value; 0, if empty or something goes wrong.
       */
      public int getStat(String _stat) {
            String sql = "SELECT " + _stat + " FROM player;";
            try {
                  ResultSet result = executeWithResult(sql);
                  if (result.next()) {
                        return result.getInt(_stat);
                  }
            } catch (SQLException e) {
                  e.printStackTrace();
                  return 0;
            }
            return 0;
      }
      
      
}
