package com.example.product.Service;

import com.example.product.Entity.Charges;
import com.example.product.Repository.ChargesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargesService {
    @Autowired
    private ChargesRepository chargeRepository;

    public Charges getChargeById(Long chargeId) {
        return chargeRepository.findById(chargeId).orElse(null);
    }

    public Charges addCharge(Charges charge) {
        return chargeRepository.save(charge);
    }

    public Charges updateCharge(Charges charge) {
        return chargeRepository.save(charge);
    }

    public void deleteCharge(Long chargeId) {
        chargeRepository.deleteById(chargeId);
    }


    public List<Charges> getAllCharges() {
        return chargeRepository.findAll();
    }
}
