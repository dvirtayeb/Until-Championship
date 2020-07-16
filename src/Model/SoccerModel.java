package Model;

public class SoccerModel extends Game {
	private int firstHalfTeam1;
	private int secondHalfTeam1;
	private int thirdHalfTeam1;
	private int firstHalfTeam2;
	private int secondHalfTeam2;
	private int thirdHalfTeam2;
	private int penaltyKickTeam1;
	private int penaltyKickTeam2;

	public int getFirstHalfTeam1() {
		return firstHalfTeam1;
	}

	public void setFirstHalfTeam1(int firstHalfTeam1) {
		this.firstHalfTeam1 = firstHalfTeam1;
	}

	public int getSecondHalfTeam1() {
		return secondHalfTeam1;
	}

	public void setSecondHalfTeam1(int secondHalfTeam1) {
		this.secondHalfTeam1 = secondHalfTeam1;
	}

	public int getThirdHalfTeam1() {
		return thirdHalfTeam1;
	}

	public void setThirdHalfTeam1(int thirdHalfTeam1) {
		this.thirdHalfTeam1 = thirdHalfTeam1;
	}

	public int getFirstHalfTeam2() {
		return firstHalfTeam2;
	}

	public void setFirstHalfTeam2(int firstHalfTeam2) {
		this.firstHalfTeam2 = firstHalfTeam2;
	}

	public int getSecondHalfTeam2() {
		return secondHalfTeam2;
	}

	public void setSecondHalfTeam2(int secondHalfTeam2) {
		this.secondHalfTeam2 = secondHalfTeam2;
	}

	public int getThirdHalfTeam2() {
		return thirdHalfTeam2;
	}

	public void setThirdHalfTeam2(int thirdHalfTeam2) {
		this.thirdHalfTeam2 = thirdHalfTeam2;
	}

	public int getPenaltyKickTeam1() {
		return penaltyKickTeam1;
	}

	public void setPenaltyKickTeam1(int penaltyKickTeam1) {
		this.penaltyKickTeam1 = penaltyKickTeam1;
	}

	public int getPenaltyKickTeam2() {
		return penaltyKickTeam2;
	}

	public void setPenaltyKickTeam2(int penaltyKickTeam2) {
		this.penaltyKickTeam2 = penaltyKickTeam2;
	}

	public SoccerModel(String name) {
		super(name);
		firstHalfTeam1 = 0;
		secondHalfTeam1 = 0;
		thirdHalfTeam1 = 0;
		firstHalfTeam2 = 0;
		secondHalfTeam2 = 0;
		thirdHalfTeam2 = 0;
		penaltyKickTeam1 = 0;
		penaltyKickTeam2 = 0;
	}

}
