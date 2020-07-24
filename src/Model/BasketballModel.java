package Model;

import java.util.ArrayList;

import Controller.Controller;

public class BasketballModel extends SportGames implements GameModelUI {
	// The index in the arrayList meant the round;

	private ArrayList<Integer> firstParticipantsPoints;
	private ArrayList<Integer> secondParticipantsPoints;

	public BasketballModel(String typeGame, String name) {
		super(typeGame,name);
		firstParticipantsPoints = new ArrayList<>();
		secondParticipantsPoints = new ArrayList<>();
		
	}

	
	
	@Override
	public void addPointsToFirstparticipantList(Integer num) {
		firstParticipantsPoints.add(num);
	}

	@Override
	public void clearParticipantPoints() {
		firstParticipantsPoints.clear();
		secondParticipantsPoints.clear();
	}

	@Override
	public ArrayList<Integer> getFirstParticipantsPoints() {
		return firstParticipantsPoints;
	}

	@Override
	public void addPointsToSecondparticipantList(Integer num) {
		secondParticipantsPoints.add(num);
	}

	@Override
	public ArrayList<Integer> getSecondParticipantsPoints() {
		return secondParticipantsPoints;
	}
	
	@Override
	public String checkResults(Controller controller) throws UserExceptions {
		// Note : if the user doesn't insert number in each TextField its insert
		// automatically zero.
		int theFirstParticipantWin = 0;
		int theSecondParticipantWin = 0;
		for (int i = 0; i < getFirstParticipantsPoints().size(); i++) {
			Integer firstParticipantPoint = getFirstParticipantsPoints().get(i);
			Integer secondParticipantPoint = getSecondParticipantsPoints().get(i);
				if (firstParticipantPoint < 0
						|| secondParticipantPoint < 0) {
					throw new UserExceptions("you have to choose positive numbers");
				}
				if (firstParticipantPoint > secondParticipantPoint) {
					theFirstParticipantWin++;
				} else if (firstParticipantPoint == secondParticipantPoint) {
				} else
					theSecondParticipantWin++;
		}

		if (theFirstParticipantWin > theSecondParticipantWin) {
			return participant1;
		} else if(theFirstParticipantWin == theSecondParticipantWin) {
			Controller.err.setContentText("One of the Team has to Win");
			Controller.err.show();
			return "Draw";
		} else {
			return participant2;
		}
	}

}
