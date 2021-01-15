package org.ucm.tp1.logic.gameObjects;
import java.util.ArrayList;

public class GameObjectBoard {
	
	private ArrayList<GameObject> gameObjects;

	public GameObjectBoard(){
		this.gameObjects = new ArrayList<GameObject>();
	}
	
	public boolean addGameObject(GameObject object){
		if (!this.emptyCell(object.getX(), object.getY())) return false;
		gameObjects.add(object);
		return true;
	}
	
	public boolean emptyCell(int x, int y){
		for (int i = 0; i < this.gameObjects.size(); i++){
			if (this.gameObjects.get(i).getX() == x && this.gameObjects.get(i).getY() == y && this.gameObjects.get(i).isAlive()) return false;
		}
		return true;
	}
	
	public void moveObjects(){
		for (int i = 0; i < this.gameObjects.size(); i++){
			this.gameObjects.get(i).move();
		}
	}
	
	public void attack(){
		for (int i = 0; i < this.gameObjects.size(); i++){
			this.gameObjects.get(i).attack();
		}
	}
	
	public void move(){
		for (int i = 0; i < this.gameObjects.size(); i++){
			this.gameObjects.get(i).move();
		}
	}

	public IAttack getReferenceObject(int x, int y) {
		for (int i = 0; i < this.gameObjects.size(); i++){
			if (this.gameObjects.get(i).getX() == x && this.gameObjects.get(i).getY() == y && this.gameObjects.get(i).isAlive()) return this.gameObjects.get(i);
		}
		return null;
	}

	public String getImage(int x, int y) {
		for (int i = 0; i < this.gameObjects.size(); i++){
			if (this.gameObjects.get(i).getX() == x && this.gameObjects.get(i).getY() == y && this.gameObjects.get(i).isAlive())
				return this.gameObjects.get(i).getImage();
		}
		return "    "; //Celda vacia
	}

	//Cambiamos la direción en la que recorremos para que empuje antes los vampiros q se han metido despues
	public void garlicPush() {
		for (int i = this.gameObjects.size() - 1; i >= 0; i--){
			this.gameObjects.get(i).receiveGarlicPush();
		}
	}

	public void lightFlash() {
		for (int i = 0; i < this.gameObjects.size(); i++){
			this.gameObjects.get(i).receiveLightFlash();
		}
	}

	public String serializeGameObjects() {
		String rtr = "";
		for (int i = 0; i < this.gameObjects.size(); i++){
			if (this.gameObjects.get(i).isAlive()) rtr += this.gameObjects.get(i).serializeInfo() + "\n";
		}
		return rtr;
	}
	
}
