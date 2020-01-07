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
                        if (e.getName().equals("Weapon")) {
                              Weapon newWeapon = constructWeaponFromFile(e);
                              addItem(newWeapon);
                              System.out.println("Added " + newWeapon.getName());
                        }
                        else if (e.getName().equals("Armor")) {
                              Armor newArmor = constructArmorFromFile(e);
                              addItem(newArmor);
                              System.out.println("Added " + newArmor.getName());
                        }
                        else throw new GameException("Unknown item type.");
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
      
      private static Weapon constructWeaponFromFile (Element weapon) {
            String name = weapon.getAttributeValue("name");
            String uniqueID = weapon.getAttributeValue("id");
            int rawDamage = Integer.parseInt(weapon.getAttributeValue("rawDamage"));
            int itemLevel = Integer.parseInt(weapon.getAttributeValue("itemLevel"));
            
            return new Weapon(name, uniqueID, rawDamage, itemLevel);
      }
      
      private static Armor constructArmorFromFile(Element armor) {
            String name = armor.getAttributeValue("name");
            String uniqueID = armor.getAttributeValue("id");
            String slot = armor.getAttributeValue("slot");
            int rawDefense = Integer.parseInt(armor.getAttributeValue("rawDefense"));
            int itemLevel = Integer.parseInt(armor.getAttributeValue("itemLevel"));
            
            return new Armor(name, uniqueID, slot, rawDefense, itemLevel);
      }
      
      public static Item getItemByID (String _id) {
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
      
      public static void addItem (Item _item) {
            items.add(_item);
      }
      
      public static ArrayList<Item> getItems() {
            return items;
      }
}
