package de.nicrizzos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Handles the main menu.
 * @author Pascal Staadt
 * @version 1.0
 */
public class StartSceneHandler {

      @FXML
      public void onGameStartButtonClick(ActionEvent e) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("choosesave.fxml"));
            Parent switchscene = fxmlLoader.load();
            Scene sc = new Scene(switchscene);
            ChooseSaveHandler csh = fxmlLoader.getController();
            csh.Init();
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(sc);

      }
      @FXML
      public void onStartSceneBuilderStartButtonClick(ActionEvent e) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("scenecreator.fxml"));
            Parent switchscene = fxmlLoader.load();
            Scene sc = new Scene(switchscene);
            SceneCreator csh = fxmlLoader.getController();
            csh.Init();
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(sc);
      }
}
