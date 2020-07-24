package Model;

import javafx.scene.control.Button;

public class SportGames extends Game {

	protected String nameOfSportGame;
	protected String winner;
	protected Button startGame;
	protected boolean hasTennis;
	protected boolean hasBasketball;
	protected boolean hasSoccer;
	protected int gameNumber;
	private boolean oneGamePlayed = false;

	public SportGames(String typeGame,String kindSportGame) {
		super(typeGame);
		nameOfSportGame = kindSportGame;
	}

	public SportGames(String typeGame,String kindSportGame, String winner, Button bStartGame, int gameCounter) {
		super(typeGame);
		nameOfSportGame = kindSportGame;
		this.winner = winner;
		this.startGame = bStartGame;
		gameNumber = gameCounter;
		oneGamePlayed = false;
	}

	public void setGame(String name) {
		switch (name) {
		case "Tennis":
			hasTennis = true;
			hasBasketball = false;
			hasSoccer = false;
			break;
		case "Basketball":
			hasBasketball = true;
			hasSoccer = false;
			hasTennis = false;
			break;
		case "Soccer":
			hasSoccer = true;
			hasTennis = false;
			hasBasketball = false;
			break;
		}
	}

	public String getName() {
		return nameOfSportGame;
	}

	public int getGameNumber() {
		return gameNumber;
	}

	public void setName(String name) {
		this.nameOfSportGame = name;
	}

	public String getWinner() {
		if (winner != null && !winner.isEmpty()) {
			return "Win: " + winner;
		}
		return "";

	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public Button getStartGame() {
		return startGame;
	}

	public void setStartGame(Button startGame) {
		this.startGame = startGame;
	}
	
	public boolean isOneGamePlayed() {
		return oneGamePlayed;
	}

	public void setOneGamePlayed(boolean oneGamePlayed) {
		this.oneGamePlayed = oneGamePlayed;
	}
	

	public String toString() {
		String str = nameOfSportGame+", the Winner: "+winner+",Number Game: "+gameNumber;
		return str;
	}

}
