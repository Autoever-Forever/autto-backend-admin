package com.example.auttobackendadmin.controller;

import com.example.auttobackendadmin.dto.request.InventoryValidationRequest;
import com.example.auttobackendadmin.dto.response.InventoryValidationResponse;
import com.example.auttobackendadmin.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class InventoryController {

    private final InventoryService seatReservationService;

    @PostMapping("/api/v1/seats/validate")
    public ResponseEntity<InventoryValidationResponse> reserveSeats( @RequestBody InventoryValidationRequest request)
    {
        log.info("Received seat reservation request for inventory: {}", request.getInventoryId());
        InventoryValidationResponse response = seatReservationService.reserveSeats(request.toServiceDto());
        log.info("Processed seat reservation request. Success: {}", response.isSuccess());
        
        return ResponseEntity.ok(response);
    }
}