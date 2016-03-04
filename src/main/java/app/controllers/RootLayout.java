package app.controllers;

import org.apache.log4j.Logger;

import app.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class RootLayout implements Controllable {

	private static final Logger _logger = Logger.getLogger(RootLayout.class);

	@FXML
	private Label _appName;

	@FXML
	private Label _currnet;

	private App _app;

	public RootLayout() {
		super();
		_logger.trace("RootLayout()");
	}

	@Override
	public void setApp(App app) {
		_app = app;
		_appName.setText("Mech Flow v0.0.1");
		_app.hook1();
	}

}
