package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.entities.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a battle between the player and up to three enemies.
 */
public class Battle {
      /**
       * The player.
       */
      Player player;
      
      /**
       * An array of enemies.
       */
      ArrayList<Enemy> enemies;
      
      /**
       * Creates a new battle with up to three enemies.
       * @param _player The player.
       * @param _enemies An array of enemies. Create a new enemy object for each enemy you want to use.
       */
      public Battle (Player _player, Enemy[] _enemies) {
            this.player = _player;
            this.enemies = new ArrayList<>(Arrays.asList(_enemies));
      }
      
      /**
       * Allows enemies to perform actions; currently only lets them attack.
       */
      public void enemyTurn() {
            for (Enemy e : enemies) {
                  if (player.isAlive() && e.isAlive())
                        attack(this.player, e.getDamage());
            }
      }
      
      /**
       * Lets entities attack one another.
       * @param defendant The entity being attacked.
       * @param _damage The damage value to be dealt to the defendant.
       */
      public void attack (Entity defendant, int _damage) {
            if (defendant.isPlayer()) {
                  player.doDamage(_damage);
            }
            else {
                  if (defendant instanceof Enemy) {
                        if (enemies.contains(defendant)) {
                              defendant.doDamage(_damage);
                        }
                  }
            }
      }
      
      /**
       * Checks if there is any enemy left alive in the battle.
       * @return True, if there are enemies alive.
       */
      public boolean isAnyEnemyAlive () {
            for (Enemy e : enemies) {
                  if (e.isAlive()) {
                        return true;
                  }
            }
            
            return false;
      }
      
      /**
       * Returns the rewards that should be passed onto the player. Currently, calculates XP values based on the enemies' stats.
       */
      public void getReward(Player _player) {
            int xp = 0;
            
            for (Enemy e : enemies) {
                  xp += e.getExperienceValue();
            }
            
            System.out.println("XP of function's player before battle: " + _player.getExperience());
            System.out.println("XP of battle's player before battle: " + player.getExperience());
            System.out.printf("%s receives %d experience points.%n", _player.getName(), xp);
            
            _player.addExperience(xp);
            System.out.println("XP of function's player after battle: " + _player.getExperience());
            System.out.println("XP of battle's player after battle: " + player.getExperience());
            
            
      }
      
      /**
       * Gets the enemy at the given position in the battle.
       * @param enemyPosition A value between 0 and 2 (inclusive).
       * @return
       * @throws IllegalArgumentException
       */
      public Enemy getEnemy (int enemyPosition) throws IllegalArgumentException {
            if (enemyPosition < getNumberOfEnemies() && enemyPosition >= 0) {
                  return enemies.get(enemyPosition);
            }
            else throw new IllegalArgumentException("Wrong number of enemies.");
      }
      
      public int getNumberOfEnemies () {
            return this.enemies.size();
      }
}
