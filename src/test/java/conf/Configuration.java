package conf;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuration {
    private static final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    public static final String HOST = getString("APP_HOST");
    public static final String BASE_PATH = getString("APP_BASE_PATH");
    public static final int TESTING_THREADS = getInt("TESTING_THREADS");
    public static final boolean ENABLE_LOGGING = getBoolean("ENABLE_LOGGING");
    public static final int CONNECTION_TIMEOUT_MILLIS = getInt("CONNECTION_TIMEOUT_MILLIS");
    public static final long MAX_RESPONSE_TIME_SECONDS = getLong("MAX_RESPONSE_TIME_SECONDS");
    public static final String[] EXCLUDED_GROUPS = getString("EXCLUDED_GROUPS").split(",");
    public static final String[] INCLUDED_GROUPS = getString("INCLUDED_GROUPS").split(",");
    public static final String UNIQUE_FRAMEWORK_TEST_DATA_ID = getString("UNIQUE_FRAMEWORK_TEST_DATA_ID");

    private static String getString(String key) {
        String property = System.getProperty(key);
        if (property == null) {
            property = dotenv.get(key);
        }
        return property;
    }

    private static int getInt(String key) {
        return Integer.parseInt(getString(key));
    }

    private static long getLong(String key) {
        return Long.parseLong(getString(key));
    }

    private static boolean getBoolean(String key) {
        return Boolean.parseBoolean(getString(key));
    }
}