package com.example.autofix.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.autofix.entities.RepairEntity;
import com.example.autofix.repositories.RepairRepository;

@Service
public class RepairService {
    @Autowired
    RepairRepository repairRepository;

    // Create
    public RepairEntity saveRepair(RepairEntity repair) {
        return repairRepository.save(repair);
    }

    // Read
    public ArrayList<RepairEntity> getAllRepairs() {
        return (ArrayList<RepairEntity>) repairRepository.findAll();
    }

    public RepairEntity getRepairById(Long id) {
        return repairRepository.findById(id).orElse(null);
    }

    // Update
    public RepairEntity updateRepair(RepairEntity repair) {
        return repairRepository.save(repair);
    }
    
    // Delete
    public boolean deleteRepair(Long id) throws Exception {
        try{
            repairRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
