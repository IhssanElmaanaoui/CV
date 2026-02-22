package dao;

import model.Experience;
import util.FileStorageUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * DAO for Professional Experience.
 * Persists a list of Experience entries per session.
 */
public class ExperienceDAO {

    private static final String FILE_NAME = "experience.properties";

    public void save(List<Experience> list, String sessionId) throws IOException {
        if (list == null || sessionId == null) return;
        File file = FileStorageUtil.getSessionFile(sessionId, FILE_NAME);
        Properties props = new Properties();
        props.setProperty("size", String.valueOf(list.size()));
        for (int i = 0; i < list.size(); i++) {
            Experience ex = list.get(i);
            String p = "item." + i + ".";
            setIfNotNull(props, p + "type", ex.getType());
            setIfNotNull(props, p + "jobTitle", ex.getJobTitle());
            setIfNotNull(props, p + "company", ex.getCompany());
            setIfNotNull(props, p + "startDate", ex.getStartDate());
            setIfNotNull(props, p + "endDate", ex.getEndDate());
            setIfNotNull(props, p + "description", ex.getDescription());
        }
        try (OutputStream out = new FileOutputStream(file)) {
            props.store(out, "Experience - CV Generator");
        }
    }

    public List<Experience> load(String sessionId) throws IOException {
        File file = FileStorageUtil.getSessionFile(sessionId, FILE_NAME);
        if (!file.exists()) return new ArrayList<>();
        Properties props = new Properties();
        try (InputStream in = new FileInputStream(file)) {
            props.load(in);
        }
        int size = 0;
        try {
            size = Integer.parseInt(props.getProperty("size", "0"));
        } catch (NumberFormatException e) {
            return new ArrayList<>();
        }
        List<Experience> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String p = "item." + i + ".";
            Experience ex = new Experience();
            ex.setType(props.getProperty(p + "type"));
            ex.setJobTitle(props.getProperty(p + "jobTitle"));
            ex.setCompany(props.getProperty(p + "company"));
            ex.setStartDate(props.getProperty(p + "startDate"));
            ex.setEndDate(props.getProperty(p + "endDate"));
            ex.setDescription(props.getProperty(p + "description"));
            list.add(ex);
        }
        return list;
    }

    private static void setIfNotNull(Properties props, String key, String value) {
        if (value != null) props.setProperty(key, value);
    }
}
