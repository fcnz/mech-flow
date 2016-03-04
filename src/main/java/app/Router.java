package app;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import app.controllers.Controllable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Router {

	private static final Logger _logger = Logger.getLogger(Router.class);

	private Map<String, String> _routes;

	public static final String TEMPLATES = "app/templates/";

	public Router() {
		_routes = new HashMap<String, String>();

		// Register routes
		_routes.put("index", "RootLayout");
	}

	/**
	 *
	 * @param route
	 * @param app
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	public Pane load(String route, App app) throws Exception {
		_logger.trace("load()");
		try {
			Class<?> controllerClass = Class.forName("app.controllers." + route);
			Controllable controller = (Controllable) controllerClass.newInstance();
			URL templateURL = this.getClass().getClassLoader().getResource(TEMPLATES + route + ".fxml");
			FXMLLoader loader = new FXMLLoader(templateURL);
			loader.setController(controller);
			Pane pane = (Pane) loader.load();
			controller.setApp(app);
			return pane;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
			_logger.error(e.getMessage(), e);
			System.exit(1);
			throw e;
		}
	}

}
