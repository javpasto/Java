package org.ucm.tp1.logic.gameObjects;

import org.ucm.tp1.logic.Game;

public abstract class GameObject implements IAttack{

	protected int hp;
	protected int x;
	protected int y;
	protected Game game;
	
	public GameObject(int x, int y, Game game){
		this.x = x;
		this.y = y;
		this.game = game;
	}
	
	public void receiveDamage(int damage){
		this.hp -= damage;
		if (this.hp < 0) this.hp = 0;
	}
	
	abstract public String getImage();
	
	abstract public String serializeInfo();
	
	abstract public void move();
	
	public boolean isAlive() {
		return this.hp > 0;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	
	
}
