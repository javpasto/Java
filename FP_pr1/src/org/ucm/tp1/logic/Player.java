package org.ucm.tp1.logic;

public class Player {

	private int monedas;
	
	public Player(){
		this.monedas = 50;
	}
	
	public int getMonedas(){ return this.monedas; }
	
	//return true si hay monedas para pagar
	public boolean quitarMonedas(int monedas) {
		if (this.monedas >= monedas) {
			this.monedas -= monedas;
			return true;
		}
		return false;
	}
	
	public void darMonedas(int monedas) { this.monedas += monedas; }
	
}
