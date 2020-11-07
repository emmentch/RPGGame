package application;

public abstract class Entity {
	protected String name;
	protected int level;
	protected int maxHealth;
	protected int currHealth;
	protected int exp;
	protected int attack;
	protected Status status;

	public Entity(String name) {
		this.name = name;
		this.level = 1;
		this.currHealth = this.maxHealth = Constants.STARTINGHEALTH;
		this.exp = 0;
		this.attack = Constants.STARTINGATTACK;
		status = Status.Healthy;
	}

	public Entity(String name, int maxHealth) {
		this(name);
		this.currHealth = this.maxHealth = maxHealth;
	}

	public Entity(String name, int maxHealth, int exp) {
		this(name, maxHealth);
		this.exp = exp;
	}

	public Entity(String name, int maxHealth, int exp, int attack) {
		this(name, maxHealth, exp);
		this.attack = attack;
	}

	public Entity(String name, int maxHealth, int exp, int attack, int level) {
		this(name, maxHealth, exp, attack);
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMaxHealth() {
		return this.maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public int getCurrHealth() {
		return this.currHealth;
	}

	public void setCurrHealth(int currHealth) {
		this.currHealth = currHealth;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void damage(int damage, double hitPercent) {
		if (Math.random() < hitPercent) {
			currHealth = Math.max(0, currHealth - damage);
		}
	}

	public void jab(Entity entity) {
		double paraMod = 1.0;
		if (status == Status.Paralzyed)
			paraMod = .75;
		entity.damage(this.attack, .9 * paraMod);
	}

	public void kick(Entity entity) {
		double paraMod = 1.0;
		if (status == Status.Paralzyed)
			paraMod = .75;
		entity.damage((int) Math.ceil(this.attack * 1.25), .75 * paraMod);
	}

	public void poisondart(Entity entity) {
		double paraMod = 1.0;
		if (status == Status.Paralzyed)
			paraMod = .75;
		entity.damage((int) Math.ceil(this.attack * 0.5), 1.0 * paraMod);
		if (entity.getStatus() == Status.Healthy)
			entity.setStatus(Status.Poisoned);
	}

	public void chop(Entity entity) {
		double paraMod = 1.0;
		if (status == Status.Paralzyed)
			paraMod = .75;
		entity.damage((int) Math.ceil(this.attack * 0.8), .5 * paraMod);
		if (entity.getStatus() == Status.Healthy)
			entity.setStatus(Status.Paralzyed);
	}

	public void poisonEffect() {
		if (this.status == Status.Poisoned)
			this.damage((int) Math.ceil(maxHealth * Constants.POISONDAMAGEPERCENT), 1.0);
	}

	/**
	 * chance to heal status effect, heals some health
	 * 
	 * @param heal - how much health to heal
	 */
	public void heal() {
		if (Math.random() <= Constants.CHANCETOHEAL) {
			this.status = Status.Healthy;
		}

		currHealth = (int) Math.min(maxHealth,
				Constants.MINHEAL + currHealth + (maxHealth * Math.random() * Constants.HEALPERCENT));
	}

	abstract String getStats();
}
