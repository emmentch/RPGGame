package application;

public class Enemy extends Entity {

	public Enemy(String name) {
		super(name);
		this.exp = 20;
	}

	public Enemy(String name, int maxHealth) {
		super(name, maxHealth, 20);
	}

	public Enemy(String name, int maxHealth, int exp) {
		super(name, maxHealth, exp);
	}

	public Enemy(String name, int maxHealth, int exp, int attack) {
		super(name, maxHealth, exp, attack);
	}

	public Enemy(String name, int maxHealth, int exp, int attack, int level) {
		super(name, maxHealth, exp, attack, level);
	}

	@Override
	public String getStats() {
		return "Name: " + this.name + "\nLevel: " + this.level + "\nHealth: " + this.currHealth + "/" + this.maxHealth
				+ "\nExp Yield: " + this.exp + "\nAttack: " + this.attack + "\nStatus: " + this.status;
	}

}
