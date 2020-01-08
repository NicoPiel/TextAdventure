package de.nicrizzos.game.content.items;

import de.nicrizzos.game.exceptions.GameException;
import de.nicrizzos.game.itemsystem.Armor;
import de.nicrizzos.game.itemsystem.Item;
import de.nicrizzos.game.itemsystem.QuestItem;
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
      
      public static void createItems() throws GameException {
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
                  
                  // Get all items.
                  List<Element> itemList = root.getChildren();
                  
                  for (Element e : itemList) {
                        switch (e.getName()) {
                              case "QuestItems" -> {
                                    for (Element q : e.getChildren()) {
                                          QuestItem newItem = constructQuestItemFromFile(q);
                                          addItem(newItem);
                                    }
                              }
                              case "Weapons" -> {
                                    for (Element w : e.getChildren()) {
                                          Weapon newWeapon = constructWeaponFromFile(w);
                                          addItem(newWeapon);
                                    }
                              }
                              case "Armors" -> {
                                    for (Element a : e.getChildren()) {
                                          Armor newArmor = constructArmorFromFile(a);
                                          addItem(newArmor);
                                    }
                              }
                              default -> throw new GameException("Unknown item type.");
                        }
                  }
                  
                  System.out.println("Done adding items..");
            } catch (IOException e) {
                  System.err.println("There was a problem opening the file '" + filename + "'.");
                  e.printStackTrace();
            } catch (JDOMException e) {
                  System.err.println("JDOM ran into a problem.");
                  e.printStackTrace();
            }
            
            System.out.println("Done creating chapters..");
      }
      
      private static QuestItem constructQuestItemFromFile(Element _item) {
            String name = _item.getAttributeValue("name");
            String uniqueID = _item.getAttributeValue("id");
            String description = _item.getText();
            
            return new QuestItem(name, uniqueID, description);
      }
      
      private static Weapon constructWeaponFromFile(Element _weapon) {
            String name = _weapon.getAttributeValue("name");
            String uniqueID = _weapon.getAttributeValue("id");
            String description = _weapon.getText();
            int rawDamage = Integer.parseInt(_weapon.getAttributeValue("rawDamage"));
            int itemLevel = Integer.parseInt(_weapon.getAttributeValue("itemLevel"));
            
            return new Weapon(name, uniqueID, description, rawDamage, itemLevel);
      }
      
      private static Armor constructArmorFromFile(Element _armor) {
            String name = _armor.getAttributeValue("name");
            String uniqueID = _armor.getAttributeValue("id");
            String slot = _armor.getAttributeValue("slot");
            String description = _armor.getText();
            int rawDefense = Integer.parseInt(_armor.getAttributeValue("rawDefense"));
            int itemLevel = Integer.parseInt(_armor.getAttributeValue("itemLevel"));
            
            return new Armor(name, uniqueID, description, slot, rawDefense, itemLevel);
      }
      
      public static Item getItemByID(String _id) {
            if (!hasItem(_id)) {
                  return null;
            }
            
            for (Item item : items) {
                  if (item.getUniqueID().equals(_id)) {
                        return item;
                  }
            }
            
            return null;
      }
      
      public static boolean hasItem(String _id) {
            for (Item item : items) {
                  if (item.getUniqueID().equals(_id)) {
                        return true;
                  }
            }
            
            return false;
      }
      
      public static void addItem(Item _item) {
            if (!hasItem(_item.getUniqueID())) {
                  items.add(_item);
                  System.out.println("Added " + _item.getName());
            }
            else System.err.println(_item.getName() + " ist bereits vorhanden.");
      }
      
      public static ArrayList<Item> getItems() {
            return items;
      }
}
