package org.ucm.tp1.logic.gameObjects;

public interface IAttack {

	void attack();
	
	//He quitado default porque me daba error
	boolean receiveSlayerAttack(int damage);
	boolean receiveVampireAttack(int damage);
	boolean receiveLightFlash();
	boolean receiveGarlicPush();
	boolean receiveDraculaAttack();
	boolean receiveVampireExplosion(int explosionDamage);
	
}
