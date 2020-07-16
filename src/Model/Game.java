package Model;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Game {

	protected String name;
	protected String winner;
	protected Button startGame;
	protected boolean hasTennis;
	protected boolean hasBasketball;
	protected boolean hasSoccer;
	protected int gameNumber;

	public Game(String name) {
		this.name = name;
		hasTennis = true;
		hasBasketball = false;
		hasSoccer = false;
	}

	public Game(String name, String winner, Button bStartGame, int gameCounter) {
		this.name = name;
		this.winner = winner;
		this.startGame = bStartGame;
		gameNumber = gameCounter;
	}

	public void setGame(String name, boolean value) {
		switch (name) {
		case "Tennis":
			hasTennis = value;
			hasBasketball = false;
			hasSoccer = false;
			break;
		case "Basketball":
			hasBasketball = value;
			hasSoccer = false;
			hasTennis = false;
			break;
		case "Soccer":
			hasSoccer = value;
			hasTennis = false;
			hasBasketball = false;
			break;
		}
	}

	public String getName() {
		return name;
	}

	public int getGameNumber() {
		return gameNumber;
	}

	public void setName(String name) {
		this.name = name;
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

}
