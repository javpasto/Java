package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

//Solo puede haber un dracula en tablero y tiene toque mortal
public class Dracula extends Vampire{

	public Dracula(int x, int y, Game game) {
		super(x, y, game);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	public void attack() {
		if (this.isAlive() && this.game.onBoard(this.x - 1, this.y)) {
			IAttack other = this.game.getAttackableInPosition(this.x - 1, this.y);
			if (other != null)
				other.receiveDraculaAttack();
			}
	}

	@Override
	public boolean receiveLightFlash() {
		//No recibe daño de este ataque
		return true;
	}
	
	@Override
	public boolean receiveSlayerAttack(int damage) {
		if (this.isAlive()) {
			this.receiveDamage(damage);
			if (this.hp == 0) {
				this.game.removeNumVampiresOnBoard();
				this.game.draculaDies();
			}
			return true;
		}
		return false;
	}
	
	@Override
	public boolean receiveGarlicPush() {
		if (this.isAlive()){
			
			if (this.x + 1 >= this.game.getX()){ //Se sale del tablero
				this.hp = 0;
				this.game.removeNumVampiresOnBoard();
				this.game.draculaDies();
			}
			else if (!this.game.emptyCell(this.x + 1, this.y)){
				//nada
			}
			else{
				this.x += 1;
			}
			this.move = false; //Queda aturdido 1 turno
		}
		
		return true;
	}

	@Override
	public boolean receiveVampireExplosion(int explosionDamage) {
		if (this.isAlive()) {
			this.receiveDamage(explosionDamage);
			if (this.hp == 0) {
				this.game.removeNumVampiresOnBoard();
				this.game.draculaDies();
			}
			return true;
		}
		return false;
	}
	
	@Override
	public String getImage() {
		return "D [" + this.hp + "]";
	}
	
	@Override
	public String serializeInfo() {
		if (this.move) return "D;" + this.x + ";" + this.y + ";" + this.hp + ";" + 0;
		return "D;" + this.x + ";" + this.y + ";" + this.hp + ";" + 1;
	}

}
