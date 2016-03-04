package app;

import org.apache.log4j.Logger;

import app.controllers.Controllable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

	private static final Logger _logger = Logger.getLogger(App.class);

	private Stage _primaryStage;

	private Router _router;

	/**
	 * Default constructor
	 */
	public App() {
		super();
		_logger.trace("App()");
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

	public void hook1() {
		_logger.trace("hook1()");
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		_logger.trace("start()");

		_primaryStage = primaryStage;
		_primaryStage.setTitle(Config.get().getConfig("name"));

		Pane rootLayout = _router.load("RootLayout", this);

//		FXMLLoader loader = new FXMLLoader();
//		loader.setLocation(this.getClass().getClassLoader().getResource("app/templates/RootLayout.fxml"));
//		BorderPane rootLayout = (BorderPane) loader.load();
//		Controllable controller = loader.getController();
//		controller.setApp(this);
//
		Scene scene = new Scene(rootLayout);
		_primaryStage.setScene(scene);
		_primaryStage.show();
		_primaryStage.setMinHeight(768);
		_primaryStage.setMinWidth(1024);
	}

}
