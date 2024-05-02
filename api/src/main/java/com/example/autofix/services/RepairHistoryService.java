package com.example.autofix.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.autofix.entities.CarEntity;
import com.example.autofix.entities.RepairHistoryEntity;
import com.example.autofix.repositories.RepairHistoryRepository;

@Service
public class RepairHistoryService {
    @Autowired
    RepairHistoryRepository repairHistoryRepository;

    @Autowired
    CarService carService;

    @Autowired
    RepairPriceService repairPriceService;

    @Autowired
    ChargueByTypeService chargueByTypeService;

    // Create
    public RepairHistoryEntity saveRepairHistory(RepairHistoryEntity repairHistory) {
        return repairHistoryRepository.save(repairHistory);
    }

    public RepairHistoryEntity registerRepair(String licensePlate, Long repairTypeId) {
        RepairHistoryEntity newRepairHistory = new RepairHistoryEntity();
        CarEntity car = carService.getCarByLicensePlate(licensePlate);
        newRepairHistory.setAdmissionDateTime(LocalDateTime.now());
        newRepairHistory.setRepairTypeId(repairTypeId);
        newRepairHistory.setCarId(car.getId());

        int price = repairPriceService.getRepairPriceByRepairType(repairTypeId, car.getMotorType());
        newRepairHistory.setCost((long) price);

        double km_rechargue = chargueByTypeService.getChargueKmByType(car.getKm(), car.getType());
        int actualYear = LocalDateTime.now().getYear();
        double antiquity_rechargue = chargueByTypeService.getChargueAntiqByType(actualYear-car.getFabYear(), car.getType());
        System.out.println("Price: " + antiquity_rechargue);
        return newRepairHistory;
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
