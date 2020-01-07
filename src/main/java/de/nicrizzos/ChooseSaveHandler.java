package de.nicrizzos;

import de.nicrizzos.game.utils.SQLiteManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

/**
 * Handles loading everything
 *
 * @author Nico Piel
 * @version 1.0
 */
public class ChooseSaveHandler {
      @FXML
      private AnchorPane ap_save;
      @FXML
      private Button btn_slot1;
      @FXML
      private Button btn_slot2;
      @FXML
      private Button btn_slot3;
      @FXML
      private Button btn_delete1;
      @FXML
      private Button btn_delete2;
      @FXML
      private Button btn_delete3;
      
      public void Init() {
            this.checkButtons();
      }
      
      public void checkButtons() {
            SQLiteManager save1 = new SQLiteManager(1);
            save1.startSQL();
            if (save1.checkIfPlayerExists()) {
                  btn_slot1.setText(save1.getPlayerName());
                  btn_delete1.setVisible(true);
            } else {
                  btn_slot1.setText("New save");
                  btn_delete1.setVisible(false);
            }
            save1.stopSQL();
            
            
            SQLiteManager save2 = new SQLiteManager(2);
            save2.startSQL();
            if (save2.checkIfPlayerExists()) {
                  btn_slot2.setText(save2.getPlayerName());
                  btn_delete2.setVisible(true);
            } else {
                  btn_slot2.setText("New save");
                  btn_delete2.setVisible(false);
            }
            save2.stopSQL();
            SQLiteManager save3 = new SQLiteManager(3);
            save3.startSQL();
            if (save3.checkIfPlayerExists()) {
                  btn_slot3.setText(save3.getPlayerName());
                  btn_delete3.setVisible(true);
            } else {
                  btn_slot3.setText("New save");
                  btn_delete3.setVisible(false);
            }
            save3.stopSQL();
            
      }
      
      @FXML
      public void btn_delete(ActionEvent e) {
            Button button = (Button) e.getSource();
            int slot = 0;
            
            
            switch (button.getId()) {
                  case "btn_delete1" -> {
                        SQLiteManager save1 = new SQLiteManager(1);
                        save1.startSQL();
                        save1.deleteSave();
                        save1.stopSQL();
                        
                        slot = 1;
                  }
                  case "btn_delete2" -> {
                        SQLiteManager save2 = new SQLiteManager(2);
                        save2.startSQL();
                        save2.deleteSave();
                        save2.stopSQL();
                        
                        slot = 2;
                  }
                  case "btn_delete3" -> {
                        SQLiteManager save3 = new SQLiteManager(3);
                        save3.startSQL();
                        save3.deleteSave();
                        save3.stopSQL();
                        
                        slot = 3;
                  }
            }
            
            System.out.println("Save " + slot + " deleted.");
            this.Init();
            
            
      }
      
      @FXML
      void btn_save(ActionEvent e) throws IOException {
            Button button = (Button) e.getSource();
            SQLiteManager save = null;
            int slot = 0;
            
            switch (button.getId()) {
                  case "btn_slot1" -> {
                        System.out.println("Slot 1 selected..");
                        slot = 1;
                        save = new SQLiteManager(slot);
                        
                        
                  }
                  case "btn_slot2" -> {
                        System.out.println("Slot 2 selected..");
                        slot = 2;
                        save = new SQLiteManager(slot);
                        
                        
                  }
                  case "btn_slot3" -> {
                        System.out.println("Slot 3 selected..");
                        slot = 3;
                        save = new SQLiteManager(slot);
                        
                        
                  }
            }
            
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainscene.fxml"));
            Parent switchscene = fxmlLoader.load();
            Scene sc = new Scene(switchscene);
            MainSceneHandler mainScreen = fxmlLoader.getController();
            
            
            if (save != null) {
                  save.startSQL();
                  if (!save.checkIfPlayerExists()) {
                        String name = askForName();
                        save.setPlayer(name);
                        save.stopSQL();
                        showLoadingScreen();
                        mainScreen.Init(slot, LoadingScreenHandler.newGame(name, save), save);
                  } else {
                        showLoadingScreen();
                        mainScreen.Init(slot, LoadingScreenHandler.loadGame(save), save, true);
                        save.stopSQL();
                  }
            } else System.out.println("Save stimmt nicht.");
            
            try {
                  Thread.sleep(3000);
            } catch (InterruptedException ex) {
                  ex.printStackTrace();
            }
            
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(sc);
      }
      
      public String askForName() {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setHeaderText("Nenne uns deinen Namen");
            dialog.setContentText("Name:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()) {
                  return result.get();
            }
            return null;
      }
      
      private void showLoadingScreen() {
            btn_slot1.setVisible(false);
            btn_slot2.setVisible(false);
            btn_slot3.setVisible(false);
            btn_delete1.setVisible(false);
            btn_delete2.setVisible(false);
            btn_delete3.setVisible(false);
            
            Label label = new Label("Loading..");
            label.setFont(new Font(25));
            label.setLayoutX(230);
            label.setLayoutY(170);
            ap_save.getChildren().add(label);
      }
}
