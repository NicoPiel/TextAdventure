package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.entities.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a battle between the player and up to three enemies.
 */
public class Battle extends SceneContent {
      /**
       * The player.
       */
      Player player;
      
      /**
       * An array of enemies.
       */
      ArrayList<Enemy> enemies;
      
      int chapterIndex;
      
      /**
       * Creates a new battle with up to three enemies.
       * @param _player The player.
       * @param _enemies An array of enemies. Create a new enemy object for each enemy you want to use.
       */
      public Battle (Player _player, Enemy[] _enemies) {
            this.player = _player;
            this.enemies = new ArrayList<>(Arrays.asList(_enemies));
      }
      
      public Battle (int _chapterIndex, Enemy[] _enemies) {
            this.enemies = new ArrayList<>(Arrays.asList(_enemies));
            this.chapterIndex = _chapterIndex;
      }
      
      public Battle (int _chapterIndex, ArrayList<Enemy> _enemies) {
            this.enemies = _enemies;
            this.chapterIndex = _chapterIndex;
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
       * @param _player Add rewards to this player object.
       */
      public void getReward(Player _player) {
            int xp = 0;
            
            for (Enemy e : enemies) {
                  xp += e.getExperienceValue();
            }
            
            System.out.printf("%s receives %d experience points.%n", _player.getName(), xp);
            
            _player.addExperience(xp);
      }
      
      /**
       * Gets the enemy at the given position in the battle.
       * @param enemyPosition A value between 0 and 2 (inclusive).
       * @return The enemy at that position.
       * @throws IllegalArgumentException Thrown whenever the function tries to evaluate values greater than 2 or smaller than 0.
       */
      public Enemy getEnemy (int enemyPosition) throws IllegalArgumentException {
            if (enemyPosition < getNumberOfEnemies() && enemyPosition >= 0) {
                  return enemies.get(enemyPosition);
            }
            else throw new IllegalArgumentException("Wrong number of enemies.");
      }
      
      /**
       * @return The number of enemies in the battle.
       */
      public int getNumberOfEnemies () {
            return this.enemies.size();
      }
      
      /**
       * Sets the player object to be used in this battle.
       * @param _player Value
       */
      public void setPlayer (Player _player) {
            this.player = _player;
      }
      
      /**
       * @return The index of this battle in the overall chapter or scene.
       * @deprecated
       */
      public int getChapterIndex() {
            return chapterIndex;
      }
}
