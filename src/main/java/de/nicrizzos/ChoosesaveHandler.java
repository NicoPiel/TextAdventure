package de.nicrizzos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class ChoosesaveHandler {
      @FXML
      public void btn_save1(ActionEvent e) throws IOException {


            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("mainscene.fxml"));
            Parent switchscene = fxmlLoader.load();
            Scene sc = new Scene(switchscene);
            MainsceneHandler maincontroller = fxmlLoader.getController();
            maincontroller.Init(askForName());
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
