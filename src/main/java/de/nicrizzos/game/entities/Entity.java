package de.nicrizzos.game.entities;

import de.nicrizzos.game.GameObject;

/**
 * An abstract class defining an 'entity', as in a 'living thing'
 */
public abstract class Entity extends GameObject {
      private int health;
      private int currentHealth;
      private int damage;
      private int defense;
      
      public Entity () {
            super();
      }
      
      public Entity (String _name) {
            super(_name);
      }
      
      public Entity (String _name, String _ID) {
            super(_name, _ID);
      }
      
      public void doDamage (int _damage) {
            int damageDone = _damage - this.getDefense();
            
            if (damageDone > 0) {
                  int newHP = getCurrentHealth() - damageDone;
                  
                  if (newHP <= 0) {
                        setCurrentHealth(0);
                        System.out.println(getName() + " died.");
                  }
                  else {
                        setCurrentHealth(newHP);
                        System.out.printf("%s received %d damage!%n", this.getName(), damageDone);
                  }
            }
            else {
                  int newHP = getCurrentHealth() - 1;
                  setCurrentHealth(newHP);
                  System.out.println("That didn't do a lot of damage!");
            }
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
}
