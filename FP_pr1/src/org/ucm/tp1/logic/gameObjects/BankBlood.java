package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public class BankBlood extends Slayer{
	
	private int z;

	public BankBlood(int x, int y, Game game, int additionalCost) {
		super(x, y, game);
		this.hp = 1;
		this.z = additionalCost;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void attack() {
		//No ataca
	}
	
	int getPrice() { return this.z; }

	@Override
	public boolean receiveSlayerAttack(int damage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveVampireAttack(int damage) {
		if (this.isAlive()){
			this.hp = 0;
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
		if (this.isAlive()) this.game.addNonTurnCoins((int) ((int) this.z * 0.1)); //Se redondea hacia arriba
	}

	@Override
	public String getImage() {
		return "B [" + this.z + "]";
	}
	
	@Override
	public String serializeInfo() {
		return "B;" + this.x + ";" + this.y + ";" + this.hp + ";" + this.z;
	}

}
