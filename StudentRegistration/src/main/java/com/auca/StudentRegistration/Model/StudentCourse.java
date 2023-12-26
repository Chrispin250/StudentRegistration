package com.auca.StudentRegistration.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import java.math.BigDecimal;

/**
 * Represents a record of a student's course, including credits, results,
 * associated student registration, and course.
 */
@Entity
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Min(value = 0, message = "Credits cannot be negative")
    private int credits;

    @DecimalMin(value = "0.0", inclusive = false, message = "Results cannot be negative")
    private BigDecimal results;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentRegistration studentRegistration;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    public StudentCourse() {
    }

    public StudentCourse(int id, int credits, BigDecimal results, StudentRegistration studentRegistration, Course course) {
        this.id = id;
        this.credits = credits;
        this.results = results;
        this.studentRegistration = studentRegistration;
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public BigDecimal getResults() {
        return results;
    }

    public void setResults(BigDecimal results) {
        this.results = results;
    }

    public StudentRegistration getStudentRegistration() {
        return studentRegistration;
    }

    public void setStudentRegistration(StudentRegistration studentRegistration) {
        this.studentRegistration = studentRegistration;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    // Optional: Implement toString for human-readable representation

    @Override
    public String toString() {
        return "StudentCourse{" +
               "id=" + id +
               ", credits=" + credits +
               ", results=" + results +
               ", studentRegistration=" + studentRegistration +
               ", course=" + course +
               '}';
    }
}
