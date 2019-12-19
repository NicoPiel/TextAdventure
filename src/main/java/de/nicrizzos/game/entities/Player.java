package de.nicrizzos.game.entities;

/**
 * Represents the player object. It contains all the necessary fields and methods to create and maintain a player object.
 *
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
       * The player's current health
       */
      private int currentHealth;
      
      /**
       * The player's mana - how many spells they can use.
       */
      private int mana;
      
      /**
       * The player's current mana
       */
      private int currentMana;
      
      /**
       * The player's strength - influences their damage with melee weaponry.
       */
      private int strength;
      
      /**
       * The player's dexterity - influences their damage with ranged weaponry.
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
      
      /**
       * The player's defense - how much damage the player can absorb before it will affect their hit points.
       */
      private int defense;
      
      /**
       * Influences rolls on stuff like sneaking. Unlocks certain dialogue options.
       */
      private int lightFooted;
      
      /**
       * Influences rolls on stuff like theft and lock-picking. Unlocks certain dialogue options.
       */
      private int slightOfHand;
      
      /**
       * Influences rolls on observing your environment. Unlocks certain dialogue options.
       */
      private int perception;
      
      /**
       * Influences rolls on stuff related to surviving or nature. Unlocks certain dialogue options.
       */
      private int survivalism;
      
      /**
       * Influences rolls on stuff like the arcane, books or general knowledge. Unlocks certain dialogue options.´
       */
      private int knowledge;
      
      /**
       * Influences the player's ability to persuade other people. Unlocks certain dialogue options.´
       */
      private int rhetoric;
      
      
      /**
       * Whether the player had their initial values set.
       */
      private boolean created;
      
      /**
       * Whether the requirements for a level-up are met.
       */
      private boolean canLevelUp;
      
      
      /**
       * Creates a new player object with the given name and the id "player".
       *
       * @param _name The player's name to be used in various ways.
       */
      public Player(String _name) {
            super(_name, "player");
            this.created = false;
      }
      
      /**
       * Use this to first set the player's stats. Cannot be called a second time.
       */
      public void createPlayer(String _name) {
            if (!created) {
                  setPlayerName(_name);
                  setLevel(1);
                  setHealth(100);
                  setCurrentHealth(this.getHealth());
                  setMana(10);
                  setCurrentMana(this.getMana());
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
                  setExperienceRequiredForNextLevel();
                  this.created = true;
            } else {
                  System.err.println("Player already created.");
            }
      }
      
      /**
       * Use this function to level-up.
       */
      public void levelUp() {
            this.level++;
            this.setCanLevelUp(true);
            setCanLevelUp(true);
            setExperience(0);
            setExperienceRequiredForNextLevel();
      }
      
      /**
       * Use this to increase certain stats by 1
       *
       * @param _stat Use stat in lowercase, e.g. "strength", "lightFooted", "slightOfHand"
       */
      public void increaseStat(String _stat) {
            switch (_stat) {
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
            
            setCanLevelUp(false);
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
      
      public int getCurrentMana() {
            return this.currentMana;
      }
      
      public int getHealth() {
            return this.health;
      }
      
      public int getCurrentHealth() {
            return this.currentHealth;
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
      
      public double getHealthPercentage () {
            return ((double) this.getCurrentHealth()/this.getHealth());
      }
      
      public double getManaPercentage () {
            return ((double) this.getCurrentMana()/this.getMana());
      }
      
      public double getExperiencePercentage() {
            return (double) getExperience()/getExperienceRequiredForNextLevel();
      }
      
      @Override
      public boolean isPlayer() {
            return true;
      }
      
      public boolean canLevelUp() {
            return this.canLevelUp;
      }
      
      // -------------------- SETTERS --------------------
      
      public void setPlayerName (String _name) {
            if (this.getName().equals("Nameless")) {
                  this.name = _name;
            }
            else {
                  System.err.println("You can't change the player's name.");
            }
      }
      
      public void setLevel (int _level) {
            this.level = _level;
      }
      
      public void setMana(int _mana) {
            this.mana = _mana;
      }
      
      public void setCurrentMana(int _mana) {
            this.currentMana = _mana;
      }
      
      public void setHealth(int _health) {
            this.health = _health;
      }
      
      public void setCurrentHealth(int _health) {
            this.currentHealth = _health;
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
      
      
      public void setExperience(int _experience) {
            this.experience = _experience;
      }

      public void addExperience(int _experience) {
            this.experience += _experience;
            
            if (this.experience >= this.experienceRequiredForNextLevel) {
                  levelUp();
            }
      }
      
      public void setExperienceRequiredForNextLevel() {
            this.experienceRequiredForNextLevel = 500 + ((this.level - 1) * 500);
      }
      
      public void setCanLevelUp(boolean canLevelUp) {
            this.canLevelUp = canLevelUp;
      }
}
