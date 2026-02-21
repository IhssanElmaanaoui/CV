package dao;

import model.SkillsHobbies;
import util.FileStorageUtil;

import java.io.*;
import java.util.Properties;

/**
 * DAO for Skills & Hobbies.
 * Persists one SkillsHobbies per session.
 */
public class SkillsDAO {

    private static final String FILE_NAME = "skills.properties";

    public void save(SkillsHobbies skills, String sessionId) throws IOException {
        if (skills == null || sessionId == null) return;
        File file = FileStorageUtil.getSessionFile(sessionId, FILE_NAME);
        Properties props = new Properties();
        setIfNotNull(props, "technicalSkills", skills.getTechnicalSkills());
        setIfNotNull(props, "softSkills", skills.getSoftSkills());
        setIfNotNull(props, "hobbies", skills.getHobbies());
        try (OutputStream out = new FileOutputStream(file)) {
            props.store(out, "Skills & Hobbies - CV Generator");
        }
    }

    public SkillsHobbies load(String sessionId) throws IOException {
        File file = FileStorageUtil.getSessionFile(sessionId, FILE_NAME);
        if (!file.exists()) return null;
        Properties props = new Properties();
        try (InputStream in = new FileInputStream(file)) {
            props.load(in);
        }
        SkillsHobbies s = new SkillsHobbies();
        s.setTechnicalSkills(props.getProperty("technicalSkills"));
        s.setSoftSkills(props.getProperty("softSkills"));
        s.setHobbies(props.getProperty("hobbies"));
        return s;
    }

    private static void setIfNotNull(Properties props, String key, String value) {
        if (value != null) props.setProperty(key, value);
    }
}
