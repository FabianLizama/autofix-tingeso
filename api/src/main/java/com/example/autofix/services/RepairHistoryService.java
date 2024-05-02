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

    @Autowired
    QuantityDiscountService QuantityDiscountService;

    double IVA = 0.19;

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
        Double antiquity_rechargue = chargueByTypeService.getChargueAntiqByType(actualYear-car.getFabYear(), car.getType());
        
        Long rechargues = (long) (price*km_rechargue+price*antiquity_rechargue);
        newRepairHistory.setRechargues(rechargues);

        int repairNum = getTotalRepairsByCarId(car.getId());

        double discountPercent = QuantityDiscountService.getDiscountPercent(repairNum, car.getMotorType());
        Long discount = (long) (price*discountPercent);
        newRepairHistory.setDiscount(discount);

        Long total = price+rechargues-discount;
        Long totalIva = (long) (total*IVA);
        
        newRepairHistory.setTotalAmount(total+totalIva);
        return saveRepairHistory(newRepairHistory);
    }

    // Read
    public ArrayList<RepairHistoryEntity> getAllRepairHistories() {
        return (ArrayList<RepairHistoryEntity>) repairHistoryRepository.findAll();
    }

    public RepairHistoryEntity getRepairHistoryById(Long id) {
        return repairHistoryRepository.findById(id).orElse(null);
    }

    public int getTotalRepairsByCarId(Long carId) {
        return repairHistoryRepository.getTotalRepairsByCarId(carId);
    }

    public ArrayList<Object[]> getAllRepairsCosts() {
        return repairHistoryRepository.getAllRepairsCosts();
    }

    public ArrayList<Object[]> getReport2() {
        return repairHistoryRepository.getReport2();
    }

    public ArrayList<Object[]> getReport3() {
        return repairHistoryRepository.getReport3();
    }

    public ArrayList<Object[]> getReport4() {
        return repairHistoryRepository.getReport4();
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
