package de.nicrizzos.game.entities;

import de.nicrizzos.game.GameObject;

/**
 * An abstract class defining an 'entity', as in a 'living thing'
 * @author Nico Piel
 * @version 1.1
 */
public abstract class Entity extends GameObject {
      /**
       * The entity's health or 'hit points'
       */
      protected int health;
      
      /**
       * The entity's current health. It dies if this drops below zero.
       */
      protected int currentHealth;
      
      /**
       * The raw damage an entity can do.
       */
      protected int damage;
      
      /**
       * The raw damage an entity can absorb.
       */
      protected int defense;
      
      public Entity() {
            super();
      }
      
      public Entity(String _name) {
            super(_name);
      }
      
      public Entity(String _name, String _ID) {
            super(_name, _ID);
      }
      
      public void doDamage(int _damage) {
            int damageDone = _damage - this.getDefense();
            
            if (damageDone > 0) {
                  int newHP = getCurrentHealth() - damageDone;
                  
                  if (newHP <= 0) {
                        setCurrentHealth(0);
                        System.out.println(getName() + " died.");
                  } else {
                        setCurrentHealth(newHP);
                        System.out.printf("%s received %d damage!%n", this.getName(), damageDone);
                  }
            } else {
                  int newHP = getCurrentHealth() - 1;
                  setCurrentHealth(newHP);
                  System.out.println("That didn't do a lot of damage!");
            }
      }
      
      /**
       * @return The entity's maximum health value.
       */
      public int getHealth() {
            return this.health;
      }
      
      /**
       * @return The entity's current health value.
       */
      public int getCurrentHealth() {
            return this.currentHealth;
      }
      
      /**
       * @return True, if the entity's hit points are greater than zero.
       */
      public boolean isAlive() {
            return getCurrentHealth() > 0;
      }
      
      /**
       * @return The ratio between the current health and maximum health as a floating points number.
       */
      public double getHealthPercentage() {
            return (double) getCurrentHealth() / getHealth();
      }
      
      /**
       * @return The entity's raw damage value.
       */
      public int getDamage() {
            return this.damage;
      }
      
      /**
       * @return The entity's raw defense value.
       */
      public int getDefense() {
            return this.defense;
      }
      
      /**
       * Sets the entity's maximum health.
       *
       * @param _health Value
       */
      public void setHealth(int _health) {
            this.health = _health;
      }
      
      /**
       * Sets the entity's current health.
       *
       * @param currentHealth Value
       */
      public void setCurrentHealth(int currentHealth) {
            this.currentHealth = currentHealth;
      }
      
      /**
       * Sets the entity's damage value.
       *
       * @param _damage Value
       */
      public void setDamage(int _damage) {
            this.damage = _damage;
      }
      
      /**
       * Sets the entity's defense value.
       *
       * @param _defense Value
       */
      public void setDefense(int _defense) {
            this.defense = _defense;
      }
      
      /**
       * @param obj Object to compare with.
       * @return True, if the identification and class are equal.
       */
      @Override
      public boolean equals(Object obj) {
            if (obj.getClass().equals(this.getClass())) {
                  return (this.identification.equals(((Entity) obj).identification));
            }
            
            return false;
      }
}
