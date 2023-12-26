package com.auca.StudentRegistration.Service;

import com.auca.StudentRegistration.Model.*;
import com.auca.StudentRegistration.Repository.StudentCourseRepo;
import com.auca.StudentRegistration.Repository.StudentRegistrationRepo;
import com.auca.StudentRegistration.Repository.StudentRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudCourseService {
    private static final Logger logger = LoggerFactory.getLogger(StudCourseService.class);

    @Autowired
    private StudentCourseRepo studCrsRepo;

    @Autowired
    private StudentRegistrationRepo regRepo;

    @Transactional
    public String createStudCourse(StudentCourse studentCourse) {
        if (studentCourse != null) {
            if (isCourseRegistered(studentCourse.getCourse())) {
                return "Course is already registered by a student.";
            } else {
                studCrsRepo.save(studentCourse);
                return "Course registered successfully.";
            }
        } else {
            return "Invalid input: StudentCourse is null";
        }
    }

    public boolean isCourseRegistered(Course course) {
        return studCrsRepo.existsByCourse(course);
    }

    public boolean isStudentExists(String studentId) {
        return regRepo.existsByStudentId(studentId);
    }

    public boolean isStudentCrsExists(Integer id) {
        return studCrsRepo.existsById(id);
    }

    public List<StudentCourse> listStudentsCourse() {
        return studCrsRepo.findAll();
    }

    @Transactional
    public String updateStudCourse(Integer id, StudentCourse studentCourse) {
        logger.info("Updating student with student ID: {}", id);
        try {
            if (studentCourse != null) {
                if (isStudentCrsExists(id)) {
                    studCrsRepo.save(studentCourse);
                    logger.info("Student Course updated successfully");
                    return "Student Course updated successfully";
                } else {
                    return "Student Course not found";
                }
            } else {
                return "Invalid input: Updated studentCourse is null";
            }
        } catch (Exception ex) {
            logger.error("Failed to update student Course. Exception: {}", ex.getMessage());
            return "Student Course not updated";
        }
    }

    @Transactional
    public String deleteStudCourse(Integer id) {
        logger.info("Deleting student course with id: {}", id);
        try {
            if (id != null) {
                if (isStudentCrsExists(id)) {
                    studCrsRepo.deleteById(id);
                    logger.info("Student Course deleted successfully");
                    return "Student Course deleted successfully";
                } else {
                    return "Student Course not found";
                }
            } else {
                return "Invalid input: Id is null";
            }
        } catch (Exception e) {
            logger.error("Failed to delete student Course. Exception: {}", e.getMessage());
            return "Student Course not deleted";
        }
    }

    public List<StudentCourse> getCoursesByStudentId(String studentId) {
        StudentRegistration student = regRepo.findByStudentId(studentId);
        return studCrsRepo.findByStudentRegistration(student);
    }

    public List<StudentCourse> getStudentByCourseAndSemester(Course course, String semester) {
        List<StudentRegistration> students = regRepo.findBySemesterId(semester);
        List<StudentCourse> result = new ArrayList<>();
        for (StudentRegistration student : students) {
            List<StudentCourse> coursesForStudent = studCrsRepo.findByCourseAndStudentRegistration(course, student);
            result.addAll(coursesForStudent);
        }
        return result;
    }
}
