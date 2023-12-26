package com.auca.StudentRegistration.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * Represents a course offered by an academic unit in a specific semester.
 * This class is used to model courses and their relationships with other entities.
 */
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "academic_unit_code")
    @NotNull(message = "Department cannot be null")
    private AcademicUnit department;

    @OneToMany(mappedBy = "course")
    @JsonBackReference
    private List<StudentCourse> studentCourses;

    @ManyToOne
    @JoinColumn(name = "course_definition_id")
    @NotNull(message = "Course definition cannot be null")
    private CourseDefinition courseDefinition;

    @ManyToOne
    @JoinColumn(name = "semester_id")
    @NotNull(message = "Semester cannot be null")
    private Semester semester;

    // Getters and setters

    // Optional: Implement equals and hashCode for object comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return id == course.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Optional: Implement toString for human-readable representation

    @Override
    public String toString() {
        return "Course{" +
               "id=" + id +
               ", department=" + department +
               ", studentCourses=" + studentCourses +
               ", courseDefinition=" + courseDefinition +
               ", semester=" + semester +
               '}';
    }
}
