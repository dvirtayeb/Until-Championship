package View;

import java.util.ArrayList;

import Model.Registry;
import Model.SportGames;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Championship {
	private VBox vb;
	private TreeTableView<SportGames> treeTableView;
	private ArrayList<Button> allButton;
	private TreeItem<SportGames> games;
	private TreeItem<SportGames> groupGames;
	private TreeItem<SportGames> quarterFinalsGames;
	private TreeItem<SportGames> finalGames;
	private ArrayList<SportGames> gamesList;
	private TreeItem<SportGames> gameItem;
	private TreeItem<SportGames> gameItem2;
	private TreeItem<SportGames> gameItem3;

	public Championship(Stage seconderyStage, Registry theModel) {
		allButton = new ArrayList<Button>();
		gamesList = new ArrayList<SportGames>();

		treeTableView = new TreeTableView<SportGames>();
		TreeTableColumn<SportGames, String> treeTableColumn1 = new TreeTableColumn<>("    Games						");
		TreeTableColumn<SportGames, String> treeTableColumn2 = new TreeTableColumn<>(
				"    Winner		" + "\n(continue to next stage)");
		TreeTableColumn<SportGames, String> treeTableColumn3 = new TreeTableColumn<>("	Start Game		");
		treeTableColumn1.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
		treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("winner"));
		treeTableColumn3.setCellValueFactory(new TreeItemPropertyValueFactory<>("startGame"));
		treeTableView.getColumns().add(treeTableColumn1);
		treeTableView.getColumns().add(treeTableColumn2);
		treeTableView.getColumns().add(treeTableColumn3);

		games = new TreeItem<SportGames>(new SportGames("Sport Games", "Sport - Games"));
		groupGames = new TreeItem<SportGames>(new SportGames("Sport Games", "Quarter-finals-Games"));
		games.getChildren().add(groupGames);
		for (int i = 0; i < theModel.getParticipantsList().size() - 1; i++) {
			allButton.add(new Button("Start-Game"));
			allButton.get(i).setId("game" + (i + 1));
		}
		int counter = 0;
		// Games: 1-4
		for (int i = 0; i < theModel.getParticipantsList().size() - 1; i = i + 2) {
			gamesList.add(new SportGames("Sport Games",
					theModel.getParticipantsList().get(i) + " VS " + theModel.getParticipantsList().get(i + 1), "",
					allButton.get(i / 2), counter));
			gameItem = new TreeItem<SportGames>(gamesList.get(i / 2));
			groupGames.getChildren().add(gameItem);
			counter++;
		}
		// Games: 5-6
		quarterFinalsGames = new TreeItem<SportGames>(new SportGames("Sport Games", "Semi-finals- Games"));
		games.getChildren().add(quarterFinalsGames);
		for (int i = 0; i < theModel.getWinnerList().size() - 6; i++) {
			gamesList.add(new SportGames("Sport Games",
					theModel.getWinnerList().get(i) + " VS " + theModel.getWinnerList().get(i + 1), "",
					allButton.get((i + 4)), counter));
			gameItem2 = new TreeItem<SportGames>(gamesList.get(i + 4));
			quarterFinalsGames.getChildren().add(gameItem2);
			counter++;
		}
		// Games: 7
		finalGames = new TreeItem<SportGames>(new SportGames("Sport Games", "Final-Stage-Game"));
		games.getChildren().add(finalGames);
		gamesList.add(new SportGames("Sport Games",
				theModel.getWinnerList().get(0) + " VS " + theModel.getWinnerList().get(1), "",
				allButton.get((6)), counter));
		gameItem3 = new TreeItem<SportGames>(gamesList.get(6));
		finalGames.getChildren().add(gameItem3);
		
		treeTableView.setRoot(games);
		vb = new VBox();
		vb.getChildren().addAll(treeTableView);

		// New Scene
		Scene scene = new Scene(vb, 505, 400);
		seconderyStage.setTitle("Championship");
		seconderyStage.setScene(scene);
		seconderyStage.show();
	}

	public void update() {
		treeTableView.refresh();
	}

	public void addEventToSubmit(EventHandler<ActionEvent> event, Button bot) {
		bot.setOnAction(event);
	}

	public void addChampionship() {
		vb.getChildren().clear();
		vb.getChildren().addAll(treeTableView);
	}

	public ArrayList<Button> getAllButton() {
		return allButton;
	}

	public ArrayList<SportGames> getGamesList() {
		return gamesList;
	}
}