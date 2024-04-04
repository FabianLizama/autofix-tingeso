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

    public ChargueByTypeEntity getChargueByTypeById(Long id) {
        return chargueByTypeRepository.findById(id).orElse(null);
    }

    // Update
    public ChargueByTypeEntity updateChargueByType(ChargueByTypeEntity chargueByType) {
        return chargueByTypeRepository.save(chargueByType);
    }
    
    // Delete
    public boolean deleteChargueByType(Long id) throws Exception {
        try{
            chargueByTypeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
