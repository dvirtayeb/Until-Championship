package Model;

import java.util.ArrayList;

import Controller.Controller;

public class TennisModel extends SportGames implements GameModelUI {
	private ArrayList<Integer> firstParticipantsPoints;
	private ArrayList<Integer> secondParticipantsPoints;

	public TennisModel(String typeGame, String name) {
		super(typeGame, name);
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
		for (int i = 0; i < firstParticipantsPoints.size(); i++) {
			Integer firstParticipantPoint = firstParticipantsPoints.get(i);
			Integer secondParticipantPoint = secondParticipantsPoints.get(i);
			if (firstParticipantPoint < 0 || secondParticipantPoint < 0) {
				throw new UserExceptions("you have to choose positive numbers");
			}
			if (firstParticipantPoint > secondParticipantPoint)
				theFirstParticipantWin++;
			else if (firstParticipantPoint == secondParticipantPoint) {

			} else if (secondParticipantPoint > firstParticipantPoint)
				theSecondParticipantWin++;

		}
		if ((theFirstParticipantWin >= 3 + theSecondParticipantWin) ||
				(theFirstParticipantWin ==3 && theSecondParticipantWin==2)) {
			winner = participant1;
			return winner;
		}else if ((theFirstParticipantWin + 3 <= theSecondParticipantWin) ||
				(theFirstParticipantWin==2 && theSecondParticipantWin==3)) {
			winner = participant2;
			return winner;
		}else if (theFirstParticipantWin == theSecondParticipantWin) {
			controller.setNoProblem(false);
			Controller.err.setContentText("One of the Team has to Win");
			Controller.err.show();
			return "Draw";
		} else {
			controller.setNoProblem(false);
			Controller.err.setContentText("The difference has to be by 3 victories");
			Controller.err.show();
			return "-1";
		}
	}

	public void setParticipants(String participant1, String participant2) {
		this.participant1 = participant1;
		this.participant2 = participant2;
	}
}
