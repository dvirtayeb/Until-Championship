package Model;

import java.util.ArrayList;

import Controller.Controller;

public interface GameModelUI {

	public abstract void clearParticipantPoints();
	public abstract void addPointsToFirstparticipantList(Integer pointFromTeam1);
	public abstract void addPointsToSecondparticipantList(Integer pointFromTeam2);
	public ArrayList<Integer> getFirstParticipantsPoints();
	public ArrayList<Integer> getSecondParticipantsPoints();
	String checkResults(Controller controller) throws UserExceptions;
	
}
