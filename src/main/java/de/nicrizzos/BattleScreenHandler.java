package de.nicrizzos;

import de.nicrizzos.game.Game;
import de.nicrizzos.game.entities.Enemy;
import de.nicrizzos.game.entities.Player;
import de.nicrizzos.game.scenesystem.Battle;
import de.nicrizzos.game.utils.SQLiteManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;

public class BattleScreenHandler {

      Game game;
      Player player;
      Battle battle;
      @FXML
      private Label lbl_playername;
      @FXML
      private Label lbl_enemy1name;
      @FXML
      private Label lbl_enemy2name;
      @FXML
      private Label lbl_enemy3name;
      @FXML
      private Label lbl_enemy1health;
      @FXML
      private Label lbl_enemy2health;
      @FXML
      private Label lbl_enemy3health;
      @FXML
      private Label lbl_health;
      @FXML
      private Label lbl_mana;
      @FXML
      private Button btn_enemy1;
      @FXML
      private Button btn_enemy2;
      @FXML
      private Button btn_enemy3;
      @FXML
      private ProgressBar pgb_playerhealth;
      @FXML
      private ProgressBar pgb_playermana;
      @FXML
      private ProgressBar pgb_enemy1health;
      @FXML
      private ProgressBar pgb_enemy2health;
      @FXML
      private ProgressBar pgb_enemy3health;
      private SQLiteManager sql;

      public void Init(Game _game, SQLiteManager _sql){
            game =_game;
            sql = _sql;
            player = Game.getActivePlayer();
            battle = new Battle (player, new Enemy[] {
                    new Enemy ("Schleim", 60, 50, 5),
                    new Enemy("Goblin", 100, 60, 30),
                    new Enemy("Zigeuner", 140, 62, 22)
            });
            refreshScreen();
      }
      
      public void Init(Game _game, SQLiteManager _sql, Battle _battle){
            game =_game;
            sql = _sql;
            player = Game.getActivePlayer();
            battle = _battle;
            refreshScreen();
      }
      
      @FXML
      public void attackClicked() {
            switch(battle.getNumberOfEnemies()) {
                  case 1 -> {
                        btn_enemy1.setText(battle.getEnemy(0).getName());
                        setButtonVisibility(1);
                  }
                  case 2 -> {
                        btn_enemy1.setText(battle.getEnemy(0).getName());
                        btn_enemy2.setText(battle.getEnemy(1).getName());
                        setButtonVisibility(2);
                  }
                  case 3 -> {
                        btn_enemy1.setText(battle.getEnemy(0).getName());
                        btn_enemy2.setText(battle.getEnemy(1).getName());
                        btn_enemy3.setText(battle.getEnemy(2).getName());
                        setButtonVisibility(3);
                  }
            }
      }
      public void setButtonVisibility(int _enemynumber) {
            switch (_enemynumber){
                  case 1 -> {
                        if(battle.getEnemy(0).isAlive()) {
                              btn_enemy1.setVisible(true);
                        }else{
                              btn_enemy1.setVisible(false);
                        }
                  }
                  case 2 -> {
                        if(battle.getEnemy(0).isAlive()) {
                              btn_enemy1.setVisible(true);
                        }else{
                              btn_enemy1.setVisible(false);
                        }
                        if(battle.getEnemy(1).isAlive()) {
                              btn_enemy2.setVisible(true);
                        }else{
                              btn_enemy2.setVisible(false);
                        }
                  }
                  case 3 ->{
                        if(battle.getEnemy(0).isAlive()) {
                              btn_enemy1.setVisible(true);
                        }else{
                              btn_enemy1.setVisible(false);
                        }
                        if(battle.getEnemy(1).isAlive()) {
                              btn_enemy2.setVisible(true);
                        }else{
                              btn_enemy2.setVisible(false);
                        }
                        if(battle.getEnemy(2).isAlive()) {
                              btn_enemy3.setVisible(true);
                        }else{
                              btn_enemy3.setVisible(false);
                        }
                  
                  }
            }
            
      }
      
