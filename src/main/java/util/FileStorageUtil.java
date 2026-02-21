package util;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Utility for file-based persistence.
 * Uses a base directory (e.g. user.home/cvdata) and creates one subfolder per session.
 */
public final class FileStorageUtil {

    private static final String BASE_DIR_NAME = "cvdata";
    private static final String SESSION_PREFIX = "session_";

    private FileStorageUtil() {
    }

    /**
     * Returns the storage directory for the given session ID.
     * Creates the directory if it does not exist.
     *
     * @param sessionId HTTP session ID (or any unique string)
     * @return File pointing to the session's storage directory
     */
    public static File getSessionStorageDir(String sessionId) {
        if (sessionId == null || sessionId.trim().isEmpty()) {
            throw new IllegalArgumentException("sessionId must not be null or empty");
        }
        String safeId = sessionId.replaceAll("[^a-zA-Z0-9_-]", "_");
        Path base = Paths.get(System.getProperty("user.home"), BASE_DIR_NAME, SESSION_PREFIX + safeId);
        File dir = base.toFile();
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created && !dir.exists()) {
                throw new IllegalStateException("Could not create storage directory: " + dir.getAbsolutePath());
            }
        }
        return dir;
    }

    /**
     * Returns the path to a file inside the session storage directory.
     */
    public static File getSessionFile(String sessionId, String fileName) {
        File dir = getSessionStorageDir(sessionId);
        return new File(dir, fileName);
    }
}
