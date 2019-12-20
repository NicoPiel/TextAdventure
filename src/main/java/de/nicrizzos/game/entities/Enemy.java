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
      
      public void doDamage(int _damage) {
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
                  System.out.println("That didn't do any damage!");
            }
      }
      
      public int getHealth() {
            return this.health;
      }
      
      public int getCurrentHealth() {
            return currentHealth;
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
      
      @Override
      public boolean equals(Object obj) {
            if (obj.getClass().equals(this.getClass())) {
                  return (this.identification.equals(((Enemy) obj).identification));
            }
            
            return false;
      }
}
