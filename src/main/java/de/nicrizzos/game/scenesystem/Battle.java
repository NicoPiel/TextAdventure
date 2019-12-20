package de.nicrizzos.game.scenesystem;

import de.nicrizzos.game.entities.Enemy;
import de.nicrizzos.game.entities.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battle {
      Player player;
      ArrayList<Enemy> enemies;
      
      public Battle (Player _player, Enemy[] _enemies) {
            this.player = _player;
            
            List<Enemy> enemyList = Arrays.asList(_enemies);
            this.enemies = new ArrayList<>(enemyList) ;
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
