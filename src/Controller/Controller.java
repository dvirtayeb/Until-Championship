package Controller;

import Model.Model;
import Model.TennisModel;
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
	private static Alert change;
	private static Alert winner;

	private boolean finishGroupsGame = false;
	private boolean finishQuarterGames = false;
	private boolean noProblem = true;
	private boolean onlyOneGamePlayed = false;

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
		begin = new Alert(AlertType.INFORMATION, "Let's start", ButtonType.OK);
		change = new Alert(AlertType.INFORMATION, "The game changed", ButtonType.OK);
		winner = new Alert(AlertType.INFORMATION, "The winner is: ", ButtonType.OK);

		// Change Game : Tennis,Basketball,Soccer
		ChangeListener<Toggle> chl = new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				kindGame = theView.getKindRB();
				theModel.updateGame(kindGame);
				change.show();
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

	public boolean addParticipant() throws UserExceptions {
		String participant = "" + theView.getTfParticipantName();
		theModel.addParticipantToList(participant);
		int participantsCounter = theView.getListView().getItems().size();
		theView.addListView(theModel.getParticipantsList().get(participantsCounter));
		return true;
	}

	public boolean startChampionshipFunction() throws UserExceptions {
		tennisModel = new TennisModel("Tennis");
		basketBallModel = new BasketballModel("BasketBall");
		SoccerModel = new SoccerModel("Soccer");
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
				startGame(kindGame);

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
				startGame(kindGame);
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
//				if(!onlyOneGamePlayed)
				startGame(kindGame);
//				if(theModel.getWinnerList().get(8) !=" " || theModel.getWinnerList().get(8) !="Draw") {
//					onlyOneGamePlayed = true;
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
				if (finishGroupsGame)
					startGame(kindGame);
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
				if (finishGroupsGame)
					startGame(kindGame);
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
				if (finishQuarterGames)
					startGame(kindGame);
			}

		};

		championship.addEventToSubmit(eventStartGame7, championship.getAllButton().get(6));
		return true;
	}

	public boolean startGame(String kindGame) {
		// Page Tennis
		if (kindGame.equals("Tennis")) {
			startTennisGame = new Tennis(new Stage(), theModel, championship);
			EventHandler<ActionEvent> eventDoneTennisGame = new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					tennisModel.clearParticipantPoints();
					noProblem = true;
					tennisModel.addPointsToFirstparticipantList(startTennisGame.getFirstRoundTeam1());
					tennisModel.addPointsToFirstparticipantList(startTennisGame.getSecondRoundTeam1());
					tennisModel.addPointsToFirstparticipantList(startTennisGame.getThirdRoundTeam1());
					tennisModel.addPointsToFirstparticipantList(startTennisGame.getFourthRoundTeam1());
					tennisModel.addPointsToFirstparticipantList(startTennisGame.getFifthRoundTeam1());
					tennisModel.addPointsToSecondparticipantList(startTennisGame.getFirstRoundTeam2());
					tennisModel.addPointsToSecondparticipantList(startTennisGame.getSecondRoundTeam2());
					tennisModel.addPointsToSecondparticipantList(startTennisGame.getThirdRoundTeam2());
					tennisModel.addPointsToSecondparticipantList(startTennisGame.getFourthRoundTeam2());
					tennisModel.addPointsToSecondparticipantList(startTennisGame.getFifthRoundTeam2());
					theWinner = checkTennisResult();
					updateTheWinner(theModel, theWinner);
					int counter = 0;
					for (int i = 0; i < theModel.getWinnerList().size(); i++) {
						if (theModel.getWinnerList().get(i) != " ")
							counter++;
						if (counter == 4 && noProblem) {
							finishGroupsGame = true;
							updateTheQuarterFinalsGames(theModel, theWinner);
							championship.update();
						} else if (counter == 6 && noProblem) {
							finishQuarterGames = true;
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
					basketBallModel.clearParticipantsPoints();
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
							finishGroupsGame = true;
							updateTheQuarterFinalsGames(theModel, theWinner);
							championship.update();
						} else if (counter == 6 && noProblem) {
							finishQuarterGames = true;
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
		}
		return true;
	}

	public void handleCloseButtonActionTennis() { // close the points window (Tennis)
		Stage stage = (Stage) startTennisGame.getBtnDone().getScene().getWindow();
		stage.close();
	}

	public void handleCloseButtonActionBasketball() { // close the points window (Basketball)
		Stage stage = (Stage) startBasketBallGame.getBtnDone().getScene().getWindow();
		stage.close();
	}

	// Updates:
	public boolean updateTheWinner(Model theModel, String name) {
		for (int i = 0; i < theModel.getGamesList().size(); i++) {
			if (championship.getAllButton().get(theModel.getGamesList().get(i).getGameNumber()).getId().equals("gameOn")
					&& noProblem) {
				theModel.getGamesList().get(i).setWinner(name);
				return true;
			}
		}
		return false;
	}

	public boolean updateTheQuarterFinalsGames(Model theModel, String name) {
		theModel.getGamesList().get(4)
				.setName(theModel.getWinnerList().get(8) + " VS " + theModel.getWinnerList().get(9));
		theModel.getGamesList().get(5)
				.setName(theModel.getWinnerList().get(10) + " VS " + theModel.getWinnerList().get(11));
		return true;
	}

	public boolean updateTheFinalGames(Model theModel, String name) {
		theModel.getGamesList().get(6)
				.setName(theModel.getWinnerList().get(12) + " VS " + theModel.getWinnerList().get(13));
		return true;
	}

	// Check Winner functions (Tennis, Basketball, Soccer):
	public String checkTennisResult() {
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
				} else {
					noProblem = false;
					throw new UserExceptions("you have to insert points difference by 3");
				}

			} catch (UserExceptions ue) {
				if (tennisModel.getFirstParticipantsPoints().get(i) < 0) {
					err.setContentText("you have to choose positive numbers");
					err.show();
				} else {
					err.setContentText("you have to insert points difference by 3");
					err.show();
				}
				return "-1";
			}
		}

		if (theFirstParticipantWin > theSecondParticipantWin) {
			theModel.addToWinnerList(startTennisGame.getNameParticipant1());
			return startTennisGame.getNameParticipant1();
		} else {
			theModel.addToWinnerList(startTennisGame.getNameParticipant2());
			return startTennisGame.getNameParticipant2();
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

//	private String checkSoccerResualt() {

//	}

}
