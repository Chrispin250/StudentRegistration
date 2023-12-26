package com.auca.StudentRegistration.Model;

import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

/**
 * Represents a teacher with details such as teacher ID, code, full names, email, phone number, and qualification.
 */
@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int tr_id;

    private String tr_code;
    private String fullNames;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
    private String phoneNbr;

    @Enumerated(EnumType.STRING)
    private Qualification qualification;

    public Teacher() {
    }

    public Teacher(int tr_id, String tr_code, String fullNames, String email, String phoneNbr, Qualification qualification) {
        this.tr_id = tr_id;
        this.tr_code = tr_code;
        this.fullNames = fullNames;
        this.email = email;
        this.phoneNbr = phoneNbr;
        this.qualification = qualification;
    }

    public int getTr_id() {
        return tr_id;
    }

    public void setTr_id(int tr_id) {
        this.tr_id = tr_id;
    }

    public String getTr_code() {
        return tr_code;
    }

    public void setTr_code(String tr_code) {
        this.tr_code = tr_code;
    }

    public String getFullNames() {
        return fullNames;
    }

    public void setFullNames(String fullNames) {
        this.fullNames = fullNames;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
    }

    // Optional: Implement toString for human-readable representation

    @Override
    public String toString() {
        return "Teacher{" +
               "tr_id=" + tr_id +
               ", tr_code='" + tr_code + '\'' +
               ", fullNames='" + fullNames + '\'' +
               ", email='" + email + '\'' +
               ", phoneNbr='" + phoneNbr + '\'' +
               ", qualification=" + qualification +
               '}';
    }
}
