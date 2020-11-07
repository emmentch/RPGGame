package application;

public class EntityController {

	static Player player;
	static Enemy enemy;
	static String defaultPlayerName = Constants.DEFAULTPLAYERNAME;
	static int enemiesKilled = 0;

	static void setEntities(Player player) {
		EntityController.player = player;
		EntityController.enemy = getRandomEnemy();
	}

	static void setNewEnemy(Enemy enemy) {
		EntityController.enemy = enemy;
		UI.updateLabels();
	}

	static boolean isEnemyDead() {
		if (enemy.getCurrHealth() <= 0) {
			enemiesKilled++;
			// give player exp & level up if need be
			player.addExp(enemy.getExp());
			// new enemy
			setNewEnemy(getRandomEnemy());// random now
			return true;
		}
		return false;
	}

	static void isPlayerDead() {
		if (player.getCurrHealth() <= 0) {
			UI.infoSide.setText("You got a Game Over, close the window and try again!");
			UI.console.setDisable(true);
		}
	}

	static Enemy getRandomEnemy() {
		double x = Math.random();
		int playerLevelModifer = EntityController.player.getLevel() - 1;
		int healthModifer = 5 * playerLevelModifer;
		int attackModifer = 1 * playerLevelModifer;
		int expModifer = 10 * playerLevelModifer;
		if (enemiesKilled > 0 && enemiesKilled % 5 == 0) {
			return new Enemy("Evil " + player.getName(), player.getMaxHealth(), Constants.EXPCAP, player.getAttack(),
					player.getLevel());
		}
		if (x > .66) {
			return new Enemy("Goblin", 15 + healthModifer, 40 + expModifer, 4 + attackModifer);
		} // [.67,1]
		else if (x > .33) {
			return new Enemy("Orge", 25 + healthModifer, 50 + expModifer, 2 + attackModifer);
		} // [.33,.66]
		else {
			return new Enemy("Grunt", 10 + healthModifer, 35 + expModifer, 3 + attackModifer);
		} // [.0,.32]
	}
}
