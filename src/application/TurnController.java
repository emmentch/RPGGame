package application;

import java.util.Random;

public class TurnController {

	static void doTurn(String text) {
		PlayerAction action = processText(text);
		if (playerTurn(action)) {
			EntityController.player.poisonEffect();
			EntityController.isPlayerDead();
			enemyTurn(action);
		}
		UI.updateLabels();
	}

	private static PlayerAction processText(String text) {
		switch (text.toLowerCase().charAt(0)) {
		case 'j':
			return PlayerAction.jab;
		case 'k':
			return PlayerAction.kick;
		case 'p':
			return PlayerAction.poisondart;
		case 'c':
			return PlayerAction.chop;
		case 'b':
			return PlayerAction.block;
		case 'h':
			return PlayerAction.heal;
		default:
			return PlayerAction.invalid;
		}

	}

	private static boolean playerTurn(PlayerAction action) {
		switch (action) {
		case jab:
			EntityController.player.jab(EntityController.enemy);
			return !EntityController.isEnemyDead();
		case kick:
			EntityController.player.kick(EntityController.enemy);
			return !EntityController.isEnemyDead();
		case poisondart:
			EntityController.player.poisondart(EntityController.enemy);
			return !EntityController.isEnemyDead();
		case chop:
			EntityController.player.chop(EntityController.enemy);
			return !EntityController.isEnemyDead();
		case heal:
			EntityController.player.heal();
			return !EntityController.isEnemyDead();
		case block:
			// TODO
			return !EntityController.isEnemyDead();
		case invalid:
			return false;
		default:
			return false;
		}
	}

	private static void enemyTurn(PlayerAction action) {
		Random rand = new Random();
		int enemySelection;
		if (EntityController.player.getStatus() == Status.Healthy) {// does all attack options to maybe inflict status
			enemySelection = rand.nextInt(10);
		} else {
			enemySelection = rand.nextInt(6);
		}
		if ((EntityController.enemy.getStatus() != Status.Healthy || EntityController.enemy.getCurrHealth() <= 5)
				&& Math.random() <= .2) {
			EntityController.enemy.heal();
		} else {
			switch (enemySelection) {
			case 0:
			case 1:
			case 2:
				EntityController.enemy.jab(EntityController.player);
				break;
			case 3:
			case 4:
			case 5:
				EntityController.enemy.kick(EntityController.player);
				break;
			case 6:
			case 7:
				EntityController.enemy.poisondart(EntityController.player);
				break;
			case 8:
			case 9:
				EntityController.enemy.chop(EntityController.player);
				break;
			default:
				EntityController.enemy.jab(EntityController.player);
				break;
			}
		}
		EntityController.isPlayerDead();
		EntityController.enemy.poisonEffect();
		EntityController.isEnemyDead();
	}
}
