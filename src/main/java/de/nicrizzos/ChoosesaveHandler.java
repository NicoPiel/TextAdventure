package de.nicrizzos;

import de.nicrizzos.game.utils.SQLiteManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ChoosesaveHandler {
      SQLiteManager save1;
      SQLiteManager save2;
      SQLiteManager save3;
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
            save1 = new SQLiteManager(1);
            save2 = new SQLiteManager(2);
            save3 = new SQLiteManager(3);



            checkButtons();
      }
      public void checkButtons() {
            save1.startSQL();
            if(save1.checkSlot()) {
                  btn_slot1.setText(save1.getPlayerName());
                  btn_delete1.setVisible(true);
            }
            save2.startSQL();
            if(save2.checkSlot()) {
                  btn_slot2.setText(save2.getPlayerName());
                  btn_delete2.setVisible(true);
            }
            save3.startSQL();
            if(save3.checkSlot()) {
                  btn_slot3.setText(save3.getPlayerName());
                  btn_delete3.setVisible(true);
            }
            save1.stopSQL();
            save2.stopSQL();
            save3.stopSQL();

      }
      @FXML
      public void 

      @FXML
      public void btn_save1 (ActionEvent e) throws IOException {
                  FXMLLoader fxmlLoader = new FXMLLoader();
                  fxmlLoader.setLocation(getClass().getResource("mainscene.fxml"));
                  Parent switchscene = fxmlLoader.load();
                  Scene sc = new Scene(switchscene);
                  MainsceneHandler maincontroller = fxmlLoader.getController();
                  save1.startSQL();
                  if(!save1.checkSlot()) {
                        String name = askForName();
                        save1.setPlayer(name);
                        maincontroller.Init(askForName());
                  }else{
                        maincontroller.Init(1,save1.getPlayerName());
                  }
                  save1.stopSQL();

                  Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
                  stageTheEventSourceNodeBelongs.setScene(sc);
      }
      @FXML
      public void btn_save2 (ActionEvent e) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainscene.fxml"));
            Parent switchscene = fxmlLoader.load();
            Scene sc = new Scene(switchscene);
            MainsceneHandler maincontroller = fxmlLoader.getController();
            save2.startSQL();
            if(!save2.checkSlot()) {
                  String name = askForName();
                  save2.setPlayer(name);
                  maincontroller.Init(askForName());
            }else{
                  maincontroller.Init(2,save2.getPlayerName());
            }
            save2.stopSQL();

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(sc);
      }
      @FXML
      public void btn_save3 (ActionEvent e) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainscene.fxml"));
            Parent switchscene = fxmlLoader.load();
            Scene sc = new Scene(switchscene);
            MainsceneHandler maincontroller = fxmlLoader.getController();
            save3.startSQL();
            if(!save3.checkSlot()) {
                  String name = askForName();
                  save3.setPlayer(name);
                  maincontroller.Init(name);
            }else{
                  maincontroller.Init(3,save3.getPlayerName());
            }
            save3.stopSQL();

            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(sc);
      }

      public String askForName() {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setHeaderText("Nenne uns deinen Namen");
            dialog.setContentText("Name:");
            Optional<String> result = dialog.showAndWait();
            if (result.isPresent()){
                  return result.get();
            }
            return null;

      }
}
