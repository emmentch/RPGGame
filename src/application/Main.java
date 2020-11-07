package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {

			// first thing, init both players into EntityController
			UI.initLaunch();
			UI.console.setOnAction(e -> {
				String text = UI.console.getText();
				if (text != null && !text.isEmpty()) {
					TurnController.doTurn(text);
				}
				UI.console.setText(null);
			});

			Scene scene = new Scene(UI.root, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle(Constants.GAMETITLE);
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
