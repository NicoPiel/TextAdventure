package de.nicrizzos.game.content.items;

import de.nicrizzos.game.exceptions.GameException;
import de.nicrizzos.game.itemsystem.Armor;
import de.nicrizzos.game.itemsystem.Item;
import de.nicrizzos.game.itemsystem.Weapon;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Items {
      static ArrayList<Item> items;
      
      public static void createChapters() {
            System.out.println("Creating items..");
            // The path to the XML file containing the scene and chapter data.
            final String filename = "src/main/java/de/nicrizzos/game/content/items/items.xml";
            Document doc;
            
            // The list to save those chapters in.
            items = new ArrayList<>();
            
            try {
                  // Build a new XML reader.
                  doc = new SAXBuilder().build(filename);
                  
                  // Retrieve the root element, we'll work from there.
                  Element root = doc.getRootElement();
                  
                  // Get all chapters.
                  List<Element> itemList = root.getChildren();
                  
                  for (Element e : itemList) {
                        if (e.getName().equals("Weapon")) {
                        
                        }
                  }
                  
                  
            } catch (IOException e) {
                  System.err.println("There was a problem opening the file '" + filename + "'.");
                  e.printStackTrace();
            } catch (JDOMException e) {
                  System.err.println("JDOM ran into a problem.");
                  e.printStackTrace();
            }
            
            System.out.println("Done creating chapters..");
      }
      
      Weapon constructWeaponFromFile() {
            return null;
      }
      
      Armor constructArmorFromFile() {
            return null;
      }
}
