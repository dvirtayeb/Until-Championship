package Model;

public abstract class Game {
	protected String typeGame;
	public Game(String name) {
		this.typeGame = name;
	}
	
	public String getGame() {
		return typeGame;
	}
	
	public void setGame(String name) {
		this.typeGame = name;
	}
	

}
