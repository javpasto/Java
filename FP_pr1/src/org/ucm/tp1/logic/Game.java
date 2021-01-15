package org.ucm.tp1.logic;

import java.util.Random;

import org.ucm.tp1.exceptions.CommandExecuteException;
import org.ucm.tp1.exceptions.DraculaIsAliveException;
import org.ucm.tp1.exceptions.NotEnoughCoinsException;
import org.ucm.tp1.exceptions.UnvalidPositionException;
import org.ucm.tp1.logic.Level;
import org.ucm.tp1.logic.gameObjects.Dracula;
import org.ucm.tp1.logic.gameObjects.ExplosiveVampire;
import org.ucm.tp1.logic.gameObjects.GameObject;
import org.ucm.tp1.logic.gameObjects.GameObjectBoard;
import org.ucm.tp1.logic.gameObjects.IAttack;
import org.ucm.tp1.logic.gameObjects.Vampire;
import org.ucm.tp1.view.IPrintable;

public class Game implements IPrintable{

	private GameObjectBoard gameObject;
	private Player jugador;
	private Random rand;
	private Level level;
	private int vampiresSpawn;
	private int vampiresOnBoard;
	private int numberCycles;
	private boolean vampireWin;
	private boolean exitGame;
	private boolean draculaAlive;
	
	public Game(long seed, Level level) {
		this.gameObject = new GameObjectBoard();
		this.rand = new Random(seed);
		this.jugador = new Player();
		this.level = level;
		this.vampiresSpawn = 0;
		this.vampiresOnBoard = 0;
		this.numberCycles = 0;
		this.vampireWin = false;
		this.exitGame = false;
		this.draculaAlive = false;
	}

	public void reset() {
		this.gameObject = new GameObjectBoard();
		this.jugador = new Player();
		this.vampiresSpawn = 0;
		this.vampiresOnBoard = 0;
		this.numberCycles = 0;
		this.vampireWin = false;
		this.exitGame = false;
		this.draculaAlive = false;
	}
	
	
	public boolean quitarMonedas(int monedas) {
		if (!this.jugador.quitarMonedas(monedas)) return false;
		return true;
	}
	
	
	//For exception handling
	public void quitarMonedasCommand(int monedas) throws CommandExecuteException{
		if (!this.jugador.quitarMonedas(monedas)) throw new NotEnoughCoinsException("Not enought coins, you have " + this.getMonedas() + " coins. You need: " + monedas + " coins");
	}
	
	public String getLevelName(){
		return this.level.getName();
	}
	
	public int getVampiresOnBoard(){
		return this.vampiresOnBoard;
	}
	
	public int getMonedas(){
		return this.jugador.getMonedas();
	}
	
	public void garlicPush(){
		this.gameObject.garlicPush();
	}
	
	public void lightFlash(){
		this.gameObject.lightFlash();
	}
	
	public boolean addGameObject(GameObject obj){
		return this.gameObject.addGameObject(obj);
	}
	
	public void darMonedas(int monedas){
		this.jugador.darMonedas(monedas);
	}
	
	public void addCycle(){
		this.numberCycles++;
	}
	
	public int getCycles(){
		return this.numberCycles;
	}
	
	public void addNumVampiresOnBoard(){
		this.vampiresOnBoard++;
	}
	
	public void removeNumVampiresOnBoard(){
		this.vampiresOnBoard--;
	}
	
	public int getPlayerCoins(){
		return this.jugador.getMonedas();
	}
	
	public int vampiresLeft(){
		return this.level.getVampires() - this.vampiresSpawn;
	}
	
	public int getX(){
		return this.level.getX();
	}
	
	public int getY(){
		return this.level.getY();
	}
	
	public void moveVampires(){
		gameObject.move();
	}
	
	public void addCoins(){
		
		float aux = this.rand.nextFloat();
		if (aux >= 0.5) jugador.darMonedas(10);
		
	}
	
	public void addNonTurnCoins(int coins){
		this.jugador.darMonedas(coins);
	}
	
	public void update(){
		this.addCoins();
		this.addCycle();
		this.moveVampires();
		this.attack();
		this.addRandVampire();
	}
	
