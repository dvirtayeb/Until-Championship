package Model;

import java.util.ArrayList;
import java.util.Optional;

import Controller.Controller;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;

public class SoccerModel extends SportGames implements GameModelUI {
	// The index in the arrayList meant the round;

	private ArrayList<Integer> firstParticipantsPoints;
	private ArrayList<Integer> secondParticipantsPoints;

	public SoccerModel(String typeGame, String name) {
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
		for (int i = 0; i < secondParticipantsPoints.size(); i++) {
//			try {
			if (getFirstParticipantsPoints().get(i) < 0 || getSecondParticipantsPoints().get(i) < 0) {
//					noProblem = false;
				throw new UserExceptions("you have to choose positive numbers");
			} else if (getFirstParticipantsPoints().get(i) > getSecondParticipantsPoints().get(i)) {
				theFirstParticipantWin++;
			} else if (getFirstParticipantsPoints().get(i) < getSecondParticipantsPoints().get(i)) {
				theSecondParticipantWin++;
			}
			if (theFirstParticipantWin > theSecondParticipantWin) {
				return participant1;
			} else if (theFirstParticipantWin < theSecondParticipantWin) {
				return participant2;
			} else {
				// NOTE: Extra Time Match!
				TextInputDialog dialog = new TextInputDialog("0");
				TextInputDialog dialog2 = new TextInputDialog("0");
				dialog.setTitle("Extra-Time");
				dialog.setHeaderText("One more match, please insert points \nTeam 1:");
				dialog2.setTitle("Extra-Time");
				dialog2.setHeaderText("One more match, please insert points \nTeam 2:");
				Optional<String> resultTeam1 = dialog.showAndWait();
				int num = 0;
				if (resultTeam1.isPresent()) {
					try {
						if (Integer.parseInt(resultTeam1.get()) < 0) {
							controller.setNoProblem(false);
							throw new UserExceptions("you have to choose positive numbers");
						}
						num = Integer.parseInt(resultTeam1.get());
//						} catch (UserExceptions ue) {
//							err.setContentText("you have to choose positive numbers");
//							err.show();
//							return "-1";
					} catch (Exception e) {
						controller.setNoProblem(false);
						Controller.err.setContentText("you didn't insert a number..");
						Controller.err.show();
						return "-1";
					}
				}
				Optional<String> resultTeam2 = dialog2.showAndWait();
				int num2 = 0;
				if (resultTeam2.isPresent()) {
					try {
						if (Integer.parseInt(resultTeam2.get()) < 0) {
//								noProblem = false;
							throw new UserExceptions("you have to choose positive numbers");
						}
						num2 = Integer.parseInt(resultTeam2.get());
//						} catch (UserExceptions ue) {
//							err.setContentText("you have to choose positive numbers");
//							err.show();
//							return "-1";
					} catch (Exception e) {
						Controller.err.setContentText("you didn't insert a number..");
						Controller.err.show();
						return "-1";
					}
					if (num > num2) {
						theFirstParticipantWin++;
						return participant1;
					} else if (num < num2) {
						theSecondParticipantWin++;
						return participant2;
					} else {
						// NOTE: Penalty Kicks !
						int penaltyTeam1Score = 0;
						int penaltyTeam2Score = 0;
						Alert penaltyTeam1 = new Alert(AlertType.CONFIRMATION);
						penaltyTeam1.setTitle("Penalty Kicks");
						penaltyTeam1.setHeaderText("Penalty - Kicks Time!\nTeam1: did you score? ");
						penaltyTeam1.setContentText("Choose your option, YES/NO ");

						ButtonType buttonTypeOne = new ButtonType("YES");
						ButtonType buttonTypeTwo = new ButtonType("NO");

						penaltyTeam1.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
						do {
							Optional<ButtonType> resultPenaltyTeam1 = penaltyTeam1.showAndWait();
							if (resultPenaltyTeam1.get() == buttonTypeOne) {
								penaltyTeam1Score++;
							}
							Alert penaltyTeam2 = new Alert(AlertType.CONFIRMATION);
							penaltyTeam2.setTitle("Penalty Kicks");
							penaltyTeam2.setHeaderText("Penalty - Kicks Time!\nTeam2: did you score? ");
							penaltyTeam2.setContentText("Choose your option, YES/NO ");
							penaltyTeam2.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
							Optional<ButtonType> resultPenaltyTeam2 = penaltyTeam2.showAndWait();
							if (resultPenaltyTeam2.get() == buttonTypeOne) {
								penaltyTeam2Score++;
							}
							if (penaltyTeam1Score > penaltyTeam2Score) {
								theFirstParticipantWin++;
							} else if (penaltyTeam1Score < penaltyTeam2Score) {
								theSecondParticipantWin++;
							}
						} while (penaltyTeam1Score == penaltyTeam2Score);
					}
				}
			}
//			} catch (UserExceptions ue) {
//				err.setContentText("you have to choose positive numbers");
//				err.show();
//				return "-1";
		}
		if (theFirstParticipantWin > theSecondParticipantWin) {
			return participant1;
		} else if (theFirstParticipantWin == theSecondParticipantWin) {
			controller.setNoProblem(false);
			Controller.err.setContentText("One of the Team has to Win.");
			Controller.err.show();
			return "Draw";
		} else {
			return participant2;
		}
	}
}