      @FXML
      public void attackEnemy(ActionEvent e) {
            Button pressed = (Button) e.getSource();
            switch(pressed.getId()) {
                  case "btn_enemy1" ->  {
                        if (battle.getEnemy(0).isAlive())
                              battle.attack(battle.getEnemy(0), player.getDamage());
                        else System.out.println(battle.getEnemy(0).getName() + " is already dead.");
                  }
                  case "btn_enemy2" ->  {
                        if (battle.getEnemy(1).isAlive())
                              battle.attack(battle.getEnemy(1), player.getDamage());
                        else System.out.println(battle.getEnemy(1).getName() + " is already dead.");
                  }
                  case "btn_enemy3" -> {
                        if (battle.getEnemy(2).isAlive())
                              battle.attack(battle.getEnemy(2), player.getDamage());
                        else System.out.println(battle.getEnemy(2).getName() + " is already dead.");
                  }
            }
            
            if (battle.isAnyEnemyAlive()) {
                  battle.enemyTurn();
            }
            else {
                  System.out.println("All enemies have been defeated.");
                  
                  battle.getReward(Game.getActivePlayer());

                  FXMLLoader fxmlLoader = new FXMLLoader();
                  fxmlLoader.setLocation(getClass().getResource("mainscene.fxml"));
                  Parent switchScene = null;
                  try {
                        switchScene= fxmlLoader.load();
                  } catch (IOException ex){
                        ex.printStackTrace();
                  }
                  Scene sc = new Scene(switchScene);
                  MainSceneHandler main = fxmlLoader.getController();
                  main.InitFromBattle(game, sql, battle.getChapterIndex());
                  sql.stopSQL();
                  Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)e.getSource()).getScene().getWindow();
                  stageTheEventSourceNodeBelongs.setScene(sc);
            }
            
            if (!player.isAlive()) {
                  System.out.println("You died.");
      
                  FXMLLoader fxmlLoader = new FXMLLoader();
                  fxmlLoader.setLocation(getClass().getResource("mainscene.fxml"));
                  Parent switchScene = null;
                  try {
                        switchScene= fxmlLoader.load();
                  } catch (IOException ex){
                        ex.printStackTrace();
                  }
                  Scene sc = new Scene(switchScene);
                  MainSceneHandler main = fxmlLoader.getController();
                  main.InitFromBattle(game, sql, battle.getChapterIndex() - 2);
                  Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)e.getSource()).getScene().getWindow();
                  stageTheEventSourceNodeBelongs.setScene(sc);
            }
            
            refreshScreen();
      }
      
      @FXML
      public void defend () {
            player.startDefense();
            battle.enemyTurn();
            player.stopDefense();
      }

      
      
      
      
      
      
      
      
      public void refreshScreen() {
            lbl_playername.setText(player.getName());
            pgb_playerhealth.setProgress(player.getHealthPercentage());
            pgb_playermana.setProgress(player.getManaPercentage());
            lbl_health.setText(player.getCurrentHealth() + " / " + player.getHealth());
            lbl_mana.setText(player.getCurrentMana() + " / " + player.getMana());
            btn_enemy1.setVisible(false);
            btn_enemy2.setVisible(false);
            btn_enemy3.setVisible(false);
            switch(battle.getNumberOfEnemies()) {
                  case 1 -> {
                        pgb_enemy1health.setVisible(true);
                        pgb_enemy2health.setVisible(false);
                        pgb_enemy3health.setVisible(false);
                        lbl_enemy1name.setVisible(true);
                        lbl_enemy2name.setVisible(false);
                        lbl_enemy3name.setVisible(false);
                        lbl_enemy1health.setVisible(true);
                        lbl_enemy2health.setVisible(false);
                        lbl_enemy3health.setVisible(false);

                        lbl_enemy1name.setText(battle.getEnemy(0).getName());
                        pgb_enemy1health.setProgress(battle.getEnemy(0).getHealthPercentage());
                        lbl_enemy1health.setText(battle.getEnemy(0).getCurrentHealth() +" / " +battle.getEnemy(0).getHealth());

                  }
                  case 2 -> {
                        pgb_enemy1health.setVisible(true);
                        pgb_enemy2health.setVisible(true);
                        pgb_enemy3health.setVisible(false);
                        lbl_enemy1name.setVisible(true);
                        lbl_enemy2name.setVisible(true);
                        lbl_enemy3name.setVisible(false);
                        lbl_enemy1health.setVisible(true);
                        lbl_enemy2health.setVisible(true);
                        lbl_enemy3health.setVisible(false);


                        lbl_enemy1name.setText(battle.getEnemy(0).getName());
                        lbl_enemy2name.setText(battle.getEnemy(1).getName());
                        pgb_enemy1health.setProgress(battle.getEnemy(0).getHealthPercentage());
                        pgb_enemy2health.setProgress(battle.getEnemy(1).getHealthPercentage());
                        lbl_enemy1health.setText(battle.getEnemy(0).getCurrentHealth() +" / " +battle.getEnemy(0).getHealth());
                        lbl_enemy2health.setText(battle.getEnemy(1).getCurrentHealth() +" / " +battle.getEnemy(1).getHealth());
                  }
                  case 3 -> {
                        pgb_enemy1health.setVisible(true);
                        pgb_enemy2health.setVisible(true);
                        pgb_enemy3health.setVisible(true);
                        lbl_enemy1name.setVisible(true);
                        lbl_enemy2name.setVisible(true);
                        lbl_enemy3name.setVisible(true);
                        lbl_enemy1health.setVisible(true);
                        lbl_enemy2health.setVisible(true);
                        lbl_enemy3health.setVisible(true);

                        lbl_enemy1name.setText(battle.getEnemy(0).getName());
                        lbl_enemy2name.setText(battle.getEnemy(1).getName());
                        lbl_enemy3name.setText(battle.getEnemy(2).getName());
                        pgb_enemy1health.setProgress(battle.getEnemy(0).getHealthPercentage());
                        pgb_enemy2health.setProgress(battle.getEnemy(1).getHealthPercentage());
                        pgb_enemy3health.setProgress(battle.getEnemy(2).getHealthPercentage());



                        lbl_enemy1health.setText(battle.getEnemy(0).getCurrentHealth() +" / " +battle.getEnemy(0).getHealth());
                        lbl_enemy2health.setText(battle.getEnemy(1).getCurrentHealth() +" / " +battle.getEnemy(1).getHealth());
                        lbl_enemy3health.setText(battle.getEnemy(2).getCurrentHealth() +" / " +battle.getEnemy(2).getHealth());

                  }
            }
      }

}