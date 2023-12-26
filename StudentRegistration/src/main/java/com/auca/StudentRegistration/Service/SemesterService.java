package com.auca.StudentRegistration.Service;

import com.auca.StudentRegistration.Model.Semester;
import com.auca.StudentRegistration.Repository.SemesterRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {
    private static final Logger logger = LoggerFactory.getLogger(SemesterService.class);

    @Autowired
    private SemesterRepo semRepo;

    public String createSemester(Semester semester) {
        if (semester != null) {
            if (semesterExists(semester.getId())) {
                return "Semester already exists";
            } else {
                semRepo.save(semester);
                return "Semester created successfully";
            }
        } else {
            return "Invalid input: Semester is null";
        }
    }

    public boolean semesterExists(String id) {
        return id != null && semRepo.existsById(id);
    }

    public List<Semester> listSemesters() {
        return semRepo.findAll();
    }

    public String updateSemester(String id, Semester updatedSemester) {
        logger.info("Updating semester with id: {}", id);
        try {
            if (updatedSemester != null) {
                if (semesterExists(id)) {
                    semRepo.save(updatedSemester);
                    logger.info("Semester updated successfully");
                    return "Semester updated successfully";
                } else {
                    return "Semester not found";
                }
            } else {
                return "Invalid input: Updated semester is null";
            }
        } catch (Exception ex) {
            logger.error("Failed to update Semester. Exception: {}", ex.getMessage());
            return "Semester not updated";
        }
    }

    public String deleteSemester(String id) {
        logger.info("Deleting Semester with id: {}", id);
        try {
            if (id != null) {
                if (semesterExists(id)) {
                    semRepo.deleteById(id);
                    logger.info("Semester deleted successfully");
                    return "Semester deleted successfully";
                } else {
                    return "Semester not found";
                }
            } else {
                return "Invalid input: Semester id is null";
            }
        } catch (Exception e) {
            logger.error("Failed to delete Semester. Exception: {}", e.getMessage());
            return "Semester not deleted";
        }
    }

    public Semester getSemesterById(String id) {
        logger.info("Getting Semester with id: {}", id);
        return semRepo.findById(id).orElse(null);
    }
}
