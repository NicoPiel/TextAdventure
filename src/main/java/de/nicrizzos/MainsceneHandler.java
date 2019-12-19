package de.nicrizzos;

import de.nicrizzos.game.entities.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class MainsceneHandler {
      @FXML
      private TextArea ta_game;

      @FXML
      private ProgressBar pgb_life;

      @FXML
      private Label lbl_life;

      @FXML
      private Label lbl_mana;

      @FXML
      private ProgressBar pgb_mana;

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

      Player p;

      public void Init(String _name){
            p = new Player(_name);
            constructCharInv();
            p.createPlayer();
            p.setCurrentMana(5);
            setPlayerInformation();
      }

      public void setPlayerInformation() {
            lbl_life.setText(p.getCurrentHealth() + " / " + p.getHealth());
            pgb_life.setProgress((double) p.getCurrentHealth() / p.getHealth());
            lbl_mana.setText(p.getCurrentMana() + " / " + p.getMana());
            pgb_mana.setProgress((double) p.getCurrentMana() / p.getMana());
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
