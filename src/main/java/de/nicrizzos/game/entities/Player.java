package de.nicrizzos.game.entities;

/**
 * Represents the player object. It contains all the necessary fields and methods to create and maintain a player object.
 * @see Entity
 */
public class Player extends Entity {
      /**
       * The player's level.
       */
      private int level;
      
      /**
       * The player's progress to the next level.
       */
      private int experience;
      
      /**
       * The experience required to reach the next level, as dictated by exp = 500 + (lvl - 1) * 500
       */
      private int experienceRequiredForNextLevel;
      
      /**
       * The player's hit points - how much damage they can take before dying.
       */
      private int health;
      
      /**
       * The player's mana - how many spells they can use.
       */
      private int mana;
      
      /**
       * The player's strength - influences their damage with melee weaponry.
       */
      private int strength;
      
      /**
       * The player's dexterity - influences their damage with ranged weapons.
       */
      private int dexterity;
      
      /**
       * The player's magical prowess - influences their damage with spells and increases mana.
       */
      private int magic;
      
      /**
       * The player's vitality - increases the player's hit points.
       */
      private int vitality;
      private int defense;
      private int lightFooted;
      private int slightOfHand;
      private int perception;
      private int survivalism;
      private int knowledge;
      private int rhetoric;
      
      private boolean created;
      private boolean canLevelUp;
      
      public Player(String _name) {
            this.identification = "player";
            this.name = _name;
            this.created = false;
      }
      
      public void createPlayer() {
            if (!created) {
                  setHealth(100);
                  setMana(10);
                  setStrength(1);
                  setDexterity(1);
                  setMagic(1);
                  setVitality(0);
                  setDefense(0);
                  setLightFooted(0);
                  setSlightOfHand(0);
                  setPerception(0);
                  setSurvivalism(0);
                  setKnowledge(0);
                  setRhetoric(0);
                  this.created = true;
            } else {
                  System.err.println("Player already created.");
            }
      }
      
      public void levelUp() {
            this.level++;
            setExperienceRequiredForNextLevel();
      }
      
      public void increaseStat(String stat) {
            switch (stat) {
                  case "strength" -> this.strength++;
                  case "dexterity" -> this.dexterity++;
                  case "magic" -> {
                        this.magic++;
                        this.mana = 10 + (this.magic * 10);
                  }
                  case "vitality" -> {
                        this.vitality++;
                        this.health = 100 + (this.vitality * 20);
                  }
                  case "defense" -> this.defense++;
                  case "lightFooted" -> this.lightFooted++;
                  case "slightOfHand" -> this.slightOfHand++;
                  case "perception" -> this.perception++;
                  case "survivalism" -> this.survivalism++;
                  case "knowledge" -> this.knowledge++;
                  case "rhetoric" -> this.rhetoric++;
            }
      }
      
      // -------------------- GETTERS --------------------
      
      public int getLevel() {
            return this.level;
      }
      
      public int getExperience() {
            return this.experience;
      }
      
      public int getExperienceRequiredForNextLevel() {
            return this.experienceRequiredForNextLevel;
      }
      
      public int getMana() {
            return this.mana;
      }
      
      public int getHealth() {
            return this.health;
      }
      
      public int getStrength() {
            return this.strength;
      }
      
      public int getDexterity() {
            return this.dexterity;
      }
      
      public int getMagic() {
            return this.magic;
      }
      
      public int getVitality() {
            return this.vitality;
      }
      
      public int getDefense() {
            return this.defense;
      }
      
      public int getLightFooted() {
            return this.lightFooted;
      }
      
      public int getSlightOfHand() {
            return this.slightOfHand;
      }
      
      public int getPerception() {
            return this.perception;
      }
      
      public int getSurvivalism() {
            return this.survivalism;
      }
      
      public int getKnowledge() {
            return this.knowledge;
      }
      
      public int getRhetoric() {
            return this.rhetoric;
      }
      
      @Override
      public boolean isPlayer() {
            return true;
      }
      
      public boolean canLevelUp() {
            return this.canLevelUp;
      }
      
      // -------------------- SETTERS --------------------
      
      
      public void setMana(int _mana) {
            this.mana = _mana;
      }
      
      
      public void setHealth(int _health) {
            this.health = _health;
      }
      
      
      public void setStrength(int _strength) {
            this.strength = _strength;
      }
      
      
      public void setDexterity(int _dexterity) {
            this.dexterity = _dexterity;
      }
      
      
      public void setMagic(int _magic) {
            this.magic = _magic;
      }
      
      
      public void setVitality(int _vitality) {
            this.vitality = _vitality;
      }
      
      
      public void setDefense(int _defense) {
            this.defense = _defense;
      }
      
      
      public void setLightFooted(int _lightFooted) {
            this.lightFooted = _lightFooted;
      }
      
      
      public void setSlightOfHand(int _slightOfHand) {
            this.slightOfHand = _slightOfHand;
      }
      
      
      public void setPerception(int _perception) {
            this.perception = _perception;
      }
      
      
      public void setSurvivalism(int _survivalism) {
            this.survivalism = _survivalism;
      }
      
      
      public void setKnowledge(int _knowledge) {
            this.knowledge = _knowledge;
      }
      
      
      public void setRhetoric(int _rhetoric) {
            this.rhetoric = _rhetoric;
      }
      
      
      public void setLevel(int _level) {
            this.level = _level;
      }
      
      
      public void setExperience(int _experience) {
            this.experience = _experience;
      }
      
      
      public void setExperienceRequiredForNextLevel() {
            this.experienceRequiredForNextLevel = 500 + ((this.level - 1) * 500);
      }
      
      public void setCanLevelUp(boolean canLevelUp) {
            this.canLevelUp = canLevelUp;
      }
}
