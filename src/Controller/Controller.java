package Controller;

import Model.Model;
import Model.TennisModel;

import java.util.Optional;

import Model.BasketballModel;
import Model.SoccerModel;
import Model.UserExceptions;
import View.Basketball;
import View.Championship;
import View.Soccer;
import View.Tennis;
import View.View;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class Controller {

	private Model theModel;
	private TennisModel tennisModel;
	private BasketballModel basketBallModel;
	private SoccerModel SoccerModel;

	private static Alert err;
	private static Alert begin;
//	private static Alert change;
	private static Alert winner;

	private boolean finishQuarterGames = false;
	private boolean finishSemiFinalsGames = false;
	private boolean noProblem = true;

	private View theView;
	private Championship championship;
	private Tennis startTennisGame;
	private Basketball startBasketBallGame;
	private Soccer startSoccerGame;

	private String theWinner;
	private String kindGame;

	public Controller(Model theModel, View theView) throws UserExceptions {
		this.theModel = theModel;
		this.theView = theView;
		kindGame = theView.getKindRB(); // Default
		err = new Alert(AlertType.ERROR, "", ButtonType.OK);
		begin = new Alert(AlertType.INFORMATION, "Let's start, press on the arrow where 'Sport - Games' for to start"
				+ "\n#Tip: you can decide who will comptition in the next stage by choosing which team to play first",
				ButtonType.OK);
//		change = new Alert(AlertType.INFORMATION, "The game changed", ButtonType.OK);
		winner = new Alert(AlertType.INFORMATION, "The winner is: ", ButtonType.OK);

		// Change Game : Tennis,Basketball,Soccer
		ChangeListener<Toggle> chl = new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				kindGame = theView.getKindRB();
				theModel.updateGame(kindGame);
//				change.show();
			}

		};
		theView.addChangeListenerToToggleGroup(chl);

		// ADD Participant
		EventHandler<ActionEvent> eventAddParticipant = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (theModel.getParticipantsList().size() < 8)
						addParticipant();
					else {
						throw new UserExceptions("");
					}
				} catch (UserExceptions Ue) {
					err.setContentText("you try to insert more then 8 participant");
					err.show();
				}

			}

		};
		theView.addEventToSubmit(eventAddParticipant, theView.getBtnAddParticipantName());

		// Start Championship
		EventHandler<ActionEvent> startChampionship = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (theModel.getParticipantsList().size() == 8) {
						startChampionshipFunction();
						begin.show();
					} else {
						throw new UserExceptions("");
					}
				} catch (UserExceptions Ue) {
					err.setContentText("you didn't insert 8 participants");
					err.show();
				}

			}

		};
		theView.addEventToSubmit(startChampionship, theView.getBtnStartChampionship());

	}

	private boolean addParticipant() throws UserExceptions {
		String participant = "" + theView.getTfParticipantName();
		theModel.addParticipantToList(participant);
		int participantsCounter = theView.getListView().getItems().size();
		theView.addListView(theModel.getParticipantsList().get(participantsCounter));
		return true;
	}

	private boolean startChampionshipFunction() throws UserExceptions {
		theModel.clearWinnerList();
		tennisModel = new TennisModel("Sport - Games", "Tennis");
		basketBallModel = new BasketballModel("Sport - Games", "BasketBall");
		SoccerModel = new SoccerModel("Sport - Games", "Soccer");
		kindGame = theView.getKindRB();
		theModel.updateGame(kindGame);
		for (int i = 0; i < 8; i++) {
			theModel.addToWinnerList(" ");
		}
		// Page Championship :
		championship = new Championship(new Stage(), theModel);
		championship.addChampionship();

		EventHandler<ActionEvent> eventStartGame = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				championship.getAllButton().get(0).setId("gameOn");
				championship.getAllButton().get(1).setId("game2");
				championship.getAllButton().get(2).setId("game3");
				championship.getAllButton().get(3).setId("game4");
				championship.getAllButton().get(4).setId("game5");
				championship.getAllButton().get(5).setId("game6");
//				if(!championship.getGame().isOneGamePlayed()) {
				startGame(kindGame);
//					championship.getGame().setOneGamePlayed(true);
//				}

			}

		};

		championship.addEventToSubmit(eventStartGame, championship.getAllButton().get(0));

		EventHandler<ActionEvent> eventStartGame2 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				championship.getAllButton().get(0).setId("game1");
				championship.getAllButton().get(1).setId("gameOn");
				championship.getAllButton().get(2).setId("game3");
				championship.getAllButton().get(3).setId("game4");
				championship.getAllButton().get(4).setId("game5");
				championship.getAllButton().get(5).setId("game6");
				startGame(kindGame);
