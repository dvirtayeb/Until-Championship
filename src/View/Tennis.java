package View;

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

	private VBox vb;
	private Text nameParticipant1;
	private Text nameParticipant2;
	private Button btnDone;
	private BorderPane bp;
	private VBox vbTitle;
	private TextField firstRoundTeam1;
	private TextField secondRoundTeam1;
	private TextField thirdRoundTeam1;
	private TextField fourthRoundTeam1;
	private TextField fifthRoundTeam1;
	private TextField firstRoundTeam2;
	private TextField secondRoundTeam2;
	private TextField thirdRoundTeam2;
	private TextField fourthRoundTeam2;
	private TextField fifthRoundTeam2;
	private Alert err;

	public Tennis(Stage thirdStage, Model theModel, Championship champ) {
		err = new Alert(AlertType.ERROR, "", ButtonType.OK);
		Text tennisGame = new Text("Tennis Game");
		if (champ.getAllButton().get(0).isArmed()) {
			nameParticipant1 = new Text(theModel.getParticipantsList().get(0));
			nameParticipant2 = new Text(theModel.getParticipantsList().get(1));
		} else if (champ.getAllButton().get(1).isArmed()) {
			nameParticipant1 = new Text(theModel.getParticipantsList().get(2));
			nameParticipant2 = new Text(theModel.getParticipantsList().get(3));
		} else if (champ.getAllButton().get(2).isArmed()) {
			nameParticipant1 = new Text(theModel.getParticipantsList().get(4));
			nameParticipant2 = new Text(theModel.getParticipantsList().get(5));
		} else if (champ.getAllButton().get(3).isArmed()) {
			nameParticipant1 = new Text(theModel.getParticipantsList().get(6));
			nameParticipant2 = new Text(theModel.getParticipantsList().get(7));
		} else if (champ.getAllButton().get(4).isArmed()) {
			nameParticipant1 = new Text(theModel.getWinnerList().get(8));
			nameParticipant2 = new Text(theModel.getWinnerList().get(9));
		} else if (champ.getAllButton().get(5).isArmed()) {
			nameParticipant1 = new Text(theModel.getWinnerList().get(10));
			nameParticipant2 = new Text(theModel.getWinnerList().get(11));
		} else {
			nameParticipant1 = new Text(theModel.getWinnerList().get(12));
			nameParticipant2 = new Text(theModel.getWinnerList().get(13));
		}
		firstRoundTeam1 = new TextField();
		secondRoundTeam1 = new TextField();
		thirdRoundTeam1 = new TextField();
		fourthRoundTeam1 = new TextField();
		fifthRoundTeam1 = new TextField();
		firstRoundTeam2 = new TextField();
		secondRoundTeam2 = new TextField();
		thirdRoundTeam2 = new TextField();
		fourthRoundTeam2 = new TextField();
		fifthRoundTeam2 = new TextField();
		btnDone = new Button("Done");

		HBox participant1 = new HBox(nameParticipant1, firstRoundTeam1, secondRoundTeam1, thirdRoundTeam1,
				fourthRoundTeam1, fifthRoundTeam1);
		HBox participant2 = new HBox(nameParticipant2, firstRoundTeam2, secondRoundTeam2, thirdRoundTeam2,
				fourthRoundTeam2, fifthRoundTeam2);
		HBox hbDone = new HBox(btnDone);
		vb = new VBox();
		vb.getChildren().addAll(tennisGame, participant1, participant2, hbDone, btnDone);
		vb.setAlignment(Pos.CENTER_LEFT);

		vbTitle = new VBox();
		vbTitle.getChildren().add(tennisGame);

		bp = new BorderPane();
		bp.setTop(vbTitle);
		bp.setCenter(vb);

		// New Scene
		Scene scene = new Scene(bp, 400, 400);
		thirdStage.setTitle("Tennis Game");
		thirdStage.setScene(scene);
		thirdStage.show();
	}

	public int getFirstRoundTeam1() {
		try {
			if (!firstRoundTeam1.getText().equals(""))
				return Integer.parseInt(firstRoundTeam1.getText());

		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public int getSecondRoundTeam1() {
		try {
			if (!secondRoundTeam1.getText().equals(""))
				return Integer.parseInt(secondRoundTeam1.getText());
		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public int getThirdRoundTeam1() {
		try {
			if (!thirdRoundTeam1.getText().equals(""))
				return Integer.parseInt(thirdRoundTeam1.getText());
		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public int getFourthRoundTeam1() {
		try {
			if (!fourthRoundTeam1.getText().equals(""))
				return Integer.parseInt(fourthRoundTeam1.getText());
		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public int getFifthRoundTeam1() {
		try {
			if (!fifthRoundTeam1.getText().equals(""))
				return Integer.parseInt(fifthRoundTeam1.getText());
		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public int getFirstRoundTeam2() {
		try {
			if (!firstRoundTeam2.getText().equals(""))
				return Integer.parseInt(firstRoundTeam2.getText());
		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public int getSecondRoundTeam2() {
		try {
			if (!secondRoundTeam2.getText().equals(""))
				return Integer.parseInt(secondRoundTeam2.getText());
		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public int getThirdRoundTeam2() {
		try {
			if (!thirdRoundTeam2.getText().equals(""))
				return Integer.parseInt(thirdRoundTeam2.getText());
		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public int getFourthRoundTeam2() {
		try {
			if (!fourthRoundTeam2.getText().equals(""))
				return Integer.parseInt(fourthRoundTeam2.getText());
		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public int getFifthRoundTeam2() {
		try {
			if (!fifthRoundTeam2.getText().equals(""))
				return Integer.parseInt(fifthRoundTeam2.getText());
		} catch (Exception num) {
			err.setContentText("You didn't insert correctally number, insert only postive numbers");
			err.show();
			return -1;
		}
		return 0;
	}

	public String getNameParticipant1() {
		return nameParticipant1.getText();
	}

	public String getNameParticipant2() {
		return nameParticipant2.getText();
	}

	public Button getBtnDone() {
		return btnDone;
	}
}
