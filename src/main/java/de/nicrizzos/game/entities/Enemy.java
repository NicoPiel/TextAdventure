package de.nicrizzos.game.entities;

public class Enemy extends Entity {
      private int health;
      private int damage;
      private int defense;
      
      public Enemy(String _name, int _health, int _damage, int _defense) {
            super(_name, "enemy_" + _name);
            setHealth(_health);
            setDamage(_damage);
            setDefense(_defense);
      }
      
      public int getHealth() {
            return this.health;
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
      
      public void setDamage(int _damage) {
            this.damage = _damage;
      }
      
      public void setDefense(int _defense) {
            this.defense = _defense;
      }
}