	//Con solo el correo y sin casos de prueba parece que pueden spawnear hasta los 3 tipos de vampiro en el mismo turno
	public void addRandVampire(){
		if (this.vampiresSpawn < this.level.getVampires()){
			
			if (level.getFrecuency() >= rand.nextDouble()){
				
				if (gameObject.addGameObject(new Vampire(this.level.getX() - 1, rand.nextInt(level.getY()), this))){
					this.vampiresSpawn++;
					this.vampiresOnBoard++;
				}
			}
			try{
				if (!this.draculaAlive){
					
					if (level.getFrecuency() >= rand.nextDouble()){
						
						if (gameObject.addGameObject(new Dracula(this.level.getX() - 1, rand.nextInt(level.getY()), this))){
							this.vampiresSpawn++;
							this.vampiresOnBoard++;
							this.draculaAlive = true;
						}
					}
					
				}
				else throw new DraculaIsAliveException("");
			} catch (DraculaIsAliveException err){
				//rand.nextDouble(); //No aparece en el documento si tenemos q hacer el rand si dracula esta vivo o no
			}
			
			if (level.getFrecuency() >= rand.nextDouble()){
				
				if (gameObject.addGameObject(new ExplosiveVampire(this.level.getX() - 1, rand.nextInt(level.getY()), this))){
					this.vampiresSpawn++;
					this.vampiresOnBoard++;
				}
			}
		}
	}

	//End the game
	public void end() {
		this.exitGame = true;
		
	}
	
	public boolean isFinished(){
		if (this.vampireWin || (this.vampiresOnBoard == 0 && this.vampiresLeft() == 0) || this.exitGame) {
			if (this.vampireWin) System.out.println("[GAME OVER] Vampires win!");
			if (this.exitGame) System.out.println("[GAME OVER] Nobody wins...");
			if ((this.vampiresOnBoard == 0 && this.vampiresLeft() == 0)) System.out.println("[GAME OVER] You win!");
			return true;
		}
		return false;
	}

	public IAttack getAttackableInPosition(int x, int y) {
		return this.gameObject.getReferenceObject(x, y);
	}
	
	public void attack(){
		this.gameObject.attack();
	}

	@Override
	public String getPositionToString(int x, int y) {
		return this.gameObject.getImage(x, y);
	}

	@Override
	public String getInfo() {
		String rtr = "Number of cycles: " + this.getCycles() +
				"\nCoins: " + this.getPlayerCoins()  +
				"\nRemaining vampires: " + this.vampiresLeft() +
				"\nVampires on the board: " + this.vampiresOnBoard + "\n";
		
		if (this.draculaAlive) rtr += "Dracula is alive\n";
		
		return rtr;
	}
	
	public String serializeGameObjects(){
		return this.gameObject.serializeGameObjects();
	}

	public void changeVampireWin() {
		this.vampireWin = true;		
	}
	
	public void draculaDies(){
		this.draculaAlive = false;
	}
	
	public void draculaLives(){
		this.draculaAlive = true;
	}
	
	public boolean draculaAlive(){
		return this.draculaAlive;
	}
	
	public void vampireSpawns(){
		this.vampiresSpawn++;
		this.vampiresOnBoard++;
	}

	public boolean onBoard(int x, int y) {
		if ((x >= 0 && x < this.level.getX() && y >= 0 && y < this.level.getY())) return true;
		return false;
	}
	
	public boolean onBoardPlayer(int x, int y) {
		if ((x >= 0 && x < this.level.getX() - 1 && y >= 0 && y < this.level.getY())) return true;
		return false;
	}
	
	
	//For exception handling
	public void onBoardCommand(int x, int y) throws CommandExecuteException{
		if (!this.onBoard(x, y)) throw new UnvalidPositionException("The position selected (" + x + ", " + y + ") is invalid");
	}
	
	//For exception handling
	public void onBoardCommandPlayer(int x, int y) throws CommandExecuteException{
		if (!this.onBoardPlayer(x, y)) throw new UnvalidPositionException("The position selected (" + x + ", " + y + ") is invalid");
	}
	
	//For exception handling
	public void emptyCellCommand(int x, int y) throws CommandExecuteException {
		if (!this.emptyCell(x, y)) throw new UnvalidPositionException("The position selected (" + x + ", " + y + ") is already ocupied");
	}
	
	
	public boolean emptyCell(int x, int y) {
		if (this.gameObject.emptyCell(x, y)) return true;
		return false;
	}
	
	public String serialize(){
		String info = "";
		info += "Cycles: " + this.getCycles() + "\n";
		info += "Coins: " + this.getMonedas() + "\n";
		info += "Level: " + this.getLevelName().toUpperCase() + "\n";
		info += "Remaining Vampires: " + this.vampiresLeft() + "\n";
		info += "Vampires on Board: " + this.getVampiresOnBoard() + "\n";
		info += "\n";
		info += "Game Object List:\n";
		info += this.serializeGameObjects() + "\n";
		return info;
	}

}
