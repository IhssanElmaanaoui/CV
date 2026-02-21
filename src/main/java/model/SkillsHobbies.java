package model;

import java.io.Serializable;

/**
 * Model bean for Skills & Hobbies - Step 4.
 * Technical skills, soft skills, and hobbies (stored as text; can be comma-separated).
 */
public class SkillsHobbies implements Serializable {

    private static final long serialVersionUID = 1L;

    private String technicalSkills;
    private String softSkills;
    private String hobbies;

    public SkillsHobbies() {
    }

    public String getTechnicalSkills() {
        return technicalSkills;
    }

    public void setTechnicalSkills(String technicalSkills) {
        this.technicalSkills = technicalSkills;
    }

    public String getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(String softSkills) {
        this.softSkills = softSkills;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }
}
