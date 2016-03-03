package app;

import org.apache.log4j.Logger;

import app.templates.Controllable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	private static final Logger logger = Logger.getLogger(App.class);

	private Stage _primaryStage;

	private Router _router;

	/**
	 * Default constructor
	 */
	public App() {
		super();
		logger.trace("App()");
		_router = new Router();
	}

	/**
	 * Entry point
	 *
	 * @param args
	 *            - String[] options
	 */
	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		logger.trace("start()");

		_primaryStage = primaryStage;
		_primaryStage.setTitle(Config.get().getConfig("name"));

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getClassLoader().getResource("app/templates/RootLayout.fxml"));
		BorderPane rootLayout = (BorderPane) loader.load();
		Controllable controller = loader.getController();
		controller.setApp(this);

		Scene scene = new Scene(rootLayout);
		_primaryStage.setScene(scene);
		_primaryStage.show();
		_primaryStage.setMinHeight(768);
		_primaryStage.setMinWidth(1024);
	}

}
