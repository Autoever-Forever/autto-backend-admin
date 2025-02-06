package com.example.auttobackendadmin.dto.requestDto;

import lombok.Builder;
import lombok.Getter;
import java.util.UUID;

@Getter
public class InventoryValidationRequestDto {
    private final UUID inventoryId;
    private final Integer quantity;

    @Builder
    public InventoryValidationRequestDto(UUID inventoryId, Integer quantity) {
        this.inventoryId = inventoryId;
        this.quantity = quantity;
    }
} 