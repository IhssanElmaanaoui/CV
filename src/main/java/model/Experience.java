package model;

import java.io.Serializable;

/**
 * Model bean for Professional Experience - Step 3.
 * One experience entry: job title, company, dates, description.
 */
public class Experience implements Serializable {

    private static final long serialVersionUID = 1L;

    /** "stage" = internship, "job" = professional experience */
    private String type;
    private String jobTitle;
    private String company;
    private String startDate;
    private String endDate;
    private String description;

    public Experience() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /** Display string for date range (ATS-friendly text). */
    public String getDateRange() {
        if (startDate == null && endDate == null) return "";
        if (startDate == null) return endDate == null ? "" : endDate.trim();
        if (endDate == null) return startDate.trim();
        return startDate.trim() + " – " + endDate.trim();
    }
}
