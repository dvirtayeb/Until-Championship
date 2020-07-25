package Model;

import java.util.ArrayList;

import Controller.Controller;

public class TennisModel extends SportGames implements GameModelUI{
	
	public ArrayList<Integer> firstParticipantsPoints;
	public ArrayList<Integer> secondParticipantsPoints;
	
	public TennisModel(String typeGame, String name) {
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
					if (firstParticipantPoint < 0 || secondParticipantPoint < 0) {
						throw new UserExceptions("you have to choose positive numbers");
					}
					if (firstParticipantPoint > secondParticipantPoint)
						theFirstParticipantWin++;
					else if (firstParticipantPoint == secondParticipantPoint) {
						
					}
					else 
						theSecondParticipantWin++;
			}
			if (theFirstParticipantWin >= 3 + theSecondParticipantWin) 
				return participant1;
			else if(theFirstParticipantWin + 3<= theSecondParticipantWin)
				return participant2;
			else if(theFirstParticipantWin == theSecondParticipantWin) {
				controller.setNoProblem(false);
				Controller.err.setContentText("One of the Team has to Win");
				Controller.err.show();
				return "Draw";
			}else {
				controller.setNoProblem(false);
				Controller.err.setContentText("The difference has to be by 3 victories");
				Controller.err.show();
				return "-1";
			}
	}


}
