package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Simple file-based authentication.
 * Reads users from classpath users.properties (username=password).
 */
public final class AuthUtil {

    private static final String USERS_FILE = "users.properties";
    private static final String DEFAULT_USER = "admin";
    private static final String DEFAULT_PASS = "admin";

    private AuthUtil() {
    }

    /**
     * Validates username and password.
     * Tries users.properties from classpath; falls back to admin/admin if file missing.
     */
    public static boolean validate(String username, String password) {
        if (username == null || password == null || username.isBlank()) {
            return false;
        }
        Properties users = new Properties();
        try (InputStream in = AuthUtil.class.getClassLoader().getResourceAsStream(USERS_FILE)) {
            if (in != null) {
                users.load(in);
            }
        } catch (IOException e) {
            // fall through to default
        }
        String expected = users.getProperty(username.trim());
        if (expected != null) {
            return expected.equals(password);
        }
        // Demo default when no file or user not in file
        return DEFAULT_USER.equals(username.trim()) && DEFAULT_PASS.equals(password);
    }
}
