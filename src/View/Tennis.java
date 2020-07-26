package View;

import java.util.ArrayList;

import Model.Registry;
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
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Tennis implements GameViewUI {
	private ArrayList<Text> participants;
	private ArrayList<TextField> pointsListFirstParticipants;
	private ArrayList<TextField> pointsListSecondParticipants;
	private HBox participantName1;
	private HBox participantName2;

	private Button btnDone;
	private Alert err;

	private VBox vbTitle;
	private VBox vb;
	private BorderPane bp;

	public Tennis(Stage thirdStage, Registry theReg, Championship champ) {
		err = new Alert(AlertType.ERROR, "", ButtonType.OK);
		participants = new ArrayList<Text>();
		pointsListFirstParticipants = new ArrayList<TextField>();
		pointsListSecondParticipants = new ArrayList<TextField>();
		Text tennisGame = new Text("Tennis Game");
		tennisGame.setFont(new Font(22));
		showParticipants(champ,theReg);

		pointsListFirstParticipants.add(new TextField());
		pointsListFirstParticipants.add(new TextField());
		pointsListFirstParticipants.add(new TextField());
		pointsListFirstParticipants.add(new TextField());
		pointsListFirstParticipants.add(new TextField());
		HBox points1 = new HBox(pointsListFirstParticipants.get(0), pointsListFirstParticipants.get(1),
				pointsListFirstParticipants.get(2), pointsListFirstParticipants.get(3), pointsListFirstParticipants.get(4));
		points1.setAlignment(Pos.CENTER);
		pointsListSecondParticipants.add(new TextField());
		pointsListSecondParticipants.add(new TextField());
		pointsListSecondParticipants.add(new TextField());
		pointsListSecondParticipants.add(new TextField());
		pointsListSecondParticipants.add(new TextField());
		HBox points2= new HBox(pointsListSecondParticipants.get(0),pointsListSecondParticipants.get(1), pointsListSecondParticipants.get(2),
				pointsListSecondParticipants.get(3), pointsListSecondParticipants.get(4));
		points2.setAlignment(Pos.CENTER);
		for (int i = 0; i < participants.size() - 1; i++) {
			participantName1 = new HBox(participants.get(i), points1);
			participantName1.setAlignment(Pos.CENTER);
			participantName2 = new HBox(participants.get(i + 1), points2);
			participantName2.setAlignment(Pos.CENTER);
		}

		btnDone = new Button("End Game");
		HBox hbDone = new HBox(btnDone);
		hbDone.setAlignment(Pos.CENTER);
		vb = new VBox();
		vb.getChildren().addAll(participantName1, points1, participantName2, points2, hbDone);
		vb.setAlignment(Pos.CENTER);

		vbTitle = new VBox();
		vbTitle.getChildren().add(tennisGame);
		vbTitle.setAlignment(Pos.TOP_CENTER);

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
	
	private void showParticipants(Championship champ,Registry theReg) {
		if (champ.getAllButton().get(0).isArmed()) {
			participants.add(new Text(theReg.getParticipantsList().get(0)));
			participants.add(new Text(theReg.getParticipantsList().get(1)));
			participants.get(0).setFont(new Font(16));
			participants.get(1).setFont(new Font(16));
		} else if (champ.getAllButton().get(1).isArmed()) {
			participants.add(new Text(theReg.getParticipantsList().get(2)));
			participants.add(new Text(theReg.getParticipantsList().get(3)));
			participants.get(0).setFont(new Font(16));
			participants.get(1).setFont(new Font(16));
		} else if (champ.getAllButton().get(2).isArmed()) {
			participants.add(new Text(theReg.getParticipantsList().get(4)));
			participants.add(new Text(theReg.getParticipantsList().get(5)));
			participants.get(0).setFont(new Font(16));
			participants.get(1).setFont(new Font(16));
		} else if (champ.getAllButton().get(3).isArmed()) {
			participants.add(new Text(theReg.getParticipantsList().get(6)));
			participants.add(new Text(theReg.getParticipantsList().get(7)));
			participants.get(0).setFont(new Font(16));
			participants.get(1).setFont(new Font(16));
		} else if (champ.getAllButton().get(4).isArmed()) {
			participants.add(new Text(theReg.getWinnerList().get(8)));
			participants.add(new Text(theReg.getWinnerList().get(9)));
			participants.get(0).setFont(new Font(16));
			participants.get(1).setFont(new Font(16));
		} else if (champ.getAllButton().get(5).isArmed()) {
			participants.add(new Text(theReg.getWinnerList().get(10)));
			participants.add(new Text(theReg.getWinnerList().get(11)));
			participants.get(0).setFont(new Font(16));
			participants.get(1).setFont(new Font(16));
		} else {
			participants.add(new Text(theReg.getWinnerList().get(12)));
			participants.add(new Text(theReg.getWinnerList().get(13)));
			participants.get(0).setFont(new Font(16));
			participants.get(1).setFont(new Font(16));
		}
	}
}
