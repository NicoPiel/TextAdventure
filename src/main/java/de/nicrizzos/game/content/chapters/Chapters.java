package de.nicrizzos.game.content.chapters;

import de.nicrizzos.game.exceptions.GameException;
import de.nicrizzos.game.scenesystem.Chapter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Container for all the game's chapters (i.e. the XML structure).
 */
public class Chapters {
      
      /**
       * A list of all the chapters.
       */
      static ArrayList<Chapter> chapters;
      
      /**
       * Call this to construct an internal structure of the 'scenes.xml'
       * @see Chapter#constructChapterFromFile(int) 
       */
      public static void createChapters() {
            System.out.println("Creating chapters..");
            // The path to the XML file containing the scene and chapter data.
            final String filename = "src/main/java/de/nicrizzos/game/content/chapters/scenes.xml";
            Document doc;
            
            // The list to save those chapters in.
            chapters = new ArrayList<>();
            
            try {
                  // Build a new XML reader.
                  doc = new SAXBuilder().build(filename);
      
                  // Retrieve the root element, we'll work from there.
                  Element root = doc.getRootElement();
      
                  // Get all chapters.
                  List<Element> chaptersList = root.getChildren();
                  
                  for (Element e : chaptersList) {
                        // Retrieve the <Chapter> tags and build the internal chapter structure.
                        Chapter chapter = Chapter.constructChapterFromFile(Integer.parseInt(e.getAttributeValue("id")));
                        // Add the chapter to the static list in this class.
                        chapters.add(chapter);
                  }
                  
                  
            }
            catch (GameException e) {
                  System.out.println(e.getMessage());
            } catch (IOException e) {
                  System.err.println("There was a problem opening the file '" + filename + "'.");
                  e.printStackTrace();
            } catch (JDOMException e) {
                  System.err.println("JDOM ran into a problem.");
                  e.printStackTrace();
            }
            
            System.out.println("Done creating chapters..");
      }
      
      /**
       * @return The chapter list.
       */
      public static ArrayList<Chapter> getChapters() {
            return chapters;
      }
      
      /**
       * Gets you a chapter by the list's index.
       * @param _index The index of the chapter to look for.
       * @return A chapter object.
       */
      public static Chapter getChapterByIndex (int _index) {
            try {
                  return getChapters().get(_index);
            }
            catch (NullPointerException e) {
                  System.err.println("The chapter list might be empty.");
                  e.printStackTrace();
            }
            catch (IndexOutOfBoundsException e) {
                  e.printStackTrace();
            }
            
            return null;
      }
}