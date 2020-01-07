package de.nicrizzos;

import de.nicrizzos.game.content.chapters.Chapters;
import de.nicrizzos.game.exceptions.GameException;
import de.nicrizzos.game.scenesystem.Chapter;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SceneCreator {
      final String filename = "src/main/java/de/nicrizzos/game/content/chapters/scenes.xml";
      Document doc;
      @FXML
      private AnchorPane ap_main;

      public void Init() throws IOException {
            try {
                  doc = new SAXBuilder().build(filename);
            }
            catch (JDOMException edom) {
                  edom.printStackTrace();
            }

            Element rootNode = doc.getRootElement();
            List<Element> chapters = rootNode.getChildren();

            TabPane main = new TabPane();
            main.setPrefHeight(580);
            main.setPrefWidth(770);

            for (Element e : chapters) {
                  Tab tab1 = new Tab(e.getAttributeValue("name"));
                  tab1.setClosable(false);
                  AnchorPane anchorpane = new AnchorPane();
                  if (e.getChildren() != null) {
                        List<Element> scenes = e.getChildren();
                        int x = 20;
                        int y = 20;
                        for(Element sc : scenes) {
                              Button btn = new Button(sc.getAttributeValue("id"));
                              btn.setPrefHeight(40);
                              btn.setPrefWidth(80);
                              btn.setLayoutX(x);
                              btn.setLayoutY(y);
                              x += 100;
                              anchorpane.getChildren().add(btn);

                        }
                        TextArea txt = new TextArea();
                        txt.setPrefSize(500,350);
                        txt.setLayoutX(20);
                        txt.setLayoutY(80);
                        anchorpane.getChildren().add(txt);

                        List<Element> textList = doc
                                .getRootElement()
                                .getChild("Chapter")
                                .getChild("Scene")
                                .getChild("Head")
                                .getChild("Texts")
                                .getChildren("Text");

                        StringBuilder builder = new StringBuilder();

                        for (Element text : textList) {
                              builder.append(text.getText());
                              builder.append("\n");
                        }

                        txt.setText(builder.toString());
                  }
                  tab1.setContent(anchorpane);
                  main.getTabs().add(tab1);
            }

            ap_main.getChildren().add(main);

      }


}