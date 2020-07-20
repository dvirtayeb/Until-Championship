package Model;

import java.util.ArrayList;

public class BasketballModel extends SportGames {
	// The index in the arrayList meant the round;

	private ArrayList<Integer> firstParticipantsPoints;
	private ArrayList<Integer> secondParticipantsPoints;

	public BasketballModel(String typeGame, String name) {
		super(typeGame,name);
		firstParticipantsPoints = new ArrayList<>();
		secondParticipantsPoints = new ArrayList<>();
	}

	public void addPointsToFirstparticipantList(Integer num) {
		firstParticipantsPoints.add(num);
	}

	public void clearParticipantPoints() {
		firstParticipantsPoints.clear();
		secondParticipantsPoints.clear();
	}

	public ArrayList<Integer> getFirstParticipantsPoints() {
		return firstParticipantsPoints;
	}

	public void addPointsToSecondparticipantList(Integer num) {
		secondParticipantsPoints.add(num);
	}

	public ArrayList<Integer> getSecondParticipantsPoints() {
		return secondParticipantsPoints;
	}

}
