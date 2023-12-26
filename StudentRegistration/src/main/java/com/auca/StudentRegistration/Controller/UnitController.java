package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/unit", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class UnitController {

    @Autowired
    private UnitService unitService;

    @PostMapping(value = "/saveUnit")
    public ResponseEntity<String> createUnit(@RequestBody AcademicUnit unit) {
        if (unit != null) {
            try {
                String message = unitService.saveUnit(unit);
                return new ResponseEntity<>("Unit Saved Successfully", HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listUnits")
    public ResponseEntity<List<AcademicUnit>> listUnits() {
        try {
            List<AcademicUnit> units = unitService.listUnits();
            return new ResponseEntity<>(units, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateUnit/{code}")
    public ResponseEntity<String> updateUnit(@PathVariable String code, @RequestBody AcademicUnit unit) {
        if (unit != null) {
            try {
                String message = unitService.updateUnit(code, unit);
                return new ResponseEntity<>("Unit Updated Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteUnit/{code}")
    public ResponseEntity<String> deleteUnit(@PathVariable String code) {
        if (code != null) {
            try {
                String message = unitService.deleteUnit(code);
                return new ResponseEntity<>("Unit Deleted Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}
