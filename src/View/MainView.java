package View;


import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainView {
	private RadioButton rbTenis, rbBasketBall, rbSoccer;
	private ToggleGroup tg;
	private BorderPane bp;
	private Text name;
	private TextField tfParticipantName;

	private Button btnAddParticipantName, btnStartChampionship, btnClearParticipants;

	private ListView<String> listViewParticipants;

	public MainView(Stage primaryStage) {
		String background = "-fx-background-color: #FFFFFF";
		// Location: Top
		Text championshipText = new Text("Championship ");
		championshipText.setFill(Color.ROYALBLUE);
		championshipText.setFont(new Font(34));
		// Location: LEFT
		listViewParticipants = new ListView<>();
		listViewParticipants.setMaxSize(150, 300);
		Text participants = new Text("PARTICIPANTS: ");
		participants.setFont(new Font(16));
		HBox hbListParticipants = new HBox(listViewParticipants);
		btnClearParticipants = new Button("Delete List");
		HBox hbClear = new HBox(btnClearParticipants);

		// Location: Center
		Text choose = new Text("Choose 8 participants for the competition");
		choose.setFont(new Font(16));
		Text info = new Text("(it's suppose to be a name of a team or a player,\n			Choose Wisely!)\n");
		info.setFont(new Font(13));
		name = new Text("Participant Name: ");
//		name.setFill(Color.RED);
		name.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
		//name.setFont(new Font(18));
		tfParticipantName = new TextField();
		btnAddParticipantName = new Button("Add Participant Name");
		btnStartChampionship = new Button("Start Championship");
		HBox hbInfo = new HBox();
		hbInfo.getChildren().addAll(info);
		hbInfo.setAlignment(Pos.CENTER);
		HBox hbChoose = new HBox();
		hbChoose.getChildren().add(choose);
		hbChoose.setAlignment(Pos.CENTER);
		HBox hbParticipant = new HBox();
		hbParticipant.getChildren().addAll(name, tfParticipantName);
		hbParticipant.setAlignment(Pos.CENTER);
		HBox.setMargin(name, new Insets(0, 0, 0, 10));
		HBox.setMargin(tfParticipantName, new Insets(0, 0, 0, 10));
		HBox hbSubmit = new HBox(btnAddParticipantName, btnStartChampionship);
		hbSubmit.setAlignment(Pos.CENTER);

		// Location: RIGHT
		Text changeGame = new Text("Choose the Game:   ");
		changeGame.setFont(new Font(14));
		changeGame.setFill(Color.RED);
		HBox hbChangeGame = new HBox();
		hbChangeGame.getChildren().addAll(changeGame);
		tg = new ToggleGroup();
		rbTenis = new RadioButton("Tennis");
		rbTenis.setSelected(true);
		rbTenis.setToggleGroup(tg);
		rbBasketBall = new RadioButton("BasketBall");
		rbBasketBall.setToggleGroup(tg);
		rbSoccer = new RadioButton("Soccer");
		rbSoccer.setToggleGroup(tg);
		HBox hb1 = new HBox(rbTenis);
		hb1.setAlignment(Pos.CENTER_LEFT);
		HBox hb2 = new HBox(rbBasketBall);
		hb2.setAlignment(Pos.CENTER_LEFT);
		HBox hb3 = new HBox(rbSoccer);
		hb3.setAlignment(Pos.CENTER_LEFT);

		// VBOX
		VBox vbTitle = new VBox();
		vbTitle.getChildren().addAll(championshipText);
		vbTitle.setAlignment(Pos.TOP_CENTER);

		VBox vbLeft = new VBox();
		vbLeft.getChildren().addAll(participants, hbListParticipants, hbClear);
		vbLeft.setAlignment(Pos.CENTER_LEFT);

		VBox vbCenter = new VBox();
		vbCenter.getChildren().addAll(hbChoose, hbInfo, hbParticipant, hbSubmit);
		vbCenter.setAlignment(Pos.CENTER);

		VBox vbRight = new VBox();
		vbRight.getChildren().addAll(hbChangeGame, hb1, hb2, hb3);
		vbRight.setAlignment(Pos.CENTER_RIGHT);

		// border pane
		bp = new BorderPane();
		bp.setStyle(background);
		bp.setTop(vbTitle);
		bp.setLeft(vbLeft);
		bp.setCenter(vbCenter);
		bp.setRight(vbRight);

		// New SCENE
		Scene scene = new Scene(bp, 750, 500);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void addChangeListenerToToggleGroup(ChangeListener<Toggle> chl) {
		tg.selectedToggleProperty().addListener(chl);
	}

	public void addListView(String value) {
		if (!value.equals(""))
			listViewParticipants.getItems().add(value);
	}

	public void addEventToSubmit(EventHandler<ActionEvent> event, Button bot) {
		bot.setOnAction(event);
	}

	public String getKindRB() {
		if (rbTenis.isSelected())
			return rbTenis.getText();
		else if (rbBasketBall.isSelected())
			return rbBasketBall.getText();
		else
			return rbSoccer.getText();
	}

	public void deleteParticipantList() {
		listViewParticipants.getItems().clear();
	}

	public ListView<String> getListView() {
		return listViewParticipants;
	}

	public Button getBtnClearParticipants() {
		return btnClearParticipants;
	}

	public Button getBtnAddParticipantName() {
		return btnAddParticipantName;
	}

	public Button getBtnStartChampionship() {
		return btnStartChampionship;
	}

	public String getTfParticipantName() {
		return tfParticipantName.getText();
	}
}
