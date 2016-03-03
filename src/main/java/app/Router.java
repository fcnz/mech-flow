package app;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import app.templates.Controllable;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class Router {

	private static final Logger logger = Logger.getLogger(Router.class);

	private Map<String, String> _routes;

	private FXMLLoader _loader = new FXMLLoader();

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
		logger.trace("load()");
		String fileName = _routes.get(route);
		URL templateURL = this.getClass().getClassLoader().getResource("app/templates/" + fileName + ".fxml");
		_loader.setLocation(templateURL);
		try {
			Class<?> controllerClass = Class.forName("app.controllers." + fileName);
			Constructor<?> constructor = controllerClass.getConstructor(Pane.class);
//			Controller controller = (Controller) controllerClass.newInstance();
			Controllable controller = (Controllable) constructor.newInstance();
			_loader.setController(controller);
			Pane pane = (Pane) _loader.load();
			controller.setApp(app);
			logger.debug("stop");
			return pane;
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IOException e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

}
