package View;

import java.util.ArrayList;

import Model.Game;
import Model.Model;
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
	TreeTableView<Game> treeTableView;
	private ArrayList<Button> allButton;
	private TreeItem<Game> games;
	private TreeItem<Game> groupGames;
	private TreeItem<Game> quarterFinalsGames;
	private TreeItem<Game> finalGames;
	private Game game;
	TreeItem<Game> gameItem;
	private TreeItem<Game> gameItem2;
	private ArrayList<TreeItem> treeItemList;
	private TreeItem<Game> gameItem3;

	public Championship(Stage seconderyStage, Model theModel) {
		allButton = new ArrayList<Button>();
		treeTableView = new TreeTableView<Game>();
		treeItemList = new ArrayList<TreeItem>();
		TreeTableColumn<Game, String> treeTableColumn1 = new TreeTableColumn<>("    Games						");
		TreeTableColumn<Game, String> treeTableColumn2 = new TreeTableColumn<>("    Winner		");
		TreeTableColumn<Game, String> treeTableColumn3 = new TreeTableColumn<>("	Start Game		");
		treeTableColumn1.setCellValueFactory(new TreeItemPropertyValueFactory<>("name"));
		treeTableColumn2.setCellValueFactory(new TreeItemPropertyValueFactory<>("winner"));
		treeTableColumn3.setCellValueFactory(new TreeItemPropertyValueFactory<>("startGame"));
		treeTableView.getColumns().add(treeTableColumn1);
		treeTableView.getColumns().add(treeTableColumn2);
		treeTableView.getColumns().add(treeTableColumn3);
		games = new TreeItem<Game>(new Game("Games"));

		groupGames = new TreeItem<Game>(new Game("Groups Games"));
		games.getChildren().add(groupGames);
		for (int i = 0; i < theModel.getParticipantsList().size() - 1; i++) {
			allButton.add(new Button("Start-Game"));
			allButton.get(i).setId("game" + (i + 1));
		}
		int counter = 0;
		for (int i = 0; i < theModel.getParticipantsList().size() - 1; i = i + 2) {
			game = new Game(theModel.getParticipantsList().get(i) + " VS " + theModel.getParticipantsList().get(i + 1),
					"", allButton.get(i / 2), counter);
			gameItem = new TreeItem<Game>(game);
			groupGames.getChildren().add(gameItem);
			theModel.addGameToList(game); // or make array list of games and from the controller move to the model?
			counter++;
		}
		quarterFinalsGames = new TreeItem<Game>(new Game("Quarter-Final-Games"));
		games.getChildren().add(quarterFinalsGames);
		for (int i = 0; i < theModel.getWinnerList().size() - 6; i++) {
			game = new Game(theModel.getWinnerList().get(i) + " VS " + theModel.getWinnerList().get(i + 1), "",
					allButton.get((i + 4)), counter);
			gameItem2 = new TreeItem<Game>(game);
			quarterFinalsGames.getChildren().add(gameItem2);
			theModel.addGameToList(game);// or make array list of games and from the controller move to the model?
			counter++;
		}
		finalGames = new TreeItem<Game>(new Game("Final-Game"));
		games.getChildren().add(finalGames);
		for (int i = 0; i < 1; i++) {
			game = new Game(theModel.getWinnerList().get(i) + " VS " + theModel.getWinnerList().get(i + 1), "",
					allButton.get((6)), counter);
			gameItem3 = new TreeItem<Game>(game);
			finalGames.getChildren().add(gameItem3);
			theModel.addGameToList(game);// or make array list of games and from the controller move to the model?
			counter++;
		}

		treeTableView.setRoot(games);
		vb = new VBox();
		vb.getChildren().addAll(treeTableView);

		// New Scene
		Scene scene = new Scene(vb, 800, 400);
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

	public ArrayList<TreeItem> getTreeItem() {
		return treeItemList;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}
