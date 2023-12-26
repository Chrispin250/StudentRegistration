package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.Semester;
import com.auca.StudentRegistration.Service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/semester", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class SemesterController {

    @Autowired
    private SemesterService semService;

    @PostMapping(value = "/saveSemester")
    public ResponseEntity<String> createSemester(@RequestBody Semester semester) {
        if (semester != null) {
            try {
                String message = semService.saveSemester(semester);
                return new ResponseEntity<>("Semester Saved Successfully", HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Semester Not Saved: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listSemesters")
    public ResponseEntity<List<Semester>> listSemesters() {
        try {
            List<Semester> semesters = semService.listSemesters();
            return new ResponseEntity<>(semesters, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateSemester/{code}")
    public ResponseEntity<String> updateSemester(@PathVariable String code, @RequestBody Semester semester) {
        if (semester != null) {
            try {
                String message = semService.updateSemester(code, semester);
                return new ResponseEntity<>("Semester Updated Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Semester Not Updated: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteSemester/{code}")
    public ResponseEntity<String> deleteSemester(@PathVariable String code) {
        if (code != null) {
            try {
                String message = semService.deleteSemester(code);
                return new ResponseEntity<>("Semester Deleted Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Semester Not Deleted: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}
