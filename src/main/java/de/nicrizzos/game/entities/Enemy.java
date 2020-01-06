package de.nicrizzos.game.entities;

import java.util.Random;

/**
 * Represents an entity to be used as an enemy in a battle.
 * It uses its own stat system.
 * @author Nico Piel
 * @version 1.0
 */
public class Enemy extends Entity {
      /**
       * Constructs a new enemy with all its stats.
       * ID will be a random integer.
       * @param _name    The enemy's name to be shown in battle; doesn't have to be unique.
       * @param _health  The enemy's hit points.
       * @param _damage  The enemy's raw damage.
       * @param _defense The enemy's raw defense.
       */
      public Enemy(String _name, int _health, int _damage, int _defense) {
            super(_name, "enemy_" + new Random().nextInt());
            setHealth(_health);
            setCurrentHealth(getHealth());
            setDamage(_damage);
            setDefense(_defense);
      }
      
      /**
       * Calculates how many experience-points the enemy is worth, by summing its base stats.
       * Will then round down to the nearest ten.
       *
       * @return A rounded value.
       */
      public int getExperienceValue() {
            return (((getDamage() + getDefense() + getHealth()) + 5) / 10) * 10;
      }
      
      /**
       * @param obj Object to compare with.
       * @return True, if the identification and class are equal.
       */
      @Override
      public boolean equals(Object obj) {
            if (obj.getClass().equals(this.getClass())) {
                  return (this.identification.equals(((Enemy) obj).identification));
            }
            
            return false;
      }
}
