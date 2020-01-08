package de.nicrizzos.game.entities;

import de.nicrizzos.game.exceptions.GameException;
import de.nicrizzos.game.itemsystem.Armor;
import de.nicrizzos.game.itemsystem.Item;
import de.nicrizzos.game.itemsystem.Weapon;

import java.util.ArrayList;

/**
 * Represents the player object. It contains all the necessary fields and methods to create and maintain a player object.
 * @author Nico Piel
 * @version 1.1
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
       * The experience required to reach the next level, as dictated by 'exp = 500 + (lvl - 1) * 500'
       */
      private int experienceRequiredForNextLevel;
      
      /**
       * The player's mana - how many spells they can use.
       */
      private int mana;
      
      /**
       * The player's current mana
       */
      private int currentMana;
      
      /**
       * The player's strength - influences their damage with melee weaponry. Currently, 7% damage bonus per point.
       */
      private int strength;
      
      /**
       * The player's dexterity - influences their damage with ranged weaponry. Currently, 7% damage bonus per point.
       */
      private int dexterity;
      
      /**
       * The player's magical prowess - influences their damage with spells and increases mana. Currently, 7% damage bonus and 10 extra mana per point.
       */
      private int magic;
      
      /**
       * The player's vitality - increases the player's hit points. Currently, 20 hit-points per point.
       */
      private int vitality;
      
      /**
       * The player's defense - how much damage the player can absorb before it will affect their hit points. Currently, 7% defense bonus per point.
       */
      private int defensiveness;
      
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
       * These can be invested in advancing skills and base stats.
       */
      private int skillPoints;
      
      // Prototype inventory
      
      ArrayList<Item> inventory;
      
      private int weaponDamage;
      private int shieldDef;
      private int helmetDef;
      private int chestDef;
      private int pantsDef;
      private int bootsDef;
      private int gauntletsDef;
      
      
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
      public void createPlayer(String _name) throws GameException {
            if (!created) {
                  inventory = new ArrayList<>();
                  
                  weaponDamage = 0;
                  shieldDef = 0;
                  helmetDef = 0;
                  chestDef = 0;
                  pantsDef = 0;
                  bootsDef = 0;
                  gauntletsDef = 0;
                  
                  // Player stats init
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
                  setDefensiveness(0);
                  setLightFooted(0);
                  setSlightOfHand(0);
                  setPerception(0);
                  setSurvivalism(0);
                  setKnowledge(0);
                  setRhetoric(0);
                  setExperienceRequiredForNextLevel();
                  this.created = true;
            } else {
                  throw new GameException("Player already created. Something went horribly wrong.");
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
            
            addSkillPoints(1);
      }
      
      /**
       * Use this to increase certain stats by 1
       *
       * @param _stat Use stat in lowercase, e.g. "strength", "lightFooted", "slightOfHand"
       */
      public void increaseStat(String _stat) {
            if (skillPoints > 0) {
                  switch (_stat) {
                        case "strength" -> this.strength++;
                        case "dexterity" -> this.dexterity++;
                        case "magic" -> {
                              this.magic++;
                              this.mana = 10 + (this.magic * 10);
                              setCurrentMana(getMana());
                        }
                        case "vitality" -> {
                              this.vitality++;
                              this.health = 100 + (this.vitality * 20);
                              setCurrentHealth(getHealth());
                        }
                        case "defense" -> this.defensiveness++;
                        case "lightFooted" -> this.lightFooted++;
                        case "slightOfHand" -> this.slightOfHand++;
                        case "perception" -> this.perception++;
                        case "survivalism" -> this.survivalism++;
                        case "knowledge" -> this.knowledge++;
                        case "rhetoric" -> this.rhetoric++;
                  }
                  
                  skillPoints--;
            }
            
            if (skillPoints == 0) setCanLevelUp(false);
      }
      
      // -------------------- INVENTORY --------------------
      
      private Weapon weapon;
      private Armor helmet;
      private Armor chest;
      private Armor pants;
      private Armor boots;
      private Armor gauntlets;
      
      
      public void equipItem (Item _item) throws GameException {
            if (_item instanceof Weapon) {
                  this.weapon = (Weapon) _item;
            }
            else if (_item instanceof Armor) {
                  switch (((Armor) _item).getSlot().toLowerCase()) {
                        case "helmet" -> {
                              this.helmet = (Armor) _item;
                        }
                        case "chest" -> {
                              this.chest = (Armor) _item;
                        }
                        case "pants" -> {
                              this.pants = (Armor) _item;
                        }
                        case "boots" -> {
                              this.boots = (Armor) _item;
                        }
                        case "gauntlets" -> {
                              this.gauntlets = (Armor) _item;
                        }
                        default -> {
                              throw new GameException("An armor item seems to have an incorrect slot.");
                        }
                  }
            }
            
            setEquipmentStats();
      }
      
      private void setEquipmentStats() {
            if (weapon != null) weaponDamage = weapon.getDamageValue();
            if (helmet != null) helmetDef = helmet.getDefenseValue();
            if (chest != null) chestDef = chest.getDefenseValue();
            if (pants != null) pantsDef = pants.getDefenseValue();
            if (boots != null) bootsDef = boots.getDefenseValue();
            if (gauntlets != null) gauntletsDef = gauntlets.getDefenseValue();
      }
      
      public void addToInventory (Item _item) {
            this.inventory.add(_item);
            System.out.println("Added " + _item.getName() + " to inventory.");
      }
      
      public void removeFromInventory (Item _item) {
            this.inventory.remove(_item);
            System.out.println("Removed " + _item.getName() + " from inventory.");
      }
      
      public Weapon getWeapon() {
            return weapon;
      }
      
      public Armor getHelmet() {
            return helmet;
      }
      
      public Armor getChest() {
            return chest;
      }
      
      public Armor getPants() {
            return pants;
      }
      
      public Armor getBoots() {
            return boots;
      }
      
      public Armor getGauntlets() {
            return gauntlets;
      }
      
      // -------------------- GAMEPLAY --------------------
      
      private boolean defensive;
      
      @Override
      public int getDamage () {
            return (int) (1 + weaponDamage * (1 + (0.07 * getStrength())));
      }
      
      @Override
      public int getDefense () {
            if (defensive) {
                  // Receive double defensiveness bonus
                  return (int) (getDefensiveValues() * 2 * (1 + (0.07 * getDefensiveness())));
            }
            else
                  return (int) (getDefensiveValues() * (1 + (0.07 * getDefensiveness())));
      }
      
      private int getDefensiveValues () {
            return (shieldDef + helmetDef + chestDef + pantsDef + bootsDef + gauntletsDef);
      }
      
      public void startDefense() {
            this.defensive = true;
            System.out.println(getName() + " took a defensive stance.");
      }
      
      public void stopDefense () {
            this.defensive = false;
            System.out.println(getName() + " stopped defending themselves.");
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
      
      public int getDefensiveness() {
            return this.defensiveness;
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
      
      public int getSkillPoints() {
            return skillPoints;
      }
      
      /**
       * @return The ratio between the current mana and maximum mana as a floating points number.
       */
      public double getManaPercentage () {
            return ((double) this.getCurrentMana()/this.getMana());
      }
      
      /**
       * @return The ratio between current experience-points and experience-points required for the next level as a floating points number.
       */
      public double getExperiencePercentage() {
            return (double) getExperience()/getExperienceRequiredForNextLevel();
      }
      
      /**
       * @return True, because this is the player object.
       */
      @Override
      public boolean isPlayer() {
            return true;
      }
      
      /**
       * @return True, if the number of skill-points is greater than 0.
       */
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
      
      public void setDefensiveness(int _defensiveness) {
            this.defensiveness = _defensiveness;
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
      
      public void setCanLevelUp(boolean _canLevelUp) {
            this.canLevelUp = _canLevelUp;
      }
      
      public void setSkillPoints(int _skillPoints) {
            this.skillPoints = _skillPoints;
      }
      
      public void addSkillPoints(int _skillPoints) {
            this.skillPoints += _skillPoints;
      }
}
