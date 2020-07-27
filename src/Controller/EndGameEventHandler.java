package Controller;

import Model.GameModelUI;
import Model.Registry;
import Model.UserExceptions;
import View.GameViewUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EndGameEventHandler<T> implements EventHandler<ActionEvent> {

	GameModelUI gameModelUI;
	GameViewUI gameViewUI;
	Controller controller;
	Registry theModel;

	public EndGameEventHandler(GameModelUI gameModelUI, GameViewUI gameViewUI, Controller controller,
			Registry theModel) {
		this.gameModelUI = gameModelUI;
		this.gameViewUI = gameViewUI;
		this.controller = controller;
		this.theModel = theModel;
	}

	@Override
	public void handle(ActionEvent event) {
		this.gameModelUI.clearParticipantPoints();
		this.controller.setNoProblem(true);
		gameModelUI.setParticipants(gameViewUI.getParticipants().get(0).getText(),
				gameViewUI.getParticipants().get(1).getText());
		int numLoop;
		switch (theModel.getKindGame()) {
		case "Soccer":
			numLoop = 2;
			break;
		case "BasketBall":
			numLoop = 4;
			break;
		default:
			numLoop = 5;
			break;
		}
		for (int i = 0; i < numLoop; i++) {
			gameModelUI.addPointsToFirstparticipantList(gameViewUI.getPointFromTeam1(i));
			gameModelUI.addPointsToSecondparticipantList(gameViewUI.getPointFromTeam2(i));
		}

		try {
			String theWinner = gameModelUI.checkResults(controller);
			if (theWinner.equals("-1") || theWinner.equals("Draw")) {
				this.controller.setNoProblem(false);
				controller.handleCloseButtonActionGame();
			} else {
				theModel.addToWinnerList(theWinner);
				controller.updateTheWinner(theWinner);
				controller.updateGames(theWinner);
				controller.handleCloseButtonActionGame();
			}
		} catch (UserExceptions ue) {
			this.controller.setNoProblem(false);

			Controller.err.setContentText("you have to choose positive numbers");
			Controller.err.show();
			return;
		}
	}

}
