package Controller;

import Model.GameModelUI;
import Model.Model;
import Model.UserExceptions;
import View.GameViewUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EndGameEventHandler<T> implements EventHandler<ActionEvent> {

	GameModelUI gameModelUI;
	GameViewUI gameViewUI;
	Controller controller;
	Model theModel;

	public EndGameEventHandler(GameModelUI gameModel, GameViewUI gameView, Controller controller, Model theModel) {
		this.gameModelUI = gameModel;
		this.gameViewUI = gameView;
		this.controller = controller;
		this.theModel = theModel;
	}

	@Override
	public void handle(ActionEvent event) {
		this.gameModelUI.clearParticipantPoints();
		this.controller.setNoProblem(true);
		int counter;
		switch (theModel.getKindGame()) {
		case "Soccer":
			counter = 2;
			break;
		case "BasketBall":
			counter = 4;

		default:
			counter = 5;
			break;
		}
		for (int i = 0; i < counter; i++) {
			gameModelUI.addPointsToFirstparticipantList(gameViewUI.getPointFromTeam1(i));
			gameModelUI.addPointsToSecondparticipantList(gameViewUI.getPointFromTeam2(i));
		}

		try {
			String temp = gameModelUI.checkResults(controller);
			if (!temp.equals("-1") || !temp.equals("Draw")) {
				theModel.addToWinnerList(temp);
				controller.updateTheWinner(temp);
				controller.updateGames();
			}
		} catch (UserExceptions ue) {
			this.controller.setNoProblem(false);
			Controller.err.setContentText("you have to choose positive numbers");
			Controller.err.show();
			return;
		}

		controller.handleCloseButtonActionGame();
	}

}
