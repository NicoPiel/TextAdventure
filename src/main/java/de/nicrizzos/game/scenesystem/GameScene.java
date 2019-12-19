package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.dialoguesystem.Dialogue;
import de.nicrizzos.game.entities.Entity;
import de.nicrizzos.game.entities.GameObject;

import java.util.ArrayList;

public class GameScene {
      protected String description;
      protected ArrayList<GameObject> objects;
      protected ArrayList<Entity> characters;
      protected ArrayList<Entity> enemies;
      protected ArrayList<Dialogue> dialogues;
      
      public GameScene () {
      
      }
}
