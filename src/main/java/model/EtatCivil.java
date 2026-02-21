package model;

import java.io.Serializable;

/**
 * Model bean for Personal Information (État Civil) - Step 1.
 * Holds first name, last name, contact details and LinkedIn.
 */
public class EtatCivil implements Serializable {

    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String linkedIn;
    /** Optional professional summary for CV. */
    private String professionalSummary;

    public EtatCivil() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkedIn() {
        return linkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        this.linkedIn = linkedIn;
    }

    public String getProfessionalSummary() {
        return professionalSummary;
    }

    public void setProfessionalSummary(String professionalSummary) {
        this.professionalSummary = professionalSummary;
    }

    /** Returns full name for CV heading. */
    public String getFullName() {
        if (firstName == null && lastName == null) return "";
        if (firstName == null) return lastName == null ? "" : lastName.trim();
        if (lastName == null) return firstName.trim();
        return (firstName.trim() + " " + lastName.trim()).trim();
    }
}
