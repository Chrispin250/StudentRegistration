package com.example.StudentManagementSystem.Service;

import com.example.StudentManagementSystem.Model.*;
import com.example.StudentManagementSystem.Repository.CourseDefRepository;
import com.example.StudentManagementSystem.Repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomCourseService {
    private static final Logger logger = LoggerFactory.getLogger(CustomCourseService.class);

    @Autowired
    private CourseRepository customCourseRepository;
    @Autowired
    private CourseDefRepository customCourseDefRepository;

    public String saveCustomCourse(CustomCourse customCourse) {
        if (customCourse != null) {
            if (isCustomCourseExists(customCourse.getDepartment(), customCourse.getSemester())) {
                return "Custom Course already exists in the department and semester.";
            } else {
                customCourseRepository.save(customCourse);
                return "Custom Course created successfully.";
            }
        } else {
            return null;
        }
    }

    public boolean isCustomCourseExists(AcademicUnit department, Semester semester) {
        return customCourseRepository.existsByDepartmentAndSemester(department, semester);
    }

    public List<CustomCourse> listCustomCourses() {
        return customCourseRepository.findAll();
    }

    public CustomCourse getCustomCourseById(Integer id) {
        logger.info("Custom Course with id: {}", id);
        return customCourseRepository.findById(id).orElse(null);
    }

    public String updateCustomCourse(CourseDefinition customCourseDef, CustomCourse customCourse) {
        logger.info("Updating Custom Course with Code: {}", customCourseDef);
        try {
            if (customCourse != null) {
                if (isCustomCourseDefinitionExists(customCourseDef)) {
                    customCourseRepository.save(customCourse);
                    logger.info("Custom Course updated successfully");
                    return "Custom Course updated successfully";
                } else {
                    return "Custom Course not found";
                }
            } else {
                return "Invalid input";
            }
        } catch (Exception ex) {
            logger.error("Failed to update Custom Course", ex);
            return "Custom Course not updated";
        }
    }

    public boolean isCustomCourseDefinitionExists(CourseDefinition customCourseDefinition) {
        return customCourseRepository.existsByCourseDefinition(customCourseDefinition);
    }

    public String deleteCustomCourse(Integer id) {
        logger.info("Deleting Custom Course with id: {}", id);
        try {
            if (id != null) {
                if (isCustomCourseIdExists(id)) {
                    customCourseRepository.deleteById(id);
                    logger.info("Custom Course deleted successfully");
                    return "Custom Course deleted successfully";
                } else {
                    return "Custom Course not found";
                }
            } else {
                return "Invalid input";
            }
        } catch (Exception e) {
            logger.error("Failed to delete Custom Course", e);
            return "Custom Course was not deleted successfully";
        }
    }

    public List<CustomCourse> getCustomCoursesByDepartmentAndSemester(AcademicUnit department, Semester semester) {
        return customCourseRepository.findByDepartmentAndSemester(department, semester);
    }
}
