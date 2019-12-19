package de.nicrizzos.game.entities;

public class Player extends Entity {
      private int level;
      private int experience;
      private int experienceRequiredForNextLevel;
      private int health;
      private int mana;
      private int strength;
      private int dexterity;
      private int magic;
      private int vitality;
      private int defense;
      private int lightFooted;
      private int slightOfHand;
      private int perception;
      private int survivalism;
      private int knowledge;
      private int rhetoric;
      
      private boolean isPlayer;
      
      public Player (String _name) {
            this.identification = "player";
            this.name = _name;
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
      }
      
      public void levelUp() {
            this.level++;
            this.experienceRequiredForNextLevel = 500 + (level * 500);
      }
      
      public void increaseStat(String stat) {
            switch (stat) {
                  case "strength" -> {
                        this.strength++;
                  }
                  case "dexterity" -> {
                        this.dexterity++;
                  }
                  case "magic" -> {
                        this.magic++;
                        this.mana = 10 + (this.magic * 10);
                  }
                  case "vitality" -> {
                        this.vitality++;
                        this.health = 100 + (this.vitality * 20);
                  }
                  case "defense" -> {
                        this.defense++;
                  }
                  case "lightFooted" -> {
                        this.lightFooted++;
                  }
                  case "slightOfHand" -> {
                        this.slightOfHand++;
                  }
                  case "perception" -> {
                        this.perception++;
                  }
                  case "survivalism" -> {
                        this.survivalism++;
                  }
                  case "knowledge" -> {
                        this.knowledge++;
                  }
                  case "rhetoric" -> {
                        this.rhetoric++;
                  }
            }
      }
      
      // -------------------- GETTERS --------------------
      
      public int getLevel() {
            return this.level;
      }
      
      public int getExperience() {
            return experience;
      }
      
      public int getExperienceRequiredForNextLevel() {
            return experienceRequiredForNextLevel;
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
      
      public boolean isPlayer() {
            return isPlayer;
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
      
      
      public void setExperienceRequiredForNextLevel(int _experienceRequiredForNextLevel) {
            this.experienceRequiredForNextLevel = _experienceRequiredForNextLevel;
      }
}
