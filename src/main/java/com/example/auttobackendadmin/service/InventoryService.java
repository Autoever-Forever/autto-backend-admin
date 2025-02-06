package com.example.auttobackendadmin.service;

import com.example.auttobackendadmin.dto.requestDto.InventoryValidationRequestDto;
import com.example.auttobackendadmin.dto.response.InventoryValidationResponse;
import com.example.auttobackendadmin.entity.Inventory;
import com.example.auttobackendadmin.exception.InventoryValidation.NotExistingSeatException;
import com.example.auttobackendadmin.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
    
    private final InventoryRepository inventoryRepository;

    @Transactional
    public InventoryValidationResponse reserveSeats(InventoryValidationRequestDto dto) {
        Inventory inventory = inventoryRepository.findByIdForUpdate(dto.getInventoryId())
            .orElseThrow(NotExistingSeatException::new);

        try {
            inventory.reserveSeats(dto.getQuantity());
            
            // 변경된 엔티티를 저장
            inventoryRepository.save(inventory);

            return InventoryValidationResponse.builder()
                .success(true)
                .message("좌석 예약이 완료되었습니다.")
                .result(InventoryValidationResponse.ReservationResult.builder()
                    .remainingSeats(inventory.getAvailableSeats())
                    .reservedSeats(inventory.getReservedSeats())
                    .build())
                .build();

        } catch (Exception e) {
            return InventoryValidationResponse.builder()
                .success(false)
                .message(e.getMessage())
                .build();
        }
    }
} 