package Controller;

import Model.Model;
import Model.TennisModel;
import java.util.Optional;
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
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Toggle;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;

public class Controller {

	private Model theModel;
	private TennisModel tennisModel;
	private BasketballModel basketBallModel;
	private SoccerModel SoccerModel;

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
	
	GameViewUI game;

	public Controller(Model theModel, View theView) throws UserExceptions {
		this.theModel = theModel;
		this.theView = theView;
		kindGame = theView.getKindRB(); // Default
		err = new Alert(AlertType.ERROR, "", ButtonType.OK);
		begin = new Alert(AlertType.INFORMATION, "", ButtonType.OK);
		begin.setTitle("Instructions");
		begin.setHeaderText("Lets Start, Press double click on 'Sport - Games' for to start"
				+ "\n# Tip: You can decide who will competition in the next stage,\nby choosing which team will play first");
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

	private boolean addParticipant() throws UserExceptions {
		String participant = "" + theView.getTfParticipantName();
		if (participant.equals("") || participant.charAt(0) == ' ') {
			err.setContentText("You didn't insert participant correctly. Insret again.");
			err.show();
			return false;
		}
		theModel.addParticipantToList(participant);
		int participantsCounter = theView.getListView().getItems().size();
		theView.addListView(theModel.getParticipantsList().get(participantsCounter));
		return true;
	}

	private boolean startChampionshipFunction() throws UserExceptions {
		theModel.clearWinnerList();
		theModel.clearGameList();
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
				championship.update();
				if(theModel.getGamesList().get(0).getWinner().equals("")) {
					startGame(kindGame);
				}

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
				if(theModel.getGamesList().get(1).getWinner().equals("")) {
					startGame(kindGame);
				}

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
				if(theModel.getGamesList().get(2).getWinner().equals("")) {
					startGame(kindGame);
				}
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
				if(theModel.getGamesList().get(3).getWinner().equals("")) {
					startGame(kindGame);
				}
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
				if(theModel.getGamesList().get(4).getWinner().equals("")) {
					if (finishQuarterGames)
						startGame(kindGame);
					else {
						err.setContentText("you have to finish the QuarterGames");
						err.show();
					}
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
				if(theModel.getGamesList().get(5).getWinner().equals("")) {
					if (finishQuarterGames)
						startGame(kindGame);
					else {
						err.setContentText("you have to finish the QuarterGames");
						err.show();
					}
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
				if(theModel.getGamesList().get(6).getWinner().equals("")) {
					if (finishSemiFinalsGames)
						startGame(kindGame);
					else {
						err.setContentText("you have to finish the Semi-Finals");
						err.show();
					}
				}
			}

		};

		championship.addEventToSubmit(eventStartGame7, championship.getAllButton().get(6));
		return true;
	}
	
	public void setNoProblem(boolean noProblem) {
		this.noProblem = noProblem;
	}
	
	protected void setWinner(String name) {
		theWinner = name;
	}
	
	protected String getWinner() {
		return theWinner;
	}

	private boolean startGame(String kindGame) {
		// Page Tennis
		
		EventHandler<ActionEvent> doneGameEvent;
		
		switch(kindGame) {
		default:
		case "Tennis":
			game = new Tennis(new Stage(), theModel, championship);
			tennisModel.setParticipants(game.getParticipants().get(0).getText(),
					game.getParticipants().get(1).getText());
			doneGameEvent = new EndGameEventHandler<ActionEvent>(tennisModel, game, this, theModel);
			break;
			
		case "BasketBall":
			game = new Basketball(new Stage(), theModel, championship);
			basketBallModel.setParticipants(game.getParticipants().get(0).getText(),
					game.getParticipants().get(1).getText());
			doneGameEvent = new EndGameEventHandler<ActionEvent>(basketBallModel, game, this, theModel);
			break;
		case "Soccer":
			game = new Soccer(new Stage(), theModel, championship);
			SoccerModel.setParticipants(game.getParticipants().get(0).getText(),
					game.getParticipants().get(1).getText());
			doneGameEvent = new EndGameEventHandler<ActionEvent>(SoccerModel, game, this, theModel);
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
			for (int i = 0; i < theModel.getGamesList().size(); i++) {
				if (championship.getAllButton().get(theModel.getGamesList().get(i).getGameNumber()).getId()
						.equals("gameOn") && noProblem) {
					theModel.getGamesList().get(i).setWinner(name);
					return true;
				}
			}
		return false;
	}

	private boolean updateTheQuarterFinalsGames(String name) {
		theModel.getGamesList().get(4)
				.setName(theModel.getWinnerList().get(8) + " VS " + theModel.getWinnerList().get(9));
		theModel.getGamesList().get(5)
				.setName(theModel.getWinnerList().get(10) + " VS " + theModel.getWinnerList().get(11));
		return true;
	}

	private boolean updateTheFinalGames(String name) {
		theModel.getGamesList().get(6)
				.setName(theModel.getWinnerList().get(12) + " VS " + theModel.getWinnerList().get(13));
		return true;
	}

	boolean updateGames() {
		int counter = 0;
		championship.update();
		for (int i = 0; i < theModel.getWinnerList().size(); i++) {
			if (theModel.getWinnerList().get(i) != " ")
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
				winner.setContentText("The winner is: " + theModel.getGamesList().get(6).getWinner());
				winner.show();
			}
		}
		championship.update();
		return true;
	}
}

