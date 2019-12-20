package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.entities.*;

import java.util.ArrayList;
import java.util.Arrays;

public class Battle {
      Player player;
      ArrayList<Enemy> enemies;
      
      public Battle (Player _player, Enemy[] _enemies) {
            this.player = _player;
            this.enemies = new ArrayList<>(Arrays.asList(_enemies));
      }
      
      public void enemyTurn() {
            for (Enemy e : enemies) {
                  if (player.isAlive() && e.isAlive())
                        attack(this.player, e.getDamage());
            }
      }
      
      public void attack (Entity defendant, int _damage) {
            if (defendant.isPlayer()) {
                  player.doDamage(_damage);
            }
            else {
                  if (defendant instanceof Enemy) {
                        if (enemies.contains(defendant)) {
                              ((Enemy) defendant).doDamage(_damage);
                        }
                  }
            }
      }
      
      public boolean isAnyEnemyAlive () {
            for (Enemy e : enemies) {
                  if (e.isAlive()) {
                        return true;
                  }
            }
            
            return false;
      }
      
      public Enemy getEnemy (int enemyPosition) throws IllegalArgumentException {
            if (enemyPosition < getNumberOfEnemies()) {
                  return enemies.get(enemyPosition);
            }
            
            else throw new IllegalArgumentException("There are not that many enemies in this battle");
      }
      
      public int getNumberOfEnemies () {
            return this.enemies.size();
      }
}
