package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Model.Semester;
import com.auca.StudentRegistration.Model.StudentRegistration;
import com.auca.StudentRegistration.Service.StudRegistrationService;
import com.auca.StudentRegistration.Service.SemesterService;
import com.auca.StudentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/studentRegistration", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class StudRegController {

    @Autowired
    private StudRegistrationService regService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private SemesterService semesterService;

    @PostMapping(value = "/saveRegistration")
    public ResponseEntity<String> createReg(@RequestBody StudentRegistration studentReg) {
        if (studentReg != null) {
            try {
                String message = regService.saveRegistration(studentReg);
                if (message != null && message.startsWith("Student Registered Successfully")) {
                    return new ResponseEntity<>(message, HttpStatus.CREATED);
                } else if (message != null && message.startsWith("Student with ID")) {
                    return new ResponseEntity<>(message, HttpStatus.CONFLICT);
                } else {
                    return new ResponseEntity<>("Student Not Registered", HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listRegistrations")
    public ResponseEntity<List<StudentRegistration>> listRegs() {
        try {
            List<StudentRegistration> studentRegs = regService.listStudentsReg();
            return new ResponseEntity<>(studentRegs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateRegistration/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable Integer id, @RequestBody StudentRegistration regStudent) {
        if (regStudent != null) {
            try {
                String message = regService.updateStudentReg(id, regStudent);
                return new ResponseEntity<>("Student Registration Updated Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteRegistration/{id}")
    public ResponseEntity<String> deleteStudReg(@PathVariable Integer id) {
        if (id != null) {
            try {
                String message = regService.deleteStudentReg(id);
                return new ResponseEntity<>("Student Registration Deleted Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listRegistrationsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {
        try {
            AcademicUnit department = unitService.getAcademicUnitByCode(departmentCode);
            Semester semester = semesterService.getSemesterById(semesterId);

            if (department != null && semester != null) {
                List<StudentRegistration> registrations = regService.getRegistrationsByDepartmentAndSemester(department, semester);
                return new ResponseEntity<>(registrations, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listBySemester/{semesterId}")
    public ResponseEntity<List<StudentRegistration>> listRegistrationsBySemester(
            @PathVariable String semesterId) {
        try {
            Semester semester = semesterService.getSemesterById(semesterId);

            if (semester != null) {
                List<StudentRegistration> registrations = regService.getRegistrationsBySemester(semester);
                return new ResponseEntity<>(registrations, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
