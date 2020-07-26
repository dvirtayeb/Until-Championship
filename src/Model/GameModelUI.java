package Model;

import java.util.ArrayList;

import Controller.Controller;

public interface GameModelUI {

	public abstract void clearParticipantPoints();

	public abstract void addPointsToFirstparticipantList(Integer pointFromTeam1);

	public abstract void addPointsToSecondparticipantList(Integer pointFromTeam2);

	public ArrayList<Integer> getFirstParticipantsPoints();

	public ArrayList<Integer> getSecondParticipantsPoints();
	
	void setParticipants(String participant1, String participant2);

	String checkResults(Controller controller) throws UserExceptions;

}
