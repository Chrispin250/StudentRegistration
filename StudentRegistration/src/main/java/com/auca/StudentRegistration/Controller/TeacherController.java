package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.*;
import com.auca.StudentRegistration.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/StudCourse", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class StudentCrsController {

    @Autowired
    private StudCourseService studCrsService;

    @Autowired
    private SemesterService semesterService;

    @Autowired
    private StudRegistrationService regService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseDefService defService;

    @PostMapping(value = "/saveStudCourse")
    public ResponseEntity<String> createStudCrs(@RequestBody StudentCourse studCrs) {
        if (studCrs != null) {
            try {
                String message = studCrsService.saveStudCourse(studCrs);
                return new ResponseEntity<>("Student Course Saved Successfully", HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listStudCourse")
    public ResponseEntity<List<StudentCourse>> listStudCrs() {
        try {
            List<StudentCourse> studCrs = studCrsService.listStudentsCourse();
            return new ResponseEntity<>(studCrs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateStudCourse/{id}")
    public ResponseEntity<String> updateStudCourse(@PathVariable Integer id, @RequestBody StudentCourse studCrs) {
        if (studCrs != null) {
            try {
                String message = studCrsService.updateStudCourse(id, studCrs);
                return new ResponseEntity<>("Student Course Updated Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteStudCourse/{id}")
    public ResponseEntity<String> deleteStudCrs(@PathVariable Integer id) {
        if (id != null) {
            try {
                String message = studCrsService.deleteStudCourse(id);
                return new ResponseEntity<>("Student Course Deleted Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listByCourse/{studentId}")
    public ResponseEntity<List<StudentCourse>> listByCourse(@PathVariable String studentId) {
        try {
            StudentRegistration stud = regService.getRegistrationByStudentId(studentId);

            if (stud != null) {
                List<StudentCourse> crs = studCrsService.getCoursesByStudentId(studentId);
                return new ResponseEntity<>(crs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listByCourseAndSemester/{courseCode}/{semesterId}")
    public ResponseEntity<List<StudentCourse>> listByCourseAndSemester(
            @PathVariable Integer courseCode,
            @PathVariable String semesterId) {
        try {
            Course course = courseService.getCourseById(courseCode);

            if (course != null) {
                List<StudentCourse> crs = studCrsService.getStudentByCourseAndSemester(course, semesterId);
                return new ResponseEntity<>(crs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
