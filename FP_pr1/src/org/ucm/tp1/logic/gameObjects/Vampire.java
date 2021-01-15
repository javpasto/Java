package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public class Vampire extends GameObject{
	
	protected boolean move = false; //Para ver si se ha movido antes o no
	private final int attackDamage = 1;
	private final int maxHp = 5;
	
	public Vampire(int x, int y, Game game){
		super(x, y, game);
		this.hp = this.maxHp;
	}
	
	@Override
	public void attack() {
		if (this.isAlive() && this.game.onBoard(this.x - 1, this.y)) {
			IAttack other = this.game.getAttackableInPosition(this.x - 1, this.y);
			if (other != null)	other.receiveVampireAttack(this.attackDamage);
		}
	}

	@Override
	public boolean receiveSlayerAttack(int damage) {
		if (this.isAlive()) {
			this.receiveDamage(damage);
			if (this.hp == 0) this.game.removeNumVampiresOnBoard();
			return true;
		}
		return false;
	}

	@Override
	public boolean receiveVampireAttack(int damage) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean receiveLightFlash() {
		this.hp = 0;
		this.game.removeNumVampiresOnBoard();
		return true;
	}

	@Override
	public boolean receiveGarlicPush() {
		if (this.isAlive()){
			if (this.x + 1 >= this.game.getX()){ //Se sale del tablero
				this.hp = 0;
				this.game.removeNumVampiresOnBoard();
			}
			else if (!this.game.emptyCell(this.x + 1, this.y)) {
				//No hace nada
			}
			else{
				this.x += 1;
			}
			this.move = false; //Queda aturdido 1 turno
		}
		
		return true;
	}

	@Override
	public boolean receiveDraculaAttack() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean receiveVampireExplosion(int explosionDamage) {
		if (this.isAlive()) {
			this.receiveDamage(explosionDamage);
			if (this.hp == 0) this.game.removeNumVampiresOnBoard();
			return true;
		}
		return false;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
		if (this.isAlive()){
			if (this.game.emptyCell(this.x - 1, this.y)){
				if (this.move) this.x = this.x - 1;
				if (this.x == -1) this.game.changeVampireWin();
				this.move = !this.move;
				return;
			}
			this.move = !this.move;
			if (!this.game.emptyCell(this.x - 1, this.y) && !this.move) this.move = true; //Si no se mueve por que en la celda de delante hay algo lo hara cuando pueda
		}
		/*
		if (this.move && this.isAlive() && this.game.emptyCell(this.x - 1, this.y)){
			this.x = this.x - 1;
		}
		this.move = !this.move; //Se mueve un turno si uno no
		if (!this.game.emptyCell(this.x - 1, this.y) && !this.move) this.move = true; //Si no se mueve por que en la celda de delante hay algo lo hara cuando pueda
		if (this.x == -1) this.game.changeVampireWin();
		*/
	}

	@Override
	public String getImage() {
		return "V [" + this.hp + "]";
	}

	@Override
	public String serializeInfo() {
		if (this.move) return "V;" + this.x + ";" + this.y + ";" + this.hp + ";" + 0;
		return "V;" + this.x + ";" + this.y + ";" + this.hp + ";" + 1;
	}

}
