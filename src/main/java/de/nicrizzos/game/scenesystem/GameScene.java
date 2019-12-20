package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.dialoguesystem.*;
import de.nicrizzos.game.entities.*;

import java.util.ArrayList;

public class GameScene {
      protected String description;
      protected ArrayList<GameObject> objects;
      protected ArrayList<NPC> characters;
      protected ArrayList<Enemy> enemies;
      protected ArrayList<Dialogue> dialogues;
      
      public GameScene (String _desc, ArrayList<GameObject> _objects, ArrayList<NPC> _characters, ArrayList<Enemy> _enemies, ArrayList<Dialogue> _dialogues) {
            this.description = _desc;
            this.objects = _objects;
            this.characters = _characters;
            this.enemies = _enemies;
            this.dialogues = _dialogues;
      }
      
      public String startScene() {
            return description;
      }
}
