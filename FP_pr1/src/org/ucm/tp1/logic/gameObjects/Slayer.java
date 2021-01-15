package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public class Slayer extends GameObject{
	
	private final int price = 50;
	private final int attackDamage = 1;
	protected final int maxHp = 3;
	
	public Slayer(int x, int y, Game game){
		super(x, y, game);
		this.hp = this.maxHp;
	}
	
	@Override
	public void attack() {
		if (this.isAlive()) {
			int i = this.x;
			boolean done = false;
			while(this.game.onBoard(i, this.y) && !done){
				IAttack other = this.game.getAttackableInPosition(i + 1, this.y);
				i++;
				if (other != null) done = other.receiveSlayerAttack(this.attackDamage);
				}
			}	
	}
	
	int getPrice() { return this.price; }

	@Override
	public boolean receiveSlayerAttack(int damage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveVampireAttack(int damage) {
		if (this.isAlive()){
			this.receiveDamage(damage);
			return true;
		}
		return false;
	}

	@Override
	public boolean receiveLightFlash() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveGarlicPush() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveDraculaAttack() {
		return this.receiveVampireAttack(this.maxHp); //Nos aseguramos que muera si o si
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getImage() {
		return "S [" + this.hp + "]";
	}

	@Override
	public boolean receiveVampireExplosion(int explosionDamage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String serializeInfo() {
		return "S;" + this.x + ";" + this.y + ";" + this.hp;
	}
	
}
