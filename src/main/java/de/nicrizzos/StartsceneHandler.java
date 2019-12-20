package de.nicrizzos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class StartsceneHandler {

      @FXML
      public void onGameStartButtonClick(ActionEvent e) throws IOException {

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("choosesave.fxml"));
            Parent switchscene = fxmlLoader.load();
            Scene sc = new Scene(switchscene);
            ChoosesaveHandler csh = fxmlLoader.getController();
            csh.Init();
            Stage stageTheEventSourceNodeBelongs = (Stage) ((Node)e.getSource()).getScene().getWindow();
            stageTheEventSourceNodeBelongs.setScene(sc);

      }
}
