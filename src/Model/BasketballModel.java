package Model;

import java.util.ArrayList;

public class BasketballModel extends Game {

	private ArrayList<Integer> firstParticipantsPoints;
	private ArrayList<Integer> secondParticipantsPoints;

	public BasketballModel(String name) {
		super(name);
		firstParticipantsPoints = new ArrayList<>();
		secondParticipantsPoints = new ArrayList<>();

	}

	public void addPointsToFirstparticipantList(Integer num) {
		firstParticipantsPoints.add(num);
	}

	public void clearParticipantsPoints() {
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
