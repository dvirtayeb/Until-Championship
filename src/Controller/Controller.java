package Controller;

import Model.Registry;
import Model.TennisModel;
import java.util.ArrayList;
import Model.BasketballModel;
import Model.SoccerModel;
import Model.UserExceptions;
import View.Basketball;
import View.Championship;
import View.GameViewUI;
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

	private Registry theReg;
	private TennisModel tennisModel;
	private BasketballModel basketBallModel;
	private SoccerModel soccerModel;

	public static Alert err;
	private static Alert begin;
	private static Alert change;
	private static Alert winner;

	private boolean finishQuarterGames = false;
	private boolean finishSemiFinalsGames = false;
	private boolean noProblem = true;

	private View theView;
	private Championship championship;

	private String theWinner;
	private String kindGame;
	private ArrayList<EventHandler<ActionEvent>> eventStartGame;

	GameViewUI game;

	public Controller(Registry theReg, View theView) throws UserExceptions {
		this.theReg = theReg;
		this.theView = theView;
		kindGame = theView.getKindRB(); // Default
		err = new Alert(AlertType.ERROR, "", ButtonType.OK);
		begin = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
		begin.setTitle("Instructions");
		begin.setHeaderText("Lets Start, Press double click on 'Sport - Games' for to start"
				+ "\n# Tip: You can decide who will competition in the next stage,\nby choosing which team will play first");
		change = new Alert(AlertType.INFORMATION, "The game changed", ButtonType.OK);
		winner = new Alert(AlertType.INFORMATION, "The winner is: ", ButtonType.OK);
		eventStartGame = new ArrayList<>();
		// Change Game : Tennis,Basketball,Soccer
		ChangeListener<Toggle> chl = new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				kindGame = theView.getKindRB();
				theReg.updateGame(kindGame);
				change.show();
			}

		};
		theView.addChangeListenerToToggleGroup(chl);

		// ADD Participant
		EventHandler<ActionEvent> eventAddParticipant = new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				try {
					if (theReg.getParticipantsList().size() < 8)
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
					if (theReg.getParticipantsList().size() == 8) {
						startChampionship();
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
		if (participant.equals("") || participant.charAt(0) == ' ') {
			err.setContentText("You didn't insert participant correctly. Insret again.");
			err.show();
			return false;
		}
		theReg.addParticipantToList(participant);
		int participantsCounter = theView.getListView().getItems().size();
		theView.addListView(theReg.getParticipantsList().get(participantsCounter));
		return true;
	}

	private boolean startChampionship() throws UserExceptions {
		theReg.clearWinnerList();
		theReg.clearGameList();
		tennisModel = new TennisModel("Sport - Games", "Tennis");
		basketBallModel = new BasketballModel("Sport - Games", "BasketBall");
		soccerModel = new SoccerModel("Sport - Games", "Soccer");
		kindGame = theView.getKindRB();
		theReg.updateGame(kindGame);
		for (int i = 0; i < 8; i++) {
			theReg.addToWinnerList(" ");
		}

		// Page Championship :
		championship = new Championship(new Stage(), theReg);
		championship.addChampionship();
		for (int i = 0; i < championship.getAllButton().size(); i++) {
			int counter = i;

			eventStartGame.add(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent arg0) {
					championship.getAllButton().get(theReg.getGamesList().get(counter).getGameNumber()).setId("gameOn");
					if (theReg.getGamesList().get(counter).getWinner().equals("")) {

						if (counter < 4)
							startGame(kindGame);
						else if (finishQuarterGames && counter < 6) {
							startGame(kindGame);
						} else if (finishSemiFinalsGames && counter == 6) {
							startGame(kindGame);
						} else if (!finishQuarterGames && 3 < counter && counter < 6) {
							err.setContentText("you have to finish the QuarterGames");
							err.show();
						} else if (!finishSemiFinalsGames && !finishQuarterGames && counter > 5
								|| !finishSemiFinalsGames && finishQuarterGames && counter > 5) {
							err.setContentText("you have to finish the Semi-Finals");
							err.show();
						}
					}

				}
			});
			championship.addEventToSubmit(eventStartGame.get(i), championship.getAllButton().get(i));
		}
		return true;
	}

	private boolean startGame(String kindGame) {
		// Page Tennis

		EventHandler<ActionEvent> doneGameEvent;

		switch (kindGame) {
		default:
		case "Tennis":
			game = new Tennis(new Stage(), theReg, championship);
			tennisModel.setParticipants(game.getParticipants().get(0).getText(),
					game.getParticipants().get(1).getText());
			doneGameEvent = new EndGameEventHandler<ActionEvent>(tennisModel, game, this, theReg);
			break;

		case "BasketBall":
			game = new Basketball(new Stage(), theReg, championship);
			basketBallModel.setParticipants(game.getParticipants().get(0).getText(),
					game.getParticipants().get(1).getText());
			doneGameEvent = new EndGameEventHandler<ActionEvent>(basketBallModel, game, this, theReg);
			break;
		case "Soccer":
			game = new Soccer(new Stage(), theReg, championship);
			soccerModel.setParticipants(game.getParticipants().get(0).getText(),
					game.getParticipants().get(1).getText());
			doneGameEvent = new EndGameEventHandler<ActionEvent>(soccerModel, game, this, theReg);
			break;
		}

		championship.addEventToSubmit(doneGameEvent, game.getBtnDone());

		return true;
	}

	// NOTE: Close Windows:
	void handleCloseButtonActionGame() {
		Stage stage = (Stage) game.getBtnDone().getScene().getWindow();
		stage.close();
	}

	// NOTE: Updates:
	boolean updateTheWinner(String name) {
		for (int i = 0; i < theReg.getGamesList().size(); i++) {
			if (championship.getAllButton().get(theReg.getGamesList().get(i).getGameNumber()).getId().equals("gameOn")
					&& noProblem) {
				theReg.getGamesList().get(i).setWinner(name);
				championship.getAllButton().get(theReg.getGamesList().get(i).getGameNumber()).setId("game" + i);

				return true;
			}
		}
		return false;
	}

	private boolean updateTheQuarterFinalsGames(String name) {
		theReg.getGamesList().get(4).setName(theReg.getWinnerList().get(8) + " VS " + theReg.getWinnerList().get(9));
		theReg.getGamesList().get(5).setName(theReg.getWinnerList().get(10) + " VS " + theReg.getWinnerList().get(11));
		return true;
	}

	private boolean updateTheFinalGames(String name) {
		theReg.getGamesList().get(6).setName(theReg.getWinnerList().get(12) + " VS " + theReg.getWinnerList().get(13));
		return true;
	}

	boolean updateGames() {
		int counter = 0;
		championship.update();
		for (int i = 0; i < theReg.getWinnerList().size(); i++) {
			if (theReg.getWinnerList().get(i) != " ")
				counter++;
			if (counter == 4 && noProblem) {
				finishQuarterGames = true;
				updateTheQuarterFinalsGames(theWinner);
				championship.update();
			} else if (counter == 6 && noProblem) {
				finishSemiFinalsGames = true;
				updateTheFinalGames(theWinner);
				championship.update();
			} else if (counter == 7 && noProblem) {
				winner.setContentText("The winner is: " + theReg.getGamesList().get(6).getWinner());
				winner.show();
			}
		}
		championship.update();
		return true;
	}

	public void setNoProblem(boolean noProblem) {
		this.noProblem = noProblem;
	}

	protected void setWinner(String name) {
		theWinner = name;
	}
}
