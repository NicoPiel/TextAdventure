package de.nicrizzos;

import de.nicrizzos.game.Game;
import de.nicrizzos.game.content.chapters.Chapters;
import de.nicrizzos.game.entities.Player;
import de.nicrizzos.game.exceptions.GameException;
import de.nicrizzos.game.scenesystem.Battle;
import de.nicrizzos.game.scenesystem.Chapter;
import de.nicrizzos.game.scenesystem.GameScene;
import de.nicrizzos.game.scenesystem.SceneContent;
import de.nicrizzos.game.utils.SQLiteManager;
import javafx.event.ActionEvent;
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

public class MainSceneHandler {
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
      private Label lbl_playerName;
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
      private Label lbl_slightOfHand;
      @FXML
      private Label lbl_perception;
      @FXML
      private Label lbl_survival;
      @FXML
      private Label lbl_knowledge;
      @FXML
      private Label lbl_speech;
      @FXML
      private ImageView helmetSlot;
      @FXML
      private ImageView chestplateSlot;
      @FXML
      private ImageView pantsSlot;
      @FXML
      private ImageView shoeSlot;
      @FXML
      private ImageView gloveSlot;
      @FXML
      private ImageView shieldSlot;
      @FXML
      private ImageView swordSlot;
      @FXML
      private ImageView ringSlot;
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
      private ImageView p_slightOfHand;
      @FXML
      private ImageView p_per;
      @FXML
      private ImageView p_survivalism;
      @FXML
      private ImageView p_knowledge;
      @FXML
      private ImageView p_rhetoric;
      
      private Game game;
      private Player player;
      private SQLiteManager sql;
      private Chapter currentChapter;
      
      public void Init(int _slot, String _name, SQLiteManager _sql) {
            game = new Game(_name);
            player = Game.getActivePlayer();
            sql = _sql;
            constructCharInventory();
            
            sql.startSQL();
            sql.savePlayer(player);
            sql.stopSQL();
            //currentChapter = Chapters.getChapters().get(0);
            /*try {
                  ta_game.setText(currentChapter.startChapter());
            }
            catch (GameException e) {
                  e.printStackTrace();
            }*/
            this.refreshScene();
      }
      
      public void Init(int _slot, String _name, SQLiteManager _sql, boolean exist) {
            constructCharInventory();
            sql = _sql;
            game = new Game(_name);
            player = Game.getActivePlayer();
            constructPlayer();
            
            this.refreshScene();
      }
      public void InitFromBattle(Game _game, SQLiteManager _sql, int _chapterIndex) {
            constructCharInventory();
            
            sql = _sql;
            
            game = _game;
            player = Game.getActivePlayer();
      
            //TODO: refactoring und immer das richtige kapitel holen
            currentChapter = Chapters.getChapters().get(0);
            if (_chapterIndex > 0) {
                  currentChapter.setChapterIndex(_chapterIndex);
                  player.setCurrentHealth(player.getHealth());
                  player.setCurrentMana(player.getMana());
            }
            else {
                  currentChapter.setChapterIndex(-1);
            }
            
            continueThroughChapter(null);

            this.refreshScene();
      }
      
      /**
       * Reads the database and writes stats onto the player.
       */
      private void constructPlayer() {
            sql.startSQL();
            player.setStrength(sql.getStat("strength"));
            player.setDexterity(sql.getStat("dexterity"));
            player.setMagic(sql.getStat("magic"));
            player.setVitality(sql.getStat("vitality"));
            player.setDefensiveness(sql.getStat("defense"));
            player.setLightFooted(sql.getStat("lightFooted"));
            player.setSlightOfHand(sql.getStat("slightOfHand"));
            player.setPerception(sql.getStat("perception"));
            player.setSurvivalism(sql.getStat("survivalism"));
            player.setKnowledge(sql.getStat("knowledge"));
            player.setRhetoric(sql.getStat("rhetoric"));
            player.setLevel(sql.getStat("level"));
            player.setExperience(sql.getStat("exp"));
            player.setExperienceRequiredForNextLevel();
            player.setHealth(sql.getStat("health"));
            player.setCurrentHealth(sql.getStat("currentHealth"));
            player.setMana(sql.getStat("mana"));
            player.setCurrentMana(sql.getStat("currentMana"));
            player.setSkillPoints(sql.getStat("skillPoints"));
            
            if (player.getSkillPoints() > 0) {
                  player.setCanLevelUp(true);
            }
            
            sql.stopSQL();
      }
      
