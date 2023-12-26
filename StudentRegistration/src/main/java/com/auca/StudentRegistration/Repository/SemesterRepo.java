package com.example.StudentManagementSystem.Repository;

import com.example.StudentManagementSystem.Model.AcademicTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomSemesterRepository extends JpaRepository<AcademicTerm, String> {
}
