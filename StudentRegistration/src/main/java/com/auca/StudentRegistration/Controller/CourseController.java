package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.*;
import com.auca.StudentRegistration.Service.CourseService;
import com.auca.StudentRegistration.Service.SemesterService;
import com.auca.StudentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/course", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private SemesterService semesterService;

    @PostMapping(value = "/saveCourse")
    public ResponseEntity<String> createCourse(@RequestBody Course course) {
        if (course != null) {
            try {
                String message = courseService.saveCourse(course);
                return new ResponseEntity<>("Course Saved Successfully", HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Course Not Saved: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listCourse")
    public ResponseEntity<List<Course>> listCourses() {
        List<Course> courses = courseService.listCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @PutMapping(value = "/updateCourse/{code}")
    public ResponseEntity<String> updateCourse(@PathVariable CourseDefinition code, @RequestBody Course course) {
        if (course != null) {
            try {
                String message = courseService.updateCourse(code, course);
                return new ResponseEntity<>("Course Updated Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Course Not Updated: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteCourse/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Integer id) {
        if (id != null) {
            try {
                String message = courseService.deleteCourse(id);
                return new ResponseEntity<>("Course Deleted Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Course Not Deleted: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<Course>> listCoursesByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {
        try {
            AcademicUnit department = unitService.getAcademicUnitByCode(departmentCode);
            Semester semester = semesterService.getSemesterById(semesterId);

            if (department != null && semester != null) {
                List<Course> courses = courseService.getCoursesByDepartmentAndSemester(department, semester);
                return new ResponseEntity<>(courses, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
