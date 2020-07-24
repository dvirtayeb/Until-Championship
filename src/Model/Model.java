package Model;

import java.util.ArrayList;

public class Model {
	private Game theGame;
	private ArrayList<String> participantsList;
	private ArrayList<SportGames> gamesList;
	private ArrayList<String> winnerList;
	private String kindGame;

	public Model() {
		theGame = new SportGames("Sport - Games","Sport - Games");
		participantsList = new ArrayList<>();
		gamesList = new ArrayList<>();

		winnerList = new ArrayList<>();
	}

	public void setGame(String name) {
		theGame.setGame(name);
	}

	public void updateGame(String kindGame) {
		if (kindGame == "Tennis") {
			this.kindGame = "Tennis";
			theGame = new TennisModel("Sport - Games","Tennis");
		} else if (kindGame == "BasketBall") {
			theGame = new BasketballModel("Sport - Games","BasketBall");
			this.kindGame = "BasketBall";
		} else if (kindGame == "Soccer") {
			theGame = new SoccerModel("Sport - Games","Soccer");
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

	public void addGameToList(SportGames game) {
		gamesList.add(game);
	}

	public ArrayList<SportGames> getGamesList() {
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
	public void clearGameList() {
		gamesList.clear();
	}

	public String getKindGame() {
		return kindGame;
	}

	public void setKindGame(String kindGame) {
		this.kindGame = kindGame;
	}

}