      /**
       * Prototype function to copy a game state to a different scene.
       * @param _game The game which needs to be copied.
       */
      public void importGame(Game _game) {
            game = _game;
            player = Game.getActivePlayer();
            constructCharInventory();
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
      
      @FXML
      private void continueThroughChapter(ActionEvent e) {
            SceneContent newScene = currentChapter.continueChapter();
      
            if (newScene instanceof GameScene) {
                  ta_game.setText(((GameScene) newScene).getDescription());
            } else if (newScene instanceof Battle) {
                  try {
                        Battle battle = (Battle) newScene;
                        battle.setPlayer(player);
                        CreateNewBattle(e, battle);
                  }
                  catch (IOException ex) {
                        ex.printStackTrace();
                  }
            }
      }
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      
      private void checkLevelUpButtons() {
            if (player.canLevelUp()) {
                  setLevelUpButtonsVisible(true);
            } else {
                  setLevelUpButtonsVisible(false);
            }
      }
      
      private void setPlayerInformation() {
            lbl_life.setText(player.getCurrentHealth() + " / " + player.getHealth());
            pgb_life.setProgress(player.getHealthPercentage());
            lbl_mana.setText(player.getCurrentMana() + " / " + player.getMana());
            pgb_mana.setProgress(player.getManaPercentage());
            lbl_exp.setText(player.getExperience() + " / " + player.getExperienceRequiredForNextLevel());
            pgb_exp.setProgress(player.getExperiencePercentage());
            
            lbl_playerName.setText(player.getName());
            lbl_level.setText("Stufe: " + player.getLevel());
            
            lbl_str.setText(Integer.toString(player.getStrength()));
            lbl_dex.setText(Integer.toString(player.getDexterity()));
            lbl_magic.setText(Integer.toString(player.getMagic()));
            lbl_vitality.setText(Integer.toString(player.getVitality()));
            lbl_def.setText(Integer.toString(player.getDefensiveness()));
            lbl_light.setText(Integer.toString(player.getLightFooted()));
            lbl_perception.setText(Integer.toString(player.getPerception()));
            lbl_slightOfHand.setText(Integer.toString(player.getSlightOfHand()));
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
                  case "p_slightOfHand" -> player.increaseStat("slightOfHand");
                  case "p_per" -> player.increaseStat("perception");
                  case "p_survivalism" -> player.increaseStat("survivalism");
                  case "p_knowledge" -> player.increaseStat("knowledge");
                  case "p_rhetoric" -> player.increaseStat("rhetoric");
            }
            refreshScene();
            
      }
      
      @FXML
      private void increaseExp() {
            player.addExperience(1000);
            refreshScene();
      }
      
      @FXML
      private void CreateNewBattle(ActionEvent e) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("battlescreen.fxml"));
            Parent switchScene = fxmlLoader.load();
            Scene sc = new Scene(switchScene);
            BattleScreenHandler battlescreenHandler = fxmlLoader.getController();
            battlescreenHandler.Init(game, sql);
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(sc);
      }
      
      private void CreateNewBattle(ActionEvent e, Battle _battle) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("battlescreen.fxml"));
            Parent switchScene = fxmlLoader.load();
            Scene sc = new Scene(switchScene);
            BattleScreenHandler battlescreenHandler = fxmlLoader.getController();
            battlescreenHandler.Init(game, sql, _battle);
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(sc);
      }
      
      
      private void constructCharInventory() {
            setImageForInventorySlot(helmetSlot, "src/images/helmet.png");
            setImageForInventorySlot(chestplateSlot, "src/images/chestplate.jpg");
            setImageForInventorySlot(pantsSlot, "src/images/pants.png");
            setImageForInventorySlot(shoeSlot, "src/images/boots.png");
            setImageForInventorySlot(gloveSlot, "src/images/hands.png");
            setImageForInventorySlot(ringSlot, "src/images/ring.png");
            setImageForInventorySlot(shieldSlot, "src/images/shield.png");
            setImageForInventorySlot(swordSlot, "src/images/sword.png");
            
            
            File f = new File("src/images/plus.png");
            Image i = new Image(f.toURI().toString());
            p_def.setImage(i);
            p_dex.setImage(i);
            p_slightOfHand.setImage(i);
            p_knowledge.setImage(i);
            p_lightFooted.setImage(i);
            p_magic.setImage(i);
            p_vita.setImage(i);
            p_per.setImage(i);
            p_rhetoric.setImage(i);
            p_str.setImage(i);
            p_survivalism.setImage(i);
            
            setLevelUpButtonsVisible(false);
      }
      
      void setLevelUpButtonsVisible(boolean visible) {
            p_def.setVisible(visible);
            p_dex.setVisible(visible);
            p_slightOfHand.setVisible(visible);
            p_knowledge.setVisible(visible);
            p_lightFooted.setVisible(visible);
            p_magic.setVisible(visible);
            p_vita.setVisible(visible);
            p_per.setVisible(visible);
            p_rhetoric.setVisible(visible);
            p_str.setVisible(visible);
            p_survivalism.setVisible(visible);
      }
      
      void setImageForInventorySlot(ImageView _slot, String _path) {
            File f = new File(_path);
            Image i = new Image(f.toURI().toString());
            _slot.setImage(i);
      }
}
