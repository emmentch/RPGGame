package application;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;

public class UI {
	static TextField console;
	static Label playerSide;
	static Label enemySide;
	static Label infoSide;
	static GridPane root;
	static TextInputDialog nameInput;

	static void initConsoleAndLabels() {
		root = new GridPane();
		root.getStyleClass().add("gridpane");
		root.setPadding(new Insets(10, 10, 10, 10));
		root.setMinSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		root.setVgap(2);
		root.setHgap(3);

		console = new TextField(null);
		console.setPrefWidth(Constants.WINDOW_WIDTH);

		playerSide = new Label(EntityController.player.getStats());
		playerSide.setPrefWidth(Constants.WINDOW_WIDTH / 2);
		playerSide.setPrefHeight(Constants.WINDOW_HEIGHT / 2);

		enemySide = new Label(EntityController.enemy.getStats());
		enemySide.setPrefWidth(Constants.WINDOW_WIDTH / 2);
		enemySide.setPrefHeight(Constants.WINDOW_HEIGHT / 2);

		infoSide = new Label("Type:" + "\n1) [j]ab (base damage, .9 acc)" + "\n2) [k]ick (1.2 times damage, .75 acc)"
				+ "\n3) [p]oison dart (poisons enemy, .5 times damage, .8 acc)"
				+ "\n4) [c]hop (paralyzes enemy, .8 times damage, .5 acc)" + "\n5) [h]eal (heals 0 to 1/4 max health)"
				+ "\n6) [b]lock (DOES NOT WORK)");
		infoSide.setPrefWidth(Constants.WINDOW_WIDTH);
		infoSide.setPrefHeight(Constants.WINDOW_HEIGHT / 2);

		root.add(playerSide, 0, 0);
		root.add(enemySide, 1, 0);
		root.add(infoSide, 0, 1, 2, 1);
		root.add(console, 0, 2, 2, 1);

	}

	static void initPlayerName() {
		nameInput = new TextInputDialog();
		nameInput.setHeaderText(null);
		nameInput.setGraphic(null);
		nameInput.setTitle(Constants.GAMETITLE);
		nameInput.setContentText("Name:");
		nameInput.initModality(Modality.APPLICATION_MODAL);
//		nameInput.setOnCloseRequest(event -> { TODO fix functionality
//			System.exit(0);
//		});

		DialogPane pane = nameInput.getDialogPane();
		pane.getStylesheets().add(Main.class.getResource("dialog.css").toExternalForm());

		Optional<String> result = nameInput.showAndWait();
		result.ifPresent(name -> {
			if (name != null && !name.trim().isEmpty())
				EntityController.defaultPlayerName = name;
		});
	}

	static void initLaunch() {
		initPlayerName();
		EntityController.setEntities(new Player(EntityController.defaultPlayerName));
		UI.initConsoleAndLabels();
	}

	static void updateLabels() {
		playerSide.setText(EntityController.player.getStats());
		enemySide.setText(EntityController.enemy.getStats());
	}
}
