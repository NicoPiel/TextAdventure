package de.nicrizzos;

import de.nicrizzos.game.Game;
import de.nicrizzos.game.entities.Enemy;
import de.nicrizzos.game.entities.Player;
import de.nicrizzos.game.scenesystem.Battle;
import de.nicrizzos.game.utils.SQLiteManager;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainsceneHandler {
      @FXML
      private TextArea ta_game;
      @FXML
      private ProgressBar pgb_life;
      @FXML
      private ProgressBar pgb_exp;
      @FXML
      private Label lbl_exp;
      @FXML
      private Label lbl_level;
      @FXML
      private Label lbl_playername;
      @FXML
      private Label lbl_life;
      @FXML
      private Label lbl_mana;
      @FXML
      private ProgressBar pgb_mana;
      @FXML
      private Label lbl_vitality;
      @FXML
      private Label lbl_str;
      @FXML
      private Label lbl_dex;
      @FXML
      private Label lbl_magic;
      @FXML
      private Label lbl_def;
      @FXML
      private Label lbl_light;
      @FXML
      private Label lbl_handskill;
      @FXML
      private Label lbl_perception;
      @FXML
      private Label lbl_survival;
      @FXML
      private Label lbl_knowledge;
      @FXML
      private Label lbl_speech;
      @FXML
      private ImageView helmetslot;
      @FXML
      private ImageView chestplateslot;
      @FXML
      private ImageView pantsslot;
      @FXML
      private ImageView shoeslot;
      @FXML
      private ImageView gloveslot;
      @FXML
      private ImageView shieldslot;
      @FXML
      private ImageView swordslot;
      @FXML
      private ImageView ringslot;
      @FXML
      private ImageView p_str;
      @FXML
      private ImageView p_dex;
      @FXML
      private ImageView p_magic;
      @FXML
      private ImageView p_vita;
      @FXML
      private ImageView p_def;
      @FXML
      private ImageView p_lightFooted;
      @FXML
      private ImageView p_handskill;
      @FXML
      private ImageView p_per;
      @FXML
      private ImageView p_surv;
      @FXML
      private ImageView p_knowledge;
      @FXML
      private ImageView p_rhetoric;
      
      private Game game;
      private Player player;
      private SQLiteManager sql;

      public void Init(int _slot, String _name, SQLiteManager _sql){
            game = new Game(_name);
            player = Game.getActivePlayer();
            _sql.stopSQL();
            sql = _sql;
            sql.stopSQL();
            constructCharInv();
            sql.savePlayer(player);
            this.refreshScene();
      }

      public void Init(int _slot, String _name,SQLiteManager _sql, boolean exist){
            constructCharInv();
            _sql.stopSQL();
            sql = _sql;
            sql.stopSQL();
            game = new Game(_name);
            player = Game.getActivePlayer();
            constructPlayer();

            this.refreshScene();
      }
      private void constructPlayer() {

            sql.startSQL();
            player.setStrength(sql.getStat("strength"));
            player.setDexterity(sql.getStat("dexterity"));
            player.setMagic(sql.getStat("magic"));
            player.setVitality(sql.getStat("vitality"));
            player.setDefense(sql.getStat("defense"));
            player.setLightFooted(sql.getStat("lightFooted"));
            player.setSlightOfHand(sql.getStat("slightOfHand"));
            player.setPerception(sql.getStat("perception"));
            player.setSurvivalism(sql.getStat("survivalism"));
            player.setKnowledge(sql.getStat("knowledge"));
            player.setRhetoric(sql.getStat("rhetoric"));
            player.setLevel(sql.getStat("Level"));
            player.setExperience(sql.getStat("exp"));
            player.setExperienceRequiredForNextLevel();
            player.setHealth(sql.getStat("health"));
            player.setCurrentHealth(sql.getStat("currenthealth"));
            player.setMana(sql.getStat("mana"));
            player.setCurrentMana(sql.getStat("currentmana"));
            player.setSkillPoints(sql.getStat("skillPoints"));
            if(player.getSkillPoints() > 0 ){
                  player.setCanLevelUp(true);
            }
            sql.stopSQL();






      }

      public void importGame(Game _game) {
            game = _game;
            player = Game.getActivePlayer();
            constructCharInv();
            this.refreshScene();
            
      }

      @FXML
      private void refreshScene() {
            setPlayerInformation();
            checkLevelUpButtons();
            sql.startSQL();
            sql.savePlayer(player);
            sql.stopSQL();
      }
      
      //TODO: Einen 'Continue' Button, der durch die Szenen führt.
      // Dazu einfach GameScene.continueScene(), dann mit if-else durch die Objekte.
      // Systeme dazu noch nicht vorhanden.
      
      
      private void checkLevelUpButtons() {
            if (player.canLevelUp()) {
                  p_def.setVisible(true);
                  p_dex.setVisible(true);
                  p_handskill.setVisible(true);
                  p_knowledge.setVisible(true);
                  p_lightFooted.setVisible(true);
                  p_magic.setVisible(true);
                  p_vita.setVisible(true);
                  p_per.setVisible(true);
                  p_rhetoric.setVisible(true);
                  p_str.setVisible(true);
                  p_surv.setVisible(true);
            } else {
                  p_def.setVisible(false);
                  p_dex.setVisible(false);
                  p_handskill.setVisible(false);
                  p_knowledge.setVisible(false);
                  p_lightFooted.setVisible(false);
                  p_magic.setVisible(false);
                  p_vita.setVisible(false);
                  p_per.setVisible(false);
                  p_rhetoric.setVisible(false);
                  p_str.setVisible(false);
                  p_surv.setVisible(false);
                  
            }
            
      }
      
      private void setPlayerInformation() {
            lbl_life.setText(player.getCurrentHealth() + " / " + player.getHealth());
            pgb_life.setProgress(player.getHealthPercentage());
            lbl_mana.setText(player.getCurrentMana() + " / " + player.getMana());
            pgb_mana.setProgress(player.getManaPercentage());
            lbl_exp.setText(player.getExperience() + " / " + player.getExperienceRequiredForNextLevel());
            pgb_exp.setProgress(player.getExperiencePercentage());
            
            lbl_playername.setText(player.getName());
            lbl_level.setText("Stufe: " + Integer.toString(player.getLevel()));
            
            lbl_str.setText(Integer.toString(player.getStrength()));
            lbl_dex.setText(Integer.toString(player.getDexterity()));
            lbl_magic.setText(Integer.toString(player.getMagic()));
            lbl_vitality.setText(Integer.toString(player.getVitality()));
            lbl_def.setText(Integer.toString(player.getDefense()));
            lbl_light.setText(Integer.toString(player.getLightFooted()));
            lbl_perception.setText(Integer.toString(player.getPerception()));
            lbl_handskill.setText(Integer.toString(player.getSlightOfHand()));
            lbl_survival.setText(Integer.toString(player.getSurvivalism()));
            lbl_knowledge.setText(Integer.toString(player.getKnowledge()));
            lbl_speech.setText(Integer.toString(player.getRhetoric()));
            
            
      }
      
      @FXML
      private void increaseStat(MouseEvent e) {
            
            ImageView image = (ImageView) e.getSource();
            System.out.println(image.getId());
            switch (image.getId()) {
                  case "p_str" -> player.increaseStat("strength");
                  case "p_dex" -> player.increaseStat("dexterity");
                  case "p_magic" -> player.increaseStat("magic");
                  case "p_vita" -> player.increaseStat("vitality");
                  case "p_def" -> player.increaseStat("defense");
                  case "p_lightFooted" -> player.increaseStat("lightFooted");
                  case "p_handskill" -> player.increaseStat("slightOfHand");
                  case "p_per" -> player.increaseStat("perception");
                  case "p_surv" -> player.increaseStat("survivalism");
                  case "p_knowledge" -> player.increaseStat("knowledge");
                  case "p_rhetoric" -> player.increaseStat("rhetoric");
            }
            refreshScene();
            
      }
      
      @FXML
      private void increaseExp() {
            player.addExperience(500);
            refreshScene();
      }

      @FXML
      private void fight(ActionEvent e) throws IOException {
            /*
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainscene.fxml"));
            Parent switchscene = fxmlLoader.load();
            Scene sc = new Scene(switchscene);
            BattlescreenHandler battlescreenHandler = fxmlLoader.getController();

           // battlescreenHandler.Init(game);
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(sc);
            */

      }










      private void constructCharInv() {
            File f = new File("src/images/helmet.png");
            Image i = new Image(f.toURI().toString());
            helmetslot.setImage(i);
            
            f = new File("src/images/chestplate.jpg");
            i = new Image(f.toURI().toString());
            chestplateslot.setImage(i);
            
            f = new File("src/images/pants.png");
            i = new Image(f.toURI().toString());
            pantsslot.setImage(i);
            
            f = new File("src/images/boots.png");
            i = new Image(f.toURI().toString());
            shoeslot.setImage(i);
            
            f = new File("src/images/hands.png");
            i = new Image(f.toURI().toString());
            gloveslot.setImage(i);
            
            f = new File("src/images/ring.png");
            i = new Image(f.toURI().toString());
            ringslot.setImage(i);
            
            f = new File("src/images/shield.png");
            i = new Image(f.toURI().toString());
            shieldslot.setImage(i);
            
            f = new File("src/images/sword.png");
            i = new Image(f.toURI().toString());
            swordslot.setImage(i);
            
            f = new File("src/images/plus.png");
            i = new Image(f.toURI().toString());
            p_def.setImage(i);
            p_dex.setImage(i);
            p_handskill.setImage(i);
            p_knowledge.setImage(i);
            p_lightFooted.setImage(i);
            p_magic.setImage(i);
            p_vita.setImage(i);
            p_per.setImage(i);
            p_rhetoric.setImage(i);
            p_str.setImage(i);
            p_surv.setImage(i);
            
            p_def.setVisible(false);
            p_dex.setVisible(false);
            p_handskill.setVisible(false);
            p_knowledge.setVisible(false);
            p_lightFooted.setVisible(false);
            p_magic.setVisible(false);
            p_vita.setVisible(false);
            p_per.setVisible(false);
            p_rhetoric.setVisible(false);
            p_str.setVisible(false);
            p_surv.setVisible(false);
            
            
      }
      
}
