package com.auca.StudentRegistration.Model;

import jakarta.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * Represents an academic unit.
 * This class is used to model academic units, such as departments or faculties.
 */
@Entity
public class AcademicUnit {

    @Id
    @NotBlank(message = "Code cannot be blank")
    private String code;

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Enumerated(EnumType.STRING)
    private EAcademicUnit unitType;

    @ManyToOne
    @JoinColumn(name = "parent_unit_code")
    private AcademicUnit parentUnit;

    public AcademicUnit() {
    }

    public AcademicUnit(String code, String name, EAcademicUnit unitType, AcademicUnit parentUnit) {
        this.code = code;
        this.name = name;
        this.unitType = unitType;
        this.parentUnit = parentUnit;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public EAcademicUnit getUnitType() {
        return unitType;
    }

    public AcademicUnit getParentUnit() {
        return parentUnit;
    }

    // Optional: Implement equals and hashCode for object comparison

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AcademicUnit that = (AcademicUnit) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    // Optional: Implement toString for human-readable representation

    @Override
    public String toString() {
        return "AcademicUnit{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", unitType=" + unitType +
                ", parentUnit=" + parentUnit +
                '}';
    }
}
