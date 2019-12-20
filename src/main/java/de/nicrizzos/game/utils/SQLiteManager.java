package de.nicrizzos.game.utils;
import de.nicrizzos.game.entities.Player;

import java.sql.*;


public class SQLiteManager {
      protected java.sql.Connection connection;
      private int slot;
      public SQLiteManager(int _slot) {
            slot = _slot;
      }

      public void startSQL() {
            try { //We use a try catch to avoid errors, hopefully we don't get any.
                  Class.forName("org.sqlite.JDBC"); //this accesses Driver in jdbc.
                  System.out.println("jdbc driver successfully accessed.");
            } catch (ClassNotFoundException e) {
                  e.printStackTrace();
                  System.err.println("jdbc driver unavailable!");
                  return;
            }
            try { //Another try catch to get any SQL errors (for example connections errors)
                  connection = DriverManager.getConnection("jdbc:sqlite:src/saves/save"+slot+".db");
                  //with the method getConnection() from DriverManager, we're trying to set
                  //the connection's url, username, password to the variables we made earlier and
                  //trying to get a connection at the same time. JDBC allows us to do this.
                  System.out.println("Opened sql connection.");
            } catch (SQLException e) { //catching errors)
                  e.printStackTrace(); //prints out SQLException errors to the console (if any)
                  System.err.println("Could not open MySQL connection.");
            }
      }

      public void stopSQL() {
            // invoke on disable.
            try { //using a try catch to catch connection errors (like wrong sql password...)
                  if (connection!=null && !connection.isClosed()){ //checking if connection isn't null to
                        //avoid receiving a nullpointer
                        connection.close(); //closing the connection field variable.
                        System.out.println("Con closed");
                  }
            } catch(Exception e) {
                  e.printStackTrace();
            }
      }
      public java.sql.Connection getConnect() {
            return connection;
      }


      public ResultSet executeWithResult(String sql) {

            ResultSet results = null;
            try {
                  PreparedStatement stmt = this.getConnect().prepareStatement(sql);
                  results = stmt.executeQuery();
            }catch(SQLException e) {
                  e.printStackTrace();
            }

            return results;
      }

      public boolean executeUpdate(String sql) {

            try {
                  PreparedStatement stmt = this.getConnect().prepareStatement(sql);
                  stmt.executeUpdate();

                  return true;
            } catch(SQLException e) {
                  e.printStackTrace();

                  return false;
            }

      }
      public boolean checkSlot(){
            String sql = "Select * FROM player;";
            try {
                  ResultSet result = executeWithResult(sql);
                  if (result.next()) {
                        System.out.println("true");
                        return true;
                  }
            }catch(SQLException e){
                  e.printStackTrace();
                  return false;
            }
            return false;
      }
      public String getPlayerName() {
            String sql = "Select name FROM player;";
            try {
                  ResultSet result = executeWithResult(sql);
                  if (result.next()) {
                        System.out.println(result.getString("name"));
                        return result.getString("name");
                  }
            }catch(SQLException e){
                  e.printStackTrace();
                  return null;
            }
            return null;

      }
      public void deleteSave() {

      }
      public void savePlayer(Player _player) {

            String sql = "UPDATE player SET strength = "+_player.getStrength()+",dexterity = "+_player.getDexterity()+", magic = "+_player.getMagic()+", " +
                    "vitality= "+_player.getVitality()+", defense="+_player.getDefense()+" ,lightFooted="+_player.getLightFooted()+" ,slightOfHand="+_player.getSlightOfHand()+", " +
                    "perception="+_player.getPerception()+" , survivalism = "+_player.getSurvivalism()+", knowledge = "+_player.getKnowledge()+", rhetoric = "+_player.getRhetoric()+
                    ", level = "+_player.getLevel()+", exp = "+_player.getExperience()+", expnextlvl = "+_player.getExperienceRequiredForNextLevel()+", health = "+_player.getHealth()+"," +
                    "currenthealth= "+_player.getCurrentHealth()+", mana= "+_player.getMana()+", currentmana= "+_player.getCurrentMana()+", skillPoints ="+_player.getSkillPoints()+"," +
                    " created = 1, canLevelUp = 1 WHERE name = '"+ _player.getName()+"' ;";
            executeUpdate(sql);
      }
      public void setPlayer(String _name){
            String sql = "INSERT INTO player(name) VALUES ('"+_name+"')";
            executeUpdate(sql);
      }
      public int getStat(String _stat){
            String sql = "SELECT "+_stat+" FROM player;";
            try {
                  ResultSet result = executeWithResult(sql);
                  if (result.next()) {
                        return result.getInt(_stat);
                  }
            }catch(SQLException e){
                  e.printStackTrace();
                  return 0;
            }
            return 0;
      }





}
