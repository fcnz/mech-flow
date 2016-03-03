package app;

import java.util.HashMap;
import java.util.Map;

public class Config {

	private static Config _instance;
	private Map<String, String> _configs;

	public static Config get() {
		if (_instance == null) {
			_instance = new Config();
		}

		return _instance;

	}

	private Config() {
		_configs = new HashMap<String, String>();
		try {
			setConfig("name", "Mech Flow");
		} catch (Exception e) {
		}
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
		if (!_configs.containsKey(key)) {
			_configs.put(key, value);
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
		return _configs.get(key);
	}


}
