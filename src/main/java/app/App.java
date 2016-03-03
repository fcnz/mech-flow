package app;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import application.controllers.RootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	private static final Logger logger = Logger.getLogger(App.class);

	private Stage _primaryStage;

	private Map<String, String> _config;

	private BorderPane _rootLayout;

	private RootController _rootController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		_primaryStage = primaryStage;
		_primaryStage.setTitle(_config.get("name"));

		initRootLayout();
	}

	/**
	 * Sets up root layout, menu bar, opening default view.
	 */
	private void initRootLayout() {
		try {

		// Load RootLayout
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(this.getClass().getClassLoader().getResource("nz/ac/auckland/view/RootLayout.fxml"));
		_rootLayout = (BorderPane) loader.load();

		// Show on stage
		Scene scene = new Scene(_rootLayout);
		_primaryStage.setScene(scene);
		_primaryStage.show();
		_primaryStage.setMinHeight(768);
		_primaryStage.setMinWidth(1024);

		// Give controller access to app
		_rootController = loader.getController();
		_rootController.setMainApp(this);

		} catch(IOException e) {
			logger.error(e.getMessage(), e);
		}
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

	/**
	 * Add to configs
	 *
	 * @param key
	 *            - Name of config
	 * @param value
	 *            - Config value
	 * @throws Exception
	 *             - If key already exists
	 */
	public void setConfig(String key, String value) throws Exception {
		if (!_config.containsKey(key)) {
			_config.put(key, value);
		} else {
			throw new Exception("Cannot overwrite configs");
		}
	}

	/**
	 * Retrieve config
	 *
	 * @param key
	 *            - Name of config
	 * @return Config value
	 */
	public String getConfig(String key) {
		return _config.get(key);
	}

}
