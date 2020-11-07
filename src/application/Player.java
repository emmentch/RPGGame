package application;

public class Player extends Entity {

	public Player(String name) {
		super(name, Constants.STARTINGHEALTH, 0, Constants.STARTINGATTACK, 1);
	}

	public void addExp(int exp) {
		this.exp += exp;
		levelUp();
	}

	private void levelUp() {
		while (exp >= Constants.EXPCAP) {
			level++;
			// currHealth += 5;
			maxHealth += 5;
			currHealth = maxHealth;
			attack++;
			status = Status.Healthy;
			exp -= Constants.EXPCAP;
		}
	}

	@Override
	public String getStats() {
		return "Name: " + this.name + "\nLevel: " + this.level + "\nHealth: " + this.currHealth + "/" + this.maxHealth
				+ "\nExp: " + this.exp + "/" + Constants.EXPCAP + "\nAttack: " + this.attack + "\nStatus: "
				+ this.status + "\nEnemies Killed: " + EntityController.enemiesKilled;
	}
}
