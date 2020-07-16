package View;

import java.util.ArrayList;

import Model.Model;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tennis {
	private ArrayList<Text> participants;
	private ArrayList<TextField> pointsListFirstParticipants;
	private ArrayList<TextField> pointsListSecondParticipants;
	private HBox participant1;
	private HBox participant2;

	private Button btnDone;
	private Alert err;

	private VBox vbTitle;
	private VBox vb;
	private BorderPane bp;

	public Tennis(Stage thirdStage, Model theModel, Championship champ) {
		err = new Alert(AlertType.ERROR, "", ButtonType.OK);
		participants = new ArrayList<Text>();
		pointsListFirstParticipants = new ArrayList<TextField>();
		pointsListSecondParticipants = new ArrayList<TextField>();
		Text tennisGame = new Text("Tennis Game");
		if (champ.getAllButton().get(0).isArmed()) {
			participants.add(new Text(theModel.getParticipantsList().get(0)));
			participants.add(new Text(theModel.getParticipantsList().get(1)));
		} else if (champ.getAllButton().get(1).isArmed()) {
			participants.add(new Text(theModel.getParticipantsList().get(2)));
			participants.add(new Text(theModel.getParticipantsList().get(3)));
		} else if (champ.getAllButton().get(2).isArmed()) {
			participants.add(new Text(theModel.getParticipantsList().get(4)));
			participants.add(new Text(theModel.getParticipantsList().get(5)));
		} else if (champ.getAllButton().get(3).isArmed()) {
			participants.add(new Text(theModel.getParticipantsList().get(6)));
			participants.add(new Text(theModel.getParticipantsList().get(7)));
		} else if (champ.getAllButton().get(4).isArmed()) {
			participants.add(new Text(theModel.getWinnerList().get(8)));
			participants.add(new Text(theModel.getWinnerList().get(9)));
		} else if (champ.getAllButton().get(5).isArmed()) {
			participants.add(new Text(theModel.getWinnerList().get(10)));
			participants.add(new Text(theModel.getWinnerList().get(11)));
		} else {
			participants.add(new Text(theModel.getWinnerList().get(12)));
			participants.add(new Text(theModel.getWinnerList().get(13)));
		}

		pointsListFirstParticipants.add(new TextField());
		pointsListFirstParticipants.add(new TextField());
		pointsListFirstParticipants.add(new TextField());
		pointsListFirstParticipants.add(new TextField());
		pointsListFirstParticipants.add(new TextField());

		pointsListSecondParticipants.add(new TextField());
		pointsListSecondParticipants.add(new TextField());
		pointsListSecondParticipants.add(new TextField());
		pointsListSecondParticipants.add(new TextField());
		pointsListSecondParticipants.add(new TextField());

		for (int i = 0; i < participants.size() - 1; i++) {
			participant1 = new HBox(participants.get(i), pointsListFirstParticipants.get(0),
					pointsListFirstParticipants.get(1), pointsListFirstParticipants.get(2),
					pointsListFirstParticipants.get(3), pointsListFirstParticipants.get(4));
			participant2 = new HBox(participants.get(i + 1), pointsListSecondParticipants.get(0),
					pointsListSecondParticipants.get(1), pointsListSecondParticipants.get(2),
					pointsListSecondParticipants.get(3), pointsListSecondParticipants.get(4));
		}

		btnDone = new Button("Done");
		HBox hbDone = new HBox(btnDone);
		vb = new VBox();
		vb.getChildren().addAll(tennisGame, participant1, participant2, hbDone, btnDone);
		vb.setAlignment(Pos.CENTER);

		vbTitle = new VBox();
		vbTitle.getChildren().add(tennisGame);

		bp = new BorderPane();
		bp.setTop(vbTitle);
		bp.setCenter(vb);

		// New Scene
		Scene scene = new Scene(bp, 400, 300);
		thirdStage.setTitle("Tennis Game");
		thirdStage.setScene(scene);
		thirdStage.show();
	}

	public ArrayList<Text> getParticipants() {
		return participants;
	}

	public ArrayList<TextField> getPointsListFirstParticipants() {
		return pointsListFirstParticipants;
	}

	public ArrayList<TextField> getPointsListSecondParticipants() {
		return pointsListSecondParticipants;
	}

	public Button getBtnDone() {
		return btnDone;
	}

	public int getPointFromTeam1(int i) {
		try {
			if (!pointsListFirstParticipants.get(i).getText().equals("")) {
				return Integer.parseInt(pointsListFirstParticipants.get(i).getText());
			}

		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public int getPointFromTeam2(int i) {
		try {
			if (!pointsListSecondParticipants.get(i).getText().equals(""))
				return Integer.parseInt(pointsListSecondParticipants.get(i).getText());

		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}
}
