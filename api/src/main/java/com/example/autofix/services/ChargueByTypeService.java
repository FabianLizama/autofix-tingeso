package com.example.autofix.services;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.autofix.entities.ChargueByTypeEntity;
import com.example.autofix.repositories.ChargueByTypeRepository;

@Service
public class ChargueByTypeService {
    @Autowired
    ChargueByTypeRepository chargueByTypeRepository;

    // Create
    public ChargueByTypeEntity saveChargueByType(ChargueByTypeEntity chargueByType) {
        return chargueByTypeRepository.save(chargueByType);
    }

    // Read
    public ArrayList<ChargueByTypeEntity> getAllChargueByTypes() {
        return (ArrayList<ChargueByTypeEntity>) chargueByTypeRepository.findAll();
    }

    public ArrayList<ChargueByTypeEntity> getAllKmChargueByTypes() {
        return (ArrayList<ChargueByTypeEntity>) chargueByTypeRepository.findBySurchargeType("km");
    }

    public ArrayList<ChargueByTypeEntity> getAllAntiqChargueByTypes() {
        return (ArrayList<ChargueByTypeEntity>) chargueByTypeRepository.findBySurchargeType("antiquity");
    }

    public ChargueByTypeEntity getChargueByTypeById(Long id) {
        return chargueByTypeRepository.findById(id).orElse(null);
    }

    // Update
    public ChargueByTypeEntity updateChargueByType(ChargueByTypeEntity chargueByType) throws Exception{
        try{
            ChargueByTypeEntity chargueByTypeUpdated = chargueByTypeRepository.save(chargueByType);
            return chargueByTypeUpdated;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    // Delete
    public void deleteChargueByType(Long id) throws Exception {
        try{
            chargueByTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
