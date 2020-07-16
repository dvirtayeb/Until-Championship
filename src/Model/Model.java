package Model;

import java.util.ArrayList;

import javafx.scene.layout.BorderPane;

public class Model {
	private Game theGame;
	private ArrayList<String> participantsList;
	private ArrayList<Game> gamesList;
	private ArrayList<String> winnerList;
	private String kindGame;

	public Model() {
		theGame = new Game("Game");
		participantsList = new ArrayList<>();
		gamesList = new ArrayList<>();

		winnerList = new ArrayList<>();
	}

	public void setGame(String name, boolean value) {
		theGame.setGame(name, value);
	}

	public void updateGame(String kindGame) {
		if (kindGame == "Tennis") {
			this.kindGame = "Tennis";
			theGame = new TennisModel("Tennis");
		} else if (kindGame == "BasketBall") {
			theGame = new BasketballModel("BasketBall");
			this.kindGame = "BasketBall";
		} else if (kindGame == "Soccer") {
			theGame = new SoccerModel("Soccer");
			this.kindGame = "Soccer";
		}
	}

	public void addParticipantToList(String value) {
		participantsList.add(value);
	}

	public ArrayList<String> getParticipantsList() {
		return participantsList;
	}

	public Game getTheGame() {
		return theGame;
	}

	public int getloctionparticipantlist(String value) {
		int counter = participantsList.indexOf(value);
		return counter;
	}

	public void addGameToList(Game game) {
		gamesList.add(game);
	}

	public ArrayList<Game> getGamesList() {
		return gamesList;
	}

	public void addToWinnerList(String name) {
		winnerList.add(name);
	}

	public void setWinnerList(ArrayList<String> winnerList) {
		this.winnerList = winnerList;
	}

	public void clearWinnerList() {
		winnerList.clear();
	}

	public ArrayList<String> getWinnerList() {
		return winnerList;
	}

	public String getKindGame() {
		return kindGame;
	}

	public void setKindGame(String kindGame) {
		this.kindGame = kindGame;
	}

}
