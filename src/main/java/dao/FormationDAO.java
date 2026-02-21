package dao;

import model.Formation;
import util.FileStorageUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * DAO for Education (Formation).
 * Persists a list of Formation entries per session (formation.properties + formation_N.properties for multiple).
 * Simple approach: one file with indexed keys (degree.0, institution.0, ...).
 */
public class FormationDAO {

    private static final String FILE_NAME = "formation.properties";

    public void save(List<Formation> list, String sessionId) throws IOException {
        if (list == null || sessionId == null) return;
        File file = FileStorageUtil.getSessionFile(sessionId, FILE_NAME);
        Properties props = new Properties();
        props.setProperty("size", String.valueOf(list.size()));
        for (int i = 0; i < list.size(); i++) {
            Formation f = list.get(i);
            String p = "item." + i + ".";
            setIfNotNull(props, p + "degree", f.getDegree());
            setIfNotNull(props, p + "institution", f.getInstitution());
            setIfNotNull(props, p + "startYear", f.getStartYear());
            setIfNotNull(props, p + "endYear", f.getEndYear());
        }
        try (OutputStream out = new FileOutputStream(file)) {
            props.store(out, "Formation - CV Generator");
        }
    }

    public List<Formation> load(String sessionId) throws IOException {
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
        List<Formation> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String p = "item." + i + ".";
            Formation f = new Formation();
            f.setDegree(props.getProperty(p + "degree"));
            f.setInstitution(props.getProperty(p + "institution"));
            f.setStartYear(props.getProperty(p + "startYear"));
            f.setEndYear(props.getProperty(p + "endYear"));
            list.add(f);
        }
        return list;
    }

    private static void setIfNotNull(Properties props, String key, String value) {
        if (value != null) props.setProperty(key, value);
    }
}
