package com.example.auttobackendadmin.dto.request;

import com.example.auttobackendadmin.dto.requestDto.InventoryValidationRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;
import com.example.auttobackendadmin.util.HexStringToUUIDConverter;

@Getter
@NoArgsConstructor
public class InventoryValidationRequest {
    private String inventoryId;
    private Integer quantity;

    public InventoryValidationRequestDto toServiceDto() {
        UUID uuid = HexStringToUUIDConverter.convertHexToUUID(this.inventoryId);
        return InventoryValidationRequestDto.builder()
                .inventoryId(uuid)
                .quantity(this.quantity)
                .build();
    }
} 