package com.example.product.Controller;

import com.example.product.Entity.Charges;
import com.example.product.Payload.ChargeResponseDto;
import com.example.product.Service.ChargesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/charges")
public class ChargeController {
// http://localhost:8080/api/charges

    @Autowired
    private ChargesService chargeService;

    @GetMapping("/{chargeId}")
    public ResponseEntity<ChargeResponseDto> getChargeById(@PathVariable Long chargeId) {
        Charges charge = chargeService.getChargeById(chargeId);
        if (charge == null) {
            return ResponseEntity.notFound().build();
        }
        ChargeResponseDto chargeResponseDto = createChargeResponseDto(charge);
        return ResponseEntity.ok(chargeResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<ChargeResponseDto>> getAllCharges() {
        List<Charges> charges = chargeService.getAllCharges();
        List<ChargeResponseDto> chargeResponseDtos = charges.stream().map(this::createChargeResponseDto).collect(Collectors.toList());
        return ResponseEntity.ok(chargeResponseDtos);
    }

    @PostMapping
    public ResponseEntity<ChargeResponseDto> addCharge(@RequestBody ChargeResponseDto chargeRequestDto) {
        Charges charge = createChargeFromRequestDto(chargeRequestDto);
        charge = chargeService.addCharge(charge);
        ChargeResponseDto chargeResponseDto = createChargeResponseDto(charge);
        return ResponseEntity.ok(chargeResponseDto);
    }

    @PutMapping("/{chargeId}")
    public ResponseEntity<ChargeResponseDto> updateCharge(@PathVariable Long chargeId, @RequestBody ChargeResponseDto chargeRequestDto) {
        Charges charge = chargeService.getChargeById(chargeId);
        if (charge == null) {
            return ResponseEntity.notFound().build();
        }
        charge = updateChargeFromRequestDto(chargeRequestDto, charge);
        charge = chargeService.updateCharge(charge);
        ChargeResponseDto chargeResponseDto = createChargeResponseDto(charge);
        return ResponseEntity.ok(chargeResponseDto);
    }

    @DeleteMapping("/{chargeId}")
    public ResponseEntity<Void> deleteCharge(@PathVariable Long chargeId) {
        chargeService.deleteCharge(chargeId);
        return ResponseEntity.noContent().build();
    }

    private Charges createChargeFromRequestDto(ChargeResponseDto chargeRequestDto) {
        Charges charge = new Charges();
        charge.setGst(chargeRequestDto.getGst());
        charge.setDelivery(chargeRequestDto.getDelivery());
        return charge;
    }

    private Charges updateChargeFromRequestDto(ChargeResponseDto chargeRequestDto, Charges charge) {
        charge.setGst(chargeRequestDto.getGst());
        charge.setDelivery(chargeRequestDto.getDelivery());
        return charge;
    }

    private ChargeResponseDto createChargeResponseDto(Charges charge) {
        ChargeResponseDto chargeResponseDto = new ChargeResponseDto();
        chargeResponseDto.setChargeId(charge.getChargeId());
        chargeResponseDto.setGst(charge.getGst());
        chargeResponseDto.setDelivery(charge.getDelivery());
        return chargeResponseDto;
    }
}

