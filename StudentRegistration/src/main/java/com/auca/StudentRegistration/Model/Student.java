package com.auca.StudentRegistration.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Represents a student with a registration number, first name, date of birth,
 * and a list of registrations associated with the student.
 */
@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(name = "registration_number")
    @NotBlank(message = "Registration number cannot be blank")
    private String registrationNumber;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotNull(message = "Date of birth cannot be null")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "student")
    @JsonBackReference
    private List<StudentRegistration> registrations;

    public Student() {
    }

    public Student(String registrationNumber, String firstName, LocalDate dateOfBirth, List<StudentRegistration> registrations) {
        this.registrationNumber = registrationNumber;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.registrations = registrations;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<StudentRegistration> getRegistrations() {
        return registrations;
    }

    public void setRegistrations(List<StudentRegistration> registrations) {
        this.registrations = registrations;
    }

    // Optional: Implement equals and hashCode for object comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(registrationNumber, student.registrationNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber);
    }

    // Optional: Implement toString for human-readable representation

    @Override
    public String toString() {
        return "Student{" +
               "registrationNumber='" + registrationNumber + '\'' +
               ", firstName='" + firstName + '\'' +
               ", dateOfBirth=" + dateOfBirth +
               '}';
    }
}
