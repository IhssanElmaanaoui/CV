package model;

import java.io.Serializable;

/**
 * Model bean for Education (Formation) - Step 2.
 * One education entry: degree, institution, start/end year.
 */
public class Formation implements Serializable {

    private static final long serialVersionUID = 1L;

    private String degree;
    private String institution;
    private String startYear;
    private String endYear;

    public Formation() {
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    /** Display string e.g. "2020 - 2024" */
    public String getYearRange() {
        if (startYear == null && endYear == null) return "";
        if (startYear == null) return endYear == null ? "" : endYear.trim();
        if (endYear == null) return startYear.trim();
        return startYear.trim() + " – " + endYear.trim();
    }
}
