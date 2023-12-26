package com.auca.StudentRegistration.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Represents the definition of a course.
 * This class is used to model course definitions, including code, name, and description.
 */
@Entity
public class CourseDefinition {

    @Id
    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    public CourseDefinition() {
    }

    public CourseDefinition(String code, String name, String description) {
        this.code = code;
        this.name = name;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Optional: Implement equals and hashCode for object comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseDefinition that = (CourseDefinition) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    // Optional: Implement toString for human-readable representation

    @Override
    public String toString() {
        return "CourseDefinition{" +
               "code='" + code + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}
