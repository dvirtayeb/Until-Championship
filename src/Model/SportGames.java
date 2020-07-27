package Model;

import javafx.scene.control.Button;

public class SportGames extends Game {

	protected String nameOfSportGame;
	protected String winner;
	protected Button startGame;
	protected int gameNumber;
	protected String participant1, participant2;

	public SportGames(String typeGame, String kindSportGame) {
		super(typeGame);
		nameOfSportGame = kindSportGame;
	}

	public SportGames(String typeGame, String kindSportGame, String winner, Button bStartGame, int gameCounter) {
		super(typeGame);
		nameOfSportGame = kindSportGame;
		this.winner = winner;
		this.startGame = bStartGame;
		gameNumber = gameCounter;
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

	public String getWinnerName() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}
	// Use for the treeTableView-javaFx:
	public Button getStartGame() {
		return startGame;
	}

	public String toString() {
		String str = nameOfSportGame + ", the Winner: " + winner + ",Number Game: " + gameNumber;
		return str;
	}
}
