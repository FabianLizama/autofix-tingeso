package com.example.autofix.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.autofix.entities.RepairPriceEntity;
import com.example.autofix.repositories.RepairPriceRepository;

@Service
public class RepairPriceService {
    @Autowired
    RepairPriceRepository RepairPriceRepository;

    // Create
    public RepairPriceEntity saveRepairPrice(RepairPriceEntity repairPrice) {
        return RepairPriceRepository.save(repairPrice);
    }

    // Read
    public ArrayList<RepairPriceEntity> getAllRepairPrices() {
        return (ArrayList<RepairPriceEntity>) RepairPriceRepository.findAll();
    }

    public RepairPriceEntity getRepairPriceById(Long id) {
        return RepairPriceRepository.findById(id).orElse(null);
    }

    public int getRepairPriceByRepairType(Long repairTypeId, String motorType) {
        RepairPriceEntity repairPrice = RepairPriceRepository.findByRepairTypeId(repairTypeId);
        if (motorType.equals("gasoline")){
            return repairPrice.getGasolinePrice();
        } else if (motorType.equals("diesel")){
            return repairPrice.getDieselPrice();
        } else if (motorType.equals("electric")){
            return repairPrice.getElectricPrice();
        } else if (motorType.equals("hybrid")){
            return repairPrice.getHybridPrice();
        } else {
            return 0;
        }
    }

    // Update
    public RepairPriceEntity updateRepairPrice(RepairPriceEntity repairPrice) throws Exception{
        try{
            RepairPriceEntity repairPriceUpdated = RepairPriceRepository.save(repairPrice);
            return repairPriceUpdated;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    // Delete
    public void deleteRepairPrice(Long id) throws Exception {
        try{
            RepairPriceRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
