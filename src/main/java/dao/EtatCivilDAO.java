package dao;

import model.EtatCivil;
import util.FileStorageUtil;

import java.io.*;
import java.util.Properties;

/**
 * DAO for Personal Information (État Civil).
 * Persists and loads one EtatCivil per session using a properties file.
 */
public class EtatCivilDAO {

    private static final String FILE_NAME = "etatcivil.properties";

    /**
     * Saves the given EtatCivil for the session.
     */
    public void save(EtatCivil etatCivil, String sessionId) throws IOException {
        if (etatCivil == null || sessionId == null) return;
        File file = FileStorageUtil.getSessionFile(sessionId, FILE_NAME);
        Properties props = new Properties();
        setIfNotNull(props, "firstName", etatCivil.getFirstName());
        setIfNotNull(props, "lastName", etatCivil.getLastName());
        setIfNotNull(props, "email", etatCivil.getEmail());
        setIfNotNull(props, "phone", etatCivil.getPhone());
        setIfNotNull(props, "address", etatCivil.getAddress());
        setIfNotNull(props, "linkedIn", etatCivil.getLinkedIn());
        setIfNotNull(props, "professionalSummary", etatCivil.getProfessionalSummary());
        try (OutputStream out = new FileOutputStream(file)) {
            props.store(out, "EtatCivil - CV Generator");
        }
    }

    /**
     * Loads EtatCivil for the session, or null if none saved.
     */
    public EtatCivil load(String sessionId) throws IOException {
        File file = FileStorageUtil.getSessionFile(sessionId, FILE_NAME);
        if (!file.exists()) return null;
        Properties props = new Properties();
        try (InputStream in = new FileInputStream(file)) {
            props.load(in);
        }
        EtatCivil e = new EtatCivil();
        e.setFirstName(props.getProperty("firstName"));
        e.setLastName(props.getProperty("lastName"));
        e.setEmail(props.getProperty("email"));
        e.setPhone(props.getProperty("phone"));
        e.setAddress(props.getProperty("address"));
        e.setLinkedIn(props.getProperty("linkedIn"));
        e.setProfessionalSummary(props.getProperty("professionalSummary"));
        return e;
    }

    private static void setIfNotNull(Properties props, String key, String value) {
        if (value != null) props.setProperty(key, value);
    }
}
