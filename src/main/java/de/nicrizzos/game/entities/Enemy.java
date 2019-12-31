package de.nicrizzos.game.entities;

import java.util.Random;

public class Enemy extends Entity {
      private int health;
      private int currentHealth;
      private int damage;
      private int defense;
      
      public Enemy(String _name, int _health, int _damage, int _defense) {
            super(_name, "enemy_" + new Random().nextInt());
            setHealth(_health);
            setCurrentHealth(getHealth());
            setDamage(_damage);
            setDefense(_defense);
      }
      
      public int getHealth() {
            return this.health;
      }
      
      public int getCurrentHealth() {
            return currentHealth;
      }
      
      public boolean isAlive() {
            return getCurrentHealth() > 0;
      }
      
      public double getHealthPercentage () {
            return (double) getCurrentHealth()/getHealth();
      }
      
      public int getDamage() {
            return this.damage;
      }
      
      public int getDefense() {
            return this.defense;
      }
      
      public void setHealth(int _health) {
            this.health = _health;
      }
      
      public void setCurrentHealth(int currentHealth) {
            this.currentHealth = currentHealth;
      }
      
      public void setDamage(int _damage) {
            this.damage = _damage;
      }
      
      public void setDefense(int _defense) {
            this.defense = _defense;
      }
      
      public int getExperienceValue() {
            return (((getDamage() + getDefense() + getHealth()) + 5) / 10) * 10;
      }
      
      @Override
      public boolean equals(Object obj) {
            if (obj.getClass().equals(this.getClass())) {
                  return (this.identification.equals(((Enemy) obj).identification));
            }
            
            return false;
      }
}
