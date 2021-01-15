package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public class ExplosiveVampire extends Vampire{
	
	private final int attackDamage = 1;
	
	private final int explosionDamage = 1;

	public ExplosiveVampire(int x, int y, Game game) {
		super(x, y, game);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void attack() {
		if (this.isAlive() && this.game.onBoard(this.x - 1, this.y)) {
			IAttack other = this.game.getAttackableInPosition(this.x - 1, this.y);
			if (other != null)
				other.receiveVampireAttack(this.attackDamage);
			}
	}
	
	//Las cuatro casillas alrededor suyo y solo hace daño a vampiros
	public void deathTrigger(){
		
		if (this.game.onBoard(this.x - 1, this.y - 1)) {
			IAttack other = this.game.getAttackableInPosition(this.x - 1, this.y - 1);
			if (other != null)	other.receiveVampireExplosion(this.explosionDamage);
		}
		if (this.game.onBoard(this.x - 1, this.y)) {
			IAttack other = this.game.getAttackableInPosition(this.x - 1, this.y);
			if (other != null)	other.receiveVampireExplosion(this.explosionDamage);
		}
		if (this.game.onBoard(this.x - 1, this.y + 1)) {
			IAttack other = this.game.getAttackableInPosition(this.x - 1, this.y + 1);
			if (other != null)	other.receiveVampireExplosion(this.explosionDamage);
		}
		if (this.game.onBoard(this.x, this.y + 1)) {
			IAttack other = this.game.getAttackableInPosition(this.x, this.y + 1);
			if (other != null)	other.receiveVampireExplosion(this.explosionDamage);
		}
		if (this.game.onBoard(this.x, this.y - 1)) {
			IAttack other = this.game.getAttackableInPosition(this.x, this.y - 1);
			if (other != null)	other.receiveVampireExplosion(this.explosionDamage);
		}
		if (this.game.onBoard(this.x + 1, this.y - 1)) {
			IAttack other = this.game.getAttackableInPosition(this.x + 1, this.y - 1);
			if (other != null)	other.receiveVampireExplosion(this.explosionDamage);
		}
		if (this.game.onBoard(this.x + 1, this.y)) {
			IAttack other = this.game.getAttackableInPosition(this.x + 1, this.y);
			if (other != null)	other.receiveVampireExplosion(this.explosionDamage);
		}
		if (this.game.onBoard(this.x + 1, this.y + 1)) {
			IAttack other = this.game.getAttackableInPosition(this.x + 1, this.y + 1);
			if (other != null)	other.receiveVampireExplosion(this.explosionDamage);
		}
	}

	@Override
	public boolean receiveSlayerAttack(int damage) {
		if (this.isAlive()) {
			this.receiveDamage(damage);
			if (this.hp == 0) {
				this.game.removeNumVampiresOnBoard();
				this.deathTrigger(); //Se encarga de hacer daño cuando muere
			}
			return true;
		}
		return false;
	}

	@Override
	public String getImage() {
		return "EV [" + this.hp + "]";
	}
	
	@Override
	public String serializeInfo() {
		if (this.move) return "EV;" + this.x + ";" + this.y + ";" + this.hp + ";" + 0;
		return "EV;" + this.x + ";" + this.y + ";" + this.hp + ";" + 1;
	}

}