//					championship.getGame().setOneGamePlayed(true);

			}

		};

		championship.addEventToSubmit(eventStartGame2, championship.getAllButton().get(1));

		EventHandler<ActionEvent> eventStartGame3 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				championship.getAllButton().get(0).setId("game1");
				championship.getAllButton().get(1).setId("game2");
				championship.getAllButton().get(2).setId("gameOn");
				championship.getAllButton().get(3).setId("game4");
				championship.getAllButton().get(4).setId("game5");
				championship.getAllButton().get(5).setId("game6");
//				if(!championship.getGame().isOneGamePlayed()) {
				startGame(kindGame);
//					championship.getGame().setOneGamePlayed(true);
//				}
			}

		};

		championship.addEventToSubmit(eventStartGame3, championship.getAllButton().get(2));

		EventHandler<ActionEvent> eventStartGame4 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				championship.getAllButton().get(0).setId("game1");
				championship.getAllButton().get(1).setId("game2");
				championship.getAllButton().get(2).setId("game3");
				championship.getAllButton().get(3).setId("gameOn");
				championship.getAllButton().get(4).setId("game5");
				championship.getAllButton().get(5).setId("game6");
//				if(!championship.getGame().isOneGamePlayed()) {
				startGame(kindGame);
//					championship.getGame().setOneGamePlayed(true);
//				}
			}

		};

		championship.addEventToSubmit(eventStartGame4, championship.getAllButton().get(3));

		EventHandler<ActionEvent> eventStartGame5 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				championship.getAllButton().get(0).setId("game1");
				championship.getAllButton().get(1).setId("game2");
				championship.getAllButton().get(2).setId("game3");
				championship.getAllButton().get(3).setId("game4");
				championship.getAllButton().get(4).setId("gameOn");
				championship.getAllButton().get(5).setId("game6");
				if (finishQuarterGames)
					startGame(kindGame);
				else {
					err.setContentText("you have to finish the QuarterGames");
					err.show();
				}
			}

		};

		championship.addEventToSubmit(eventStartGame5, championship.getAllButton().get(4));

		EventHandler<ActionEvent> eventStartGame6 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				championship.getAllButton().get(0).setId("game1");
				championship.getAllButton().get(1).setId("game2");
				championship.getAllButton().get(2).setId("game3");
				championship.getAllButton().get(3).setId("game4");
				championship.getAllButton().get(4).setId("game5");
				championship.getAllButton().get(5).setId("gameOn");
				if (finishQuarterGames)
					startGame(kindGame);
				else {
					err.setContentText("you have to finish the QuarterGames");
					err.show();
				}
			}

		};

		championship.addEventToSubmit(eventStartGame6, championship.getAllButton().get(5));

		EventHandler<ActionEvent> eventStartGame7 = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				championship.getAllButton().get(0).setId("game1");
				championship.getAllButton().get(1).setId("game2");
				championship.getAllButton().get(2).setId("game3");
				championship.getAllButton().get(3).setId("game4");
				championship.getAllButton().get(4).setId("game5");
				championship.getAllButton().get(5).setId("game6");
				championship.getAllButton().get(6).setId("gameOn");
				if (finishSemiFinalsGames)
					startGame(kindGame);
				else {
					err.setContentText("you have to finish the Semi-Finals");
					err.show();
				}
			}

		};

		championship.addEventToSubmit(eventStartGame7, championship.getAllButton().get(6));
		return true;
	}

	private boolean startGame(String kindGame) {
		// Page Tennis
		if (kindGame.equals("Tennis")) {
			startTennisGame = new Tennis(new Stage(), theModel, championship);
			EventHandler<ActionEvent> eventDoneTennisGame = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					tennisModel.clearParticipantPoints();
					noProblem = true;
					for (int i = 0; i < 5; i++) {
						tennisModel.addPointsToFirstparticipantList(startTennisGame.getPointFromTeam1(i));
						tennisModel.addPointsToSecondparticipantList(startTennisGame.getPointFromTeam2(i));
					}
					theWinner = checkTennisResult();
					updateTheWinner(theModel, theWinner);
					int counter = 0;
					for (int i = 0; i < theModel.getWinnerList().size(); i++) {
						if (theModel.getWinnerList().get(i) != " ")
							counter++;
						if (counter == 4 && noProblem) {
							finishQuarterGames = true;
							updateTheQuarterFinalsGames(theModel, theWinner);
							championship.update();
						} else if (counter == 6 && noProblem) {
							finishSemiFinalsGames = true;
							updateTheFinalGames(theModel, theWinner);
							championship.update();
						} else if (counter == 7 && noProblem) {
							winner.setContentText("The winner is: " + theWinner);
							winner.show();
						}
					}
					championship.update();
					handleCloseButtonActionTennis();
				}
			};
			championship.addEventToSubmit(eventDoneTennisGame, startTennisGame.getBtnDone());
		}
		// Page BasketBall
		else if (kindGame.equals("BasketBall")) {
			startBasketBallGame = new Basketball(new Stage(), theModel, championship);
			EventHandler<ActionEvent> eventDoneBasketballGame = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					basketBallModel.clearParticipantPoints();
					noProblem = true;
					for (int i = 0; i < 4; i++) {
						basketBallModel.addPointsToFirstparticipantList(startBasketBallGame.getPointFromTeam1(i));
						basketBallModel.addPointsToSecondparticipantList(startBasketBallGame.getPointFromTeam2(i));
					}
					theWinner = checkBasketBallResualt();
					updateTheWinner(theModel, theWinner);
					int counter = 0;
					for (int i = 0; i < theModel.getWinnerList().size(); i++) {
						if (theModel.getWinnerList().get(i) != " ")
							counter++;
						if (counter == 4 && noProblem) {
							finishQuarterGames = true;
							updateTheQuarterFinalsGames(theModel, theWinner);
							championship.update();
						} else if (counter == 6 && noProblem) {
							finishSemiFinalsGames = true;
							updateTheFinalGames(theModel, theWinner);
							championship.update();
						} else if (counter == 7 && noProblem) {
							winner.setContentText("The winner is: " + theWinner);
							winner.show();
						}
					}
					championship.update();
					handleCloseButtonActionBasketball();
				}
			};
			championship.addEventToSubmit(eventDoneBasketballGame, startBasketBallGame.getBtnDone());

			// Page Soccer
		} else {
			startSoccerGame = new Soccer(new Stage(), theModel, championship);
			EventHandler<ActionEvent> eventDoneSoccerGame = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					SoccerModel.clearParticipantPoints();
					noProblem = true;
					for (int i = 0; i < 2; i++) {
						SoccerModel.addPointsToFirstparticipantList(startSoccerGame.getPointFromTeam1(i));
						SoccerModel.addPointsToSecondparticipantList(startSoccerGame.getPointFromTeam2(i));
					}
					theWinner = checkSoccerResualt();
					updateTheWinner(theModel, theWinner);
					int counter = 0;
					for (int i = 0; i < theModel.getWinnerList().size(); i++) {
						if (theModel.getWinnerList().get(i) != " ")
							counter++;
						if (counter == 4 && noProblem) {
							finishQuarterGames = true;
							updateTheQuarterFinalsGames(theModel, theWinner);
							championship.update();
						} else if (counter == 6 && noProblem) {
							finishSemiFinalsGames = true;
							updateTheFinalGames(theModel, theWinner);
							championship.update();
						} else if (counter == 7 && noProblem) {
							winner.setContentText("The winner is: " + theWinner);
							winner.show();
						}
					}
					championship.update();
					handleCloseButtonActionSoccer();
				}

			};
			championship.addEventToSubmit(eventDoneSoccerGame, startSoccerGame.getBtnDone());
		}
		return true;
	}

	private void handleCloseButtonActionTennis() { // close the points window (Tennis)
		Stage stage = (Stage) startTennisGame.getBtnDone().getScene().getWindow();
		stage.close();
	}

	private void handleCloseButtonActionBasketball() { // close the points window (Basketball)
		Stage stage = (Stage) startBasketBallGame.getBtnDone().getScene().getWindow();
		stage.close();
	}

	private void handleCloseButtonActionSoccer() { // close the points window (Soccer)
		Stage stage = (Stage) startSoccerGame.getBtnDone().getScene().getWindow();
		stage.close();
	}

	// NOTE: Updates:
	private boolean updateTheWinner(Model theModel, String name) {
		if (!name.equals("-1") || !name.equals("Draw")) {
			for (int i = 0; i < theModel.getGamesList().size(); i++) {
				if (championship.getAllButton().get(theModel.getGamesList().get(i).getGameNumber()).getId()
						.equals("gameOn") && noProblem) {
					theModel.getGamesList().get(i).setWinner(name);
					return true;
				}
			}
		}
		return false;
	}

	private boolean updateTheQuarterFinalsGames(Model theModel, String name) {
		theModel.getGamesList().get(4)
				.setName(theModel.getWinnerList().get(8) + " VS " + theModel.getWinnerList().get(9));
		theModel.getGamesList().get(5)
				.setName(theModel.getWinnerList().get(10) + " VS " + theModel.getWinnerList().get(11));
		return true;
	}

	private boolean updateTheFinalGames(Model theModel, String name) {
		theModel.getGamesList().get(6)
				.setName(theModel.getWinnerList().get(12) + " VS " + theModel.getWinnerList().get(13));
		return true;
	}

	// NOTE : Check Winner functions (Tennis, Basketball, Soccer):
	private String checkTennisResult() {
		// Note : if the user doesn't insert number in each TextField its insert
		// automatically zero.

		int theFirstParticipantWin = 0;
		int theSecondParticipantWin = 0;
		for (int i = 0; i < tennisModel.getFirstParticipantsPoints().size(); i++) {
			try {
				if (tennisModel.getFirstParticipantsPoints().get(i) < 0) {
					noProblem = false;
					throw new UserExceptions("you have to choose positive numbers");
				} else if (tennisModel.getFirstParticipantsPoints().get(i) >= 3
						+ tennisModel.getSecondParticipantsPoints().get(i)) {
					if (tennisModel.getFirstParticipantsPoints().get(i) > tennisModel.getSecondParticipantsPoints()
							.get(i))
						theFirstParticipantWin++;
					else if (tennisModel.getFirstParticipantsPoints().get(i) == tennisModel
							.getSecondParticipantsPoints().get(i)) {

					} else
						theSecondParticipantWin++;
				} else if (tennisModel.getSecondParticipantsPoints().get(i) >= 3
						+ tennisModel.getFirstParticipantsPoints().get(i)
						|| tennisModel.getSecondParticipantsPoints().get(i) > 0) {
					if (tennisModel.getFirstParticipantsPoints().get(i) < tennisModel.getSecondParticipantsPoints()
							.get(i))
						theSecondParticipantWin++;
					else if (tennisModel.getFirstParticipantsPoints().get(i) == tennisModel
							.getSecondParticipantsPoints().get(i)) {

					} else
						theFirstParticipantWin++;
				}
			} catch (UserExceptions ue) {
				if (tennisModel.getFirstParticipantsPoints().get(i) < 0) {
					err.setContentText("you have to choose positive numbers");
					err.show();
				}
				return "-1";
			}
		}
		try {
			if (theFirstParticipantWin < 3 && theSecondParticipantWin < 3) {
				noProblem = false;
				throw new UserExceptions("The participant have to win by difference by 3");
			} else {
				if (theFirstParticipantWin > theSecondParticipantWin) {
					theModel.addToWinnerList(startTennisGame.getParticipants().get(0).getText());
					return startTennisGame.getParticipants().get(0).getText();
				} else {
					theModel.addToWinnerList(startTennisGame.getParticipants().get(1).getText());
					return startTennisGame.getParticipants().get(1).getText();
				}
			}
		} catch (UserExceptions ue1) {
			err.setContentText("The participant have to win by difference by 3");
			err.show();
			return "-1";
		}

	}

	private String checkBasketBallResualt() {
		// Note : if the user doesn't insert number in each TextField its insert
		// automatically zero.
		int theFirstParticipantWin = 0;
		int theSecondParticipantWin = 0;
		for (int i = 0; i < basketBallModel.getFirstParticipantsPoints().size(); i++) {
			try {
				if (basketBallModel.getFirstParticipantsPoints().get(i) < 0
						|| basketBallModel.getSecondParticipantsPoints().get(i) < 0) {
					noProblem = false;
					throw new UserExceptions("you have to choose positive numbers");
				}
				if (basketBallModel.getFirstParticipantsPoints().get(i) > basketBallModel.getSecondParticipantsPoints()
						.get(i)) {
					theFirstParticipantWin++;
				} else if (basketBallModel.getFirstParticipantsPoints().get(i) == basketBallModel
						.getSecondParticipantsPoints().get(i)) {
				} else
					theSecondParticipantWin++;

			} catch (UserExceptions ue) {
				err.setContentText("you have to choose positive numbers");
				err.show();
				return "-1";
			}
		}

		if (theFirstParticipantWin > theSecondParticipantWin) {
			theModel.addToWinnerList(startBasketBallGame.getParticipants().get(0).getText());
			return startBasketBallGame.getParticipants().get(0).getText();
		} else if (theFirstParticipantWin == theSecondParticipantWin) {
			noProblem = false;
			err.setContentText("One of the Team has to Win.");
			err.show();
			return "Draw";
		} else {
			theModel.addToWinnerList(startBasketBallGame.getParticipants().get(1).getText());
			return startBasketBallGame.getParticipants().get(1).getText();
		}
	}

	private String checkSoccerResualt() {
		// Note : if the user doesn't insert number in each TextField its insert
		// automatically zero.
		int theFirstParticipantWin = 0;
		int theSecondParticipantWin = 0;
		for (int i = 0; i < SoccerModel.getFirstParticipantsPoints().size(); i++) {
			try {
				if (SoccerModel.getFirstParticipantsPoints().get(i) < 0
						|| SoccerModel.getSecondParticipantsPoints().get(i) < 0) {
					noProblem = false;
					throw new UserExceptions("you have to choose positive numbers");
				} else if (SoccerModel.getFirstParticipantsPoints().get(i) == SoccerModel.getSecondParticipantsPoints()
						.get(i)) {

				} else if (SoccerModel.getFirstParticipantsPoints().get(i) > SoccerModel.getSecondParticipantsPoints()
						.get(i)) {
					theFirstParticipantWin++;
				} else if (SoccerModel.getFirstParticipantsPoints().get(i) < SoccerModel.getSecondParticipantsPoints()
						.get(i)) {
					theSecondParticipantWin++;
				}
				if (theFirstParticipantWin > theSecondParticipantWin) {
					theModel.addToWinnerList(startSoccerGame.getParticipants().get(0).getText());
					return startSoccerGame.getParticipants().get(0).getText();
				} else if (theFirstParticipantWin < theSecondParticipantWin) {
					theModel.addToWinnerList(startSoccerGame.getParticipants().get(1).getText());
					return startSoccerGame.getParticipants().get(1).getText();
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
								noProblem = false;
								throw new UserExceptions("you have to choose positive numbers");
							}
							num = Integer.parseInt(resultTeam1.get());
						} catch (UserExceptions ue) {
							err.setContentText("you have to choose positive numbers");
							err.show();
							return "-1";
						} catch (Exception e) {
							noProblem = false;
							err.setContentText("you didn't insert a number..");
							err.show();
							return "-1";
						}
					}
					Optional<String> resultTeam2 = dialog2.showAndWait();
					int num2 = 0;
					if (resultTeam2.isPresent()) {
						try {
							if (Integer.parseInt(resultTeam2.get()) < 0) {
								noProblem = false;
								throw new UserExceptions("you have to choose positive numbers");
							}
							num2 = Integer.parseInt(resultTeam2.get());
						} catch (UserExceptions ue) {
							err.setContentText("you have to choose positive numbers");
							err.show();
							return "-1";
						} catch (Exception e) {
							err.setContentText("you didn't insert a number..");
							err.show();
							return "-1";
						}
						if (num > num2) {
							theFirstParticipantWin++;
							theModel.addToWinnerList(startSoccerGame.getParticipants().get(0).getText());
							return startSoccerGame.getParticipants().get(0).getText();
						} else if (num < num2) {
							theSecondParticipantWin++;
							theModel.addToWinnerList(startSoccerGame.getParticipants().get(1).getText());
							return startSoccerGame.getParticipants().get(1).getText();
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
			} catch (UserExceptions ue) {
				err.setContentText("you have to choose positive numbers");
				err.show();
				return "-1";
			}
		}
		if (theFirstParticipantWin > theSecondParticipantWin) {
			theModel.addToWinnerList(startSoccerGame.getParticipants().get(0).getText());
			return startSoccerGame.getParticipants().get(0).getText();
		} else if (theFirstParticipantWin == theSecondParticipantWin) {
			noProblem = false;
			err.setContentText("One of the Team has to Win.");
			err.show();
			return "Draw";
		} else {
			theModel.addToWinnerList(startSoccerGame.getParticipants().get(1).getText());
			return startSoccerGame.getParticipants().get(1).getText();
		}
	}
}
