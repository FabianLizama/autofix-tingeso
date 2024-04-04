package com.example.autofix.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.autofix.entities.RepairHistoryEntity;
import com.example.autofix.repositories.RepairHistoryRepository;

@Service
public class RepairHistoryService {
    @Autowired
    RepairHistoryRepository repairHistoryRepository;

    // Create
    public RepairHistoryEntity saveRepairHistory(RepairHistoryEntity repairHistory) {
        return repairHistoryRepository.save(repairHistory);
    }

    // Read
    public ArrayList<RepairHistoryEntity> getAllRepairHistories() {
        return (ArrayList<RepairHistoryEntity>) repairHistoryRepository.findAll();
    }

    public RepairHistoryEntity getRepairHistoryById(Long id) {
        return repairHistoryRepository.findById(id).orElse(null);
    }

    // Update
    public RepairHistoryEntity updateRepairHistory(RepairHistoryEntity repairHistory) throws Exception{
        try{
            RepairHistoryEntity repairHistoryUpdated = repairHistoryRepository.save(repairHistory);
            return repairHistoryUpdated;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    // Delete
    public void deleteRepairHistory(Long id) throws Exception {
        try{
            repairHistoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
