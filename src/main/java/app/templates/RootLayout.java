package app.templates;

import org.apache.log4j.Logger;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RootLayout implements Controllable {

	private static final Logger logger = Logger.getLogger(RootLayout.class);

	@FXML
	private Label _appName;

	@FXML
	private Label _currnet;

	private App _app;

	public RootLayout() {
		super();
		logger.trace("RootLayout()");
	}

	@Override
	public void setApp(App app) {
		_app = app;
		_appName.setText("Mech Flow v0.0.1");
	}

}
