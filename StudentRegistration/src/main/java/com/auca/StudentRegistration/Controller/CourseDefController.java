package com.auca.StudentRegistration.Controller;

import com.auca.StudentRegistration.Model.CourseDefinition;
import com.auca.StudentRegistration.Service.CourseDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/crsDef", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class CourseDefController {

    @Autowired
    private CourseDefService crsDefService;

    @PostMapping(value = "/saveDef")
    public ResponseEntity<String> createDef(@RequestBody CourseDefinition crsDef) {
        if (crsDef != null) {
            try {
                String message = crsDefService.saveDef(crsDef);
                return new ResponseEntity<>("Course Definition Saved Successfully", HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>("Course Definition Not Saved: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/listDefs")
    public ResponseEntity<List<CourseDefinition>> listDefs() {
        try {
            List<CourseDefinition> defs = crsDefService.listDefs();
            return new ResponseEntity<>(defs, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/updateDef/{code}")
    public ResponseEntity<String> updateDef(@PathVariable String code, @RequestBody CourseDefinition crsDef) {
        if (crsDef != null) {
            try {
                String message = crsDefService.updateDef(code, crsDef);
                return new ResponseEntity<>("Course Definition Updated Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Course Definition Not Updated: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/deleteDef/{code}")
    public ResponseEntity<String> deleteDef(@PathVariable String code) {
        if (code != null) {
            try {
                String message = crsDefService.deleteDef(code);
                return new ResponseEntity<>("Course Definition Deleted Successfully", HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity<>("Course Definition Not Deleted: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Invalid input", HttpStatus.BAD_REQUEST);
        }
    }
}
