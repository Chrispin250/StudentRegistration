package com.auca.StudentRegistration.Service;

import com.auca.StudentRegistration.Model.AcademicUnit;
import com.auca.StudentRegistration.Repository.UnitRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UnitService {
    private static final Logger logger = LoggerFactory.getLogger(UnitService.class);

    @Autowired
    private UnitRepo unitRepo;

    @Transactional
    public String createUnit(AcademicUnit acadUnit) {
        if (acadUnit != null) {
            if (isAcadExists(acadUnit.getCode())) {
                return "Unit already exists";
            } else {
                unitRepo.save(acadUnit);
                return "Unit created successfully";
            }
        } else {
            return "Invalid input: AcademicUnit is null";
        }
    }

    public boolean isAcadExists(String code) {
        return unitRepo.existsById(code);
    }

    public List<AcademicUnit> listUnits() {
        return unitRepo.findAll();
    }

    @Transactional
    public String updateUnit(String code, AcademicUnit acadUnit) {
        logger.info("Updating Unit with code: {}", code);
        try {
            if (acadUnit != null) {
                if (isAcadExists(code)) {
                    unitRepo.save(acadUnit);
                    logger.info("Unit updated successfully");
                    return "Unit updated successfully";
                } else {
                    return "Unit not found";
                }
            } else {
                return "Invalid input: Updated AcademicUnit is null";
            }
        } catch (Exception ex) {
            logger.error("Failed to update Unit. Exception: {}", ex.getMessage());
            return "Unit not updated";
        }
    }

    @Transactional
    public String deleteUnit(String code) {
        logger.info("Deleting unit with code: {}", code);
        try {
            if (code != null) {
                if (isAcadExists(code)) {
                    unitRepo.deleteById(code);
                    logger.info("Unit deleted successfully");
                    return "Unit deleted successfully";
                } else {
                    return "Unit not found";
                }
            } else {
                return "Invalid input: Code is null";
            }
        } catch (Exception e) {
            logger.error("Failed to delete Unit. Exception: {}", e.getMessage());
            return "Unit not deleted";
        }
    }

    public AcademicUnit getAcademicUnitByCode(String code) {
        return unitRepo.findById(code).orElse(null);
    }
}
